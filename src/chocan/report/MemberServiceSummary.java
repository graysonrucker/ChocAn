package chocan.report;

public class MemberServiceSummary {
    private String dateOfService;
    private String providerName;
    private String serviceName;

    public MemberServiceSummary(String dateOfService, String providerName, String serviceName){
        this.dateOfService = dateOfService;
        this.providerName = providerName;
        this.serviceName = serviceName;
    }

    public String getDateOfService(){
        return dateOfService;
    }

    public String getProviderName(){
        return providerName;
    }

    public String getServiceName(){
        return serviceName;
    }
}
