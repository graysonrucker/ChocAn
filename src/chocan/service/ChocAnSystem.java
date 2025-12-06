package chocan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chocan.model.*;
import chocan.report.*;

public class ChocAnSystem {
    private HashMap<String, Member> memberMap = new HashMap<>();
    private HashMap<String, Provider> providerMap = new HashMap<>();
    private static final Map<String, String> operatorLogins = Map.of(
        "admin123", "Operator1",
        "prov555",  "Operator2",
        "testpass", "Operator3");
    private static final Map<String, String> managerLogins = Map.of("angrychair", "manager");
    private ArrayList<String> providerIds = new ArrayList<>();
    private ArrayList<ServiceRecord> serviceRecordList = new ArrayList<>();
    
    public boolean verifyProvider(String providerNumber){
        if(providerMap.containsKey(providerNumber)){
            return true;
        }
        return false;
    }

    public boolean verifyOperator(String username, String password){
        if(operatorLogins.get(password) == username){
            return true;
        }
        return false;
    }

    public boolean verifyManager(String username, String password){
        if(managerLogins.get(password).equals(username)){
            return true;
        }
        return false;
    }

    public boolean validateMember(String MemberNumber){
        if(memberMap.containsKey(MemberNumber)){
            return true;
        }
        return false;
    }

    public void addServiceRecord(String currDateAndTime, String dateOfService, String providerNumber,
                            String memberNumber, String serviceCode){
        ServiceRecord newServiceRecord = new ServiceRecord(currDateAndTime, dateOfService, providerNumber, memberNumber, serviceCode);
        serviceRecordList.add(newServiceRecord);
    }

    public ArrayList<ProviderDirectory.Service> requestProviderDirectory(){
        ArrayList<ProviderDirectory.Service> providerDirectoryList = ProviderDirectory.getAllServices();
        return providerDirectoryList;
    }

    public void addMember(Member member){
        String id = member.getMemberNumber();
        if (memberMap.containsKey(id)) {
            throw new IllegalArgumentException("Member ID already exists: " + id);
        }
        memberMap.put(id, member);
    }

    public void deleteMember(Member member){
        String id = member.getMemberNumber();
        if(memberMap.containsKey(member.getMemberNumber())){
            memberMap.remove(id, member);
        }
        else{
            throw new IllegalArgumentException("Member not found.");
        }
    }

    public void updateMember(Member oldMember, Member newMember){
        if(!memberMap.containsKey(oldMember.getMemberNumber())){
            throw new IllegalArgumentException("Member not found.");
        }
        memberMap.remove(oldMember.getMemberNumber(), oldMember);
        memberMap.put(newMember.getMemberNumber(), newMember);
    }

    public void addProvider(Provider provider){
        String id = provider.getProviderNumber();
        if (providerMap.containsKey(id)) {
            throw new IllegalArgumentException("Provider ID already exists: " + id);
        }
        providerMap.put(id, provider);
        providerIds.add(id);
    }

    public void deleteProvider(Provider provider){
        String id = provider.getProviderNumber();
        if(providerMap.containsKey(provider.getProviderNumber())){
            providerMap.remove(id, provider);
            providerIds.remove(id);
        }
        else{
            throw new IllegalArgumentException("Provider not found.");
        }
    }

    public void updateProvider(Provider oldProvider, Provider newProvider){
        if(!providerMap.containsKey(oldProvider.getProviderNumber())){
            throw new IllegalArgumentException("Provider not found.");
        }
        providerMap.remove(oldProvider.getProviderNumber(), oldProvider);
        providerMap.put(newProvider.getProviderNumber(), newProvider);
        if(oldProvider.getProviderNumber() != newProvider.getProviderNumber()){
            providerIds.remove(oldProvider.getProviderNumber());
            providerIds.add(newProvider.getProviderNumber());
        }
    }

    public ArrayList<MemberReport> generateAllMemberReports(){
        ArrayList<MemberReport> memberReportList = new ArrayList<>();
        for(String memberNumber : memberMap.keySet()){
            MemberReport newReport = generateMemberReport(memberNumber);
            memberReportList.add(newReport);
        }
        return memberReportList;
    }

