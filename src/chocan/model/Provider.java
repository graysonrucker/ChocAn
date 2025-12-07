package chocan.model;
//Provider Class, stores and validates provider information
//done by Victoria Guest
public class Provider{
    public static final int PROVIDER_NUMBER_LENGTH = 9;
    public static final int MAX_NAME_LENGTH = 25;
    public static final int MAX_ADDRESS_LENGTH = 25;
    public static final int MAX_CITY_LENGTH = 14;
    public static final int STATE_LENGTH = 2;
    public static final int ZIP_CODE_LENGTH = 5;

    private String providerNumber;
    private String name;
    private String city;
    private String address;
    private String zipCode;
    private String state;

    public Provider(String providerNumber, String name, String city, 
        String address, String zipCode, String state){
            validateInput(providerNumber, name, city, address, zipCode, state);   
            this.providerNumber = providerNumber;
            this.name = name;
            this.city = city;
            this.address = address;
            this.zipCode = zipCode;
            this.state = state;
    }

    public Provider(Provider provider){
        this.providerNumber = provider.providerNumber;
        this.name = provider.name;
        this.city = provider.city;
        this.address = provider.address;
        this.zipCode = provider.zipCode;
        this.state = provider.state;
    }
    //returns the 9 digit provider number
    public String getProviderNumber(){
        return providerNumber;
    }
    //returns the name of the provider
    public String getName(){
        return name;
    }
    //returns the city of the provider
    public String getCity(){
        return city;
    }
    //returns the address of the provider
    public String getAddress(){
        return address;
    }
    //returns the zip code
    public String getZipCode(){
        return zipCode;
    }
    //reutrns the state
    public String getState(){
        return state;
    }
    //sets the provider number if it is valid
    public void setProviderNumber(String providerNumber){
        isValidNumber(providerNumber, PROVIDER_NUMBER_LENGTH);
        this.providerNumber = providerNumber;
    }
    //sets the name if it isn't too long
    public void setName(String name){
        isValidName(name, MAX_NAME_LENGTH);
        this.name = name;
    }
    //sets the city
    public void setCity(String city){
        isValidCity(city, MAX_CITY_LENGTH);
        this.city = city;
    }
    //sets the address
    public void setAddress(String address){
        isValidAddress(address, MAX_ADDRESS_LENGTH);
        this.address = address;
    }
    //sets the zipCode
    public void setZipCode(String zipCode){
        isValidZipCode(zipCode, ZIP_CODE_LENGTH);
        this.zipCode = zipCode;
    }
    //sets the State
    public void setState(String state){
        isValidState(state, STATE_LENGTH);
        this.state = state;
    }
    //different checks to see if input is the right length
    private void validateInput(String providerNumber, String name, String city, 
        String address, String zipCode, String state){
            isValidNumber(providerNumber, PROVIDER_NUMBER_LENGTH);
            isValidName(name, MAX_NAME_LENGTH);
            isValidCity(city, MAX_CITY_LENGTH);
            isValidAddress(address, MAX_ADDRESS_LENGTH);
            isValidZipCode(zipCode, ZIP_CODE_LENGTH);
            isValidState(state, STATE_LENGTH);
    }

    private void isValidNumber(String number, int requiredLength){
        if(number == null || number.length() != requiredLength
            || !number.chars().allMatch(Character::isDigit)){
                throw new IllegalArgumentException("Invalid provider number.");
            }
    }

    private void isValidName(String name, int maxLength){
        if(name == null || name.length() > maxLength){
            throw new IllegalArgumentException("Invalid provider name.");
        }
    }

    private void isValidCity(String city, int maxLength){
        if(city == null || city.length() > maxLength){
                throw new IllegalArgumentException("Invalid provider city.");
        }
    }

    private void isValidAddress(String address, int maxLength){
        if(address == null || address.length() > maxLength){
                throw new IllegalArgumentException("Invalid provider address");
        }
    }

    private void isValidZipCode(String zipCode, int requiredLength){
        if(zipCode == null || zipCode.length() != requiredLength
            || !zipCode.chars().allMatch(Character::isDigit)){
            throw new IllegalArgumentException("Invalid provider zipcode.");
        }
    }

    private void isValidState(String state, int requiredLength){
        if(state == null || state.length() != requiredLength){
            throw new IllegalArgumentException("Invalid provider state.");
        }
    }
}