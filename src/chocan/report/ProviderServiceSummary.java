package chocan.report;
//Brooke Shaw
public class ProviderServiceSummary{
    private String serviceDate;
    private String dateTimeReceived;
    private String memberName;
    private String memberNumber;
    private String serviceCode;
    private double fee;

    public ProviderServiceSummary(String serviceDate, String dateTimeReceived, String memberName,
            String memberNumber, String serviceCode, double fee){
        this.serviceDate = serviceDate;
        this.dateTimeReceived = dateTimeReceived;
        this.memberName = memberName;
        this.memberNumber = memberNumber;
        this.serviceCode = serviceCode;
        this.fee = fee;
    }

    public String getServiceDate(){
        return serviceDate;
    }

    public String getDateTimeReceived(){
        return dateTimeReceived;
    }

    public String getMemberName(){
        return memberName;
    }

    public String getMemberNumber(){
        return memberNumber;
    }

    public String getServiceCode(){
        return serviceCode;
    }

    public double getFee(){
        return fee;
    }
}