    public ArrayList<ProviderReport> generateAllProviderReports(){
        ArrayList<ProviderReport> providerReportList = new ArrayList<>();
        for(String providerNumber : providerMap.keySet()){
            ProviderReport providerReport = generateProviderReport(providerNumber);
            providerReportList.add(providerReport);
        }
        return providerReportList;
    }

    public SummaryReport generateSummaryReport(){
        class ProviderAggregate{
            Provider provider;
            int totalServices = 0;
            double totalFee = 0;

            ProviderAggregate(Provider provider){
                this.provider = provider;
            }
        }
    
        int numServices = 0;
        double overallFee = 0;
        HashMap<String, ProviderAggregate> aggregateByNumber = new HashMap<>();

        for(ServiceRecord serviceRecord : serviceRecordList){
            double fee = ProviderDirectory.getFeeByCode(serviceRecord.getServiceCode());
            String providerNumber = serviceRecord.getProviderNumber();
            Provider provider = new Provider(providerMap.get(providerNumber));

            if(!aggregateByNumber.containsKey(providerNumber)){
                aggregateByNumber.put(providerNumber, new ProviderAggregate(provider));
            }
            aggregateByNumber.get(providerNumber).totalServices++;
            aggregateByNumber.get(providerNumber).totalFee += fee;
            numServices++;
            overallFee += fee;
        }
        ArrayList<Provider> providersToPayList = new ArrayList<>();
        ArrayList<Integer> providerServiceCount = new ArrayList<>();
        ArrayList<Double> providerTotalFees = new ArrayList<>();
        int numProviders = aggregateByNumber.size();
        for(ProviderAggregate aggregate : aggregateByNumber.values()){
            providersToPayList.add(aggregate.provider);
            providerServiceCount.add(aggregate.totalServices);
            providerTotalFees.add(aggregate.totalFee);
        }
        return new SummaryReport(providersToPayList, providerServiceCount, providerTotalFees, numProviders, numServices, overallFee);
    }

    public MemberReport ManagerRequestMember(String memberNumber){
        MemberReport newReport = generateMemberReport(memberNumber);
        return newReport;
    }

    public ProviderReport ManagerRequestProvider(String providerNumber){
        ProviderReport newReport = generateProviderReport(providerNumber);
        return newReport;
    }

    public SummaryReport ManagerRequestSummary(){
        SummaryReport newReport = generateSummaryReport();
        return newReport;
    }

    private MemberReport generateMemberReport(String memberNumber){
        Member member = new Member(memberMap.get(memberNumber));
        ArrayList<MemberServiceSummary> memberServiceList = new ArrayList<>();
        for(ServiceRecord serviceRecord : serviceRecordList){
            if(memberNumber == serviceRecord.getMemberNumber()){
                String serviceCode = serviceRecord.getServiceCode();
                String providerNumber = serviceRecord.getProviderNumber();

                String dateOfService = serviceRecord.getDateOfService();
                String providerName = providerMap.get(providerNumber).getName();
                String serviceName = ProviderDirectory.getNameByCode(serviceCode);

                MemberServiceSummary memberServiceSummary = new MemberServiceSummary(dateOfService, providerName, serviceName);
                memberServiceList.add(memberServiceSummary);
            }
        }
        return new MemberReport(member, memberServiceList);
    }

    private ProviderReport generateProviderReport(String providerNumber){
        Provider provider = new Provider(providerMap.get(providerNumber));
        ArrayList<ProviderServiceSummary> providerServiceList = new ArrayList<>();
        int totalConsulations = 0;
        double totalFee = 0;
        for(ServiceRecord serviceRecord : serviceRecordList){
            if(providerNumber == serviceRecord.getProviderNumber()){
                String dateOfService = serviceRecord.getDateOfService();
                String dateTimeReceived = serviceRecord.getCurrDateAndTime();
                String memberNumber = serviceRecord.getMemberNumber();
                String memberName = memberMap.get(memberNumber).getName();
                String serviceCode = serviceRecord.getServiceCode();
                double fee = ProviderDirectory.getFeeByCode(serviceCode);

                totalConsulations++;
                totalFee += fee;

                ProviderServiceSummary providerServiceSummary = new ProviderServiceSummary(dateOfService, dateTimeReceived, memberName, memberNumber, serviceCode, fee);
                providerServiceList.add(providerServiceSummary);
            }
        }

        return new ProviderReport(provider, providerServiceList, totalConsulations, totalFee);
    }
}