package chocan.report;

import java.util.ArrayList;

import chocan.model.Provider;

public class ProviderReport{

    private Provider provider;
    private ArrayList<ProviderServiceSummary> providerServiceList = new ArrayList<>();
    private int totalConsultations;
    private double totalFee;

    public ProviderReport(Provider provider, ArrayList<ProviderServiceSummary> providerServiceList,
                            int totalConsultations, double totalFee){
        this.provider = new Provider(provider);
        this.providerServiceList = new ArrayList<ProviderServiceSummary>(providerServiceList);
        this.totalConsultations = totalConsultations;
        this.totalFee = totalFee;
    }

    public Provider getProvider(){
        return new Provider(provider);
    }

    public ArrayList<ProviderServiceSummary> getProviderServiceList(){
        return new ArrayList<ProviderServiceSummary>(providerServiceList);
    }

    public int gettotalConsultations(){
        return totalConsultations;
    }

    public double getTotalFee(){
        return totalFee;
    }
}