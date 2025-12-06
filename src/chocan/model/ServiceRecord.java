package chocan.model;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class ServiceRecord {
    private static final DateTimeFormatter DATE_TIME_FMT =
            DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    private static final DateTimeFormatter DATE_FMT =
        DateTimeFormatter.ofPattern("MM-dd-yyyy");
    
    private static final int SERVICE_CODE_LENGTH = 6;

    private LocalDateTime currDateAndTime;
    private LocalDate dateOfService;
    private String providerNumber;
    private String memberNumber;
    private String serviceCode;
    private String comment; //optional

    public ServiceRecord(String currDateAndTime, String dateOfService, String providerNumber,
                                String memberNumber, String serviceCode, String comment){
        isValidServiceCode(serviceCode, SERVICE_CODE_LENGTH);
        this.currDateAndTime = LocalDateTime.parse(currDateAndTime, DATE_TIME_FMT);
        this.dateOfService = LocalDate.parse(dateOfService, DATE_FMT);
        this.providerNumber = providerNumber;
        this.memberNumber = memberNumber;
        this.serviceCode = serviceCode;
        this.comment = comment;
    }

    public String getCurrDateAndTime(){
        return currDateAndTime.format(DATE_TIME_FMT);
    }

    public String getDateOfService(){
        return dateOfService.format(DATE_FMT);
    }

    public String getProviderNumber(){
        return providerNumber;
    }

    public String getMemberNumber(){
        return memberNumber;
    }

    public String getServiceCode(){
        return serviceCode;
    }

    public String getComment(){
        return comment;
    }

    public void setCurrDateAndTime(String currDateAndTime){
        this.currDateAndTime = LocalDateTime.parse(currDateAndTime, DATE_TIME_FMT);
    }

    public void setDateOfService(String dateOfService){
        this.dateOfService = LocalDate.parse(dateOfService, DATE_FMT);
    }

    public void setProviderNumber(String providerNumber){
        this.providerNumber = providerNumber;
    }

    public void setMemberNumber(String memberNumber){
        this.memberNumber = memberNumber;
    }

    public void setServiceCode(String serviceCode){
        isValidServiceCode(serviceCode, SERVICE_CODE_LENGTH);
        this.serviceCode = serviceCode;
    }

    private void isValidServiceCode(String serviceCode, int requiredLength){
        if(serviceCode == null || serviceCode.length() != requiredLength
            || !serviceCode.chars().allMatch(Character::isDigit)){
                throw new IllegalArgumentException("Invalid service code.");
            }
    }
}
