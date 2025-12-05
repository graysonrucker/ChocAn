package chocan.report;

import java.util.ArrayList;
import java.util.List;

import chocan.model.Provider;

public class SummaryReport{
    private ArrayList<Provider> providersToPay = new ArrayList<>();
    private ArrayList<Integer> providerServiceCount = new ArrayList<>();
    private ArrayList<Double> providerTotalFees = new ArrayList<>();
    private int numProviders;
    private int numServices;
    private double overallFee;

    public SummaryReport(ArrayList<Provider> providersToPay, ArrayList<Integer> providerServiceCount,
                                ArrayList<Double> providerTotalFees, int numProviders, 
                                int numServices, double overallFee){
        this.providersToPay = new ArrayList<Provider>(providersToPay);
        this.providerServiceCount = new ArrayList<Integer>(providerServiceCount);
        this.providerTotalFees = new ArrayList<>(providerTotalFees);
        this.numProviders = numProviders;
        this.numServices = numServices;
        this.overallFee = overallFee;
    }

    public List<Provider> getProvidersToPay() {
        return new ArrayList<>(providersToPay);
    }

    public List<Integer> getProviderServiceCount() {
        return new ArrayList<>(providerServiceCount);
    }

    public List<Double> getProviderTotalFees() {
        return new ArrayList<>(providerTotalFees);
    }

    public int getNumProviders() {
        return numProviders;
    }

    public int getNumServices() {
        return numServices;
    }

    public double getOverallFee() {
        return overallFee;
    }

}