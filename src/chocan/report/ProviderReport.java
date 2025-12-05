package chocan.report;

import java.util.ArrayList;

import chocan.model.Provider;

public class ProviderReport{

    private Provider provider;
    private ArrayList<ProviderServiceSummary> providerServiceList = new ArrayList<>();
    private int totalConsulations;
    private double totalFee;

    public ProviderReport(Provider provider, ArrayList<ProviderServiceSummary> providerServiceList,
                            int totalConsulations, double totalFee){
        this.provider = new Provider(provider);
        this.providerServiceList = new ArrayList<ProviderServiceSummary>(providerServiceList);
        this.totalConsulations = totalConsulations;
        this.totalFee = totalFee;
    }

    public Provider getProvider(){
        return new Provider(provider);
    }

    public ArrayList<ProviderServiceSummary> getProviderServiceList(){
        return new ArrayList<ProviderServiceSummary>(providerServiceList);
    }

    public int getTotalConsulations(){
        return totalConsulations;
    }

    public double getTotalFee(){
        return totalFee;
    }
}