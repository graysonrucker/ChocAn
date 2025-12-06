package chocan.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import chocan.model.Member;
import chocan.model.Provider;
import chocan.report.MemberReport;
import chocan.report.MemberServiceSummary;
import chocan.report.ProviderReport;
import chocan.report.ProviderServiceSummary;
import chocan.report.SummaryReport;
import chocan.service.ChocAnSystem;
//Owen Taylor
public class ManagerMenu {
    private final ChocAnSystem system;

    public ManagerMenu(ChocAnSystem system) 
    {
        this.system = system;
    }

    public void start() throws IOException 
    {
        System.out.println("Please select one or <exit> to logout");
        System.out.println("Summary report(S), provider report(P), member report(M):");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String command = br.readLine();
        
        switch (command) {
            case "P":
                System.out.println("Please input provider number");
                String pnumber = br.readLine();
                ProviderReport report = system.ManagerRequestProvider(pnumber);

                PrintProviderReport(report);

            case "S":
                PrintSummaryReport();
                break;
            case "M":
                System.out.println("Please input member number");
                String mnumber = br.readLine();
                MemberReport mreport = system.ManagerRequestMember(mnumber);

                PrintMemberReport(mreport);
                break;
            case "exit":
                return;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
        start();
    }

    public void PrintProviderReport(ProviderReport report) {

        Provider provider = report.getProvider();

        System.out.println(provider.getProviderNumber());
        System.out.println(provider.getName());
        System.out.println(provider.getAddress());
        System.out.println(provider.getCity());
        System.out.println(provider.getState());
        System.out.println(provider.getZipCode());

        for(ProviderServiceSummary services : report.getProviderServiceList()){
            System.out.println(services.getServiceDate());
            System.out.println(services.getDateTimeReceived());
            System.out.println(services.getMemberName());
            System.out.println(services.getMemberNumber());
            System.out.println(services.getServiceCode());
            System.out.println(services.getFee());
        }
    }

    public void PrintSummaryReport() {
        ArrayList<MemberReport> memberReportList = system.generateAllMemberReports();
        ArrayList<ProviderReport> providerReportList = system.generateAllProviderReports();
        SummaryReport summaryReport = system.generateSummaryReport();
        
        for(MemberReport report : memberReportList){
            System.out.println(report.getMember().getName());
            System.out.println(report.getMember().getMemberNumber());
            System.out.println(report.getMember().getAddress());
            System.out.println(report.getMember().getCity());
            System.out.println(report.getMember().getState());
            System.out.println(report.getMember().getZipCode());
            for(MemberServiceSummary summary : report.getMemberServiceList()){
                System.out.println("\t" + summary.getDateOfService() +  "\t");
                System.out.println("\t" + summary.getProviderName() + "\t");
                System.out.println("\t" + summary.getServiceName());
            }
        }
        System.out.println();
        for(ProviderReport report : providerReportList){
            System.out.println(report.getProvider().getName());
            System.out.println(report.getProvider().getProviderNumber());
            System.out.println(report.getProvider().getAddress());
            System.out.println(report.getProvider().getCity());
            System.out.println(report.getProvider().getState());
            System.out.println(report.getProvider().getZipCode());
            for(ProviderServiceSummary summary : report.getProviderServiceList()){
                System.out.println("\t Date serviced: " + summary.getServiceDate());
                System.out.println("\t Date and time received: " + summary.getDateTimeReceived());
                System.out.println("\t Member name: " + summary.getMemberName());
                System.out.println("\t Member number: " + summary.getMemberNumber());
                System.out.println("\t Service Code: " + summary.getServiceCode());
                System.out.println("\t Fee: $" + summary.getFee());
                System.out.println();
            }
        }
        System.out.println();
            ArrayList<Provider> providersToPay  = new ArrayList<Provider>(summaryReport.getProvidersToPay());
            ArrayList<Integer> providerServiceCount = new ArrayList<Integer>(summaryReport.getProviderServiceCount());
            ArrayList<Double> providerTotalFees = new ArrayList<Double>(summaryReport.getProviderTotalFees());
        for(int i = 0; i < providersToPay.size(); i++){
            System.out.println("Provider name: " + providersToPay.get(i).getName()
                                + "\t Total services: " + providerServiceCount.get(i)
                                + "\t Total fees: $" + providerTotalFees.get(i));
        }
        System.out.println("Total number of providers: " + summaryReport.getNumProviders());
        System.out.println("Total number of services: " + summaryReport.getNumServices());
        System.out.println("Fee for all providers: $" + summaryReport.getOverallFee());
    }

    
    public void PrintMemberReport(MemberReport report) {

        Member member = report.getMember();

        System.out.println(member.getMemberNumber());
        System.out.println(member.getName());
        System.out.println(member.getAddress());
        System.out.println(member.getCity());
        System.out.println(member.getState());
        System.out.println(member.getZipCode());

        for(MemberServiceSummary services : report.getMemberServiceList()){
            System.out.println(services.getDateOfService());
            System.out.println(services.getProviderName());
            System.out.println(services.getServiceName());
        }
    }
}