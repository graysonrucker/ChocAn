public class Member {
    public static final int MEMBER_NUMBER_LENGTH = 9;
    public static final int MAX_NAME_LENGTH = 25;
    public static final int MAX_ADDRESS_LENGTH = 25;
    public static final int MAX_CITY_LENGTH = 14;
    public static final int STATE_LENGTH = 2;
    public static final int ZIP_CODE_LENGTH = 5;

    private String memberNumber; 
    private String name; 
    private String city; 
    private String address;
    private String zipCode;
    private String state;

        public Member(String memberNumber, String name, String city, 
        String address, String zipCode, String state){
            validateInput(memberNumber, name, city, address, zipCode, state);   
            this.memberNumber = memberNumber;
            this.name = name;
            this.city = city;
            this.address = address;
            this.zipCode = zipCode;
            this.state = state;
    }

    public String getMemberNumber(){
        return memberNumber;
    }

    public String getName(){
        return name;
    }

    public String getCity(){
        return city;
    }

    public String getAddress(){
        return address;
    }

    public String getZipCode(){
        return zipCode;
    }

    public String getState(){
        return state;
    }

    public void setMemberNumber(String memberNumber){
        isValidNumber(memberNumber, MEMBER_NUMBER_LENGTH);
        this.memberNumber = memberNumber;
    }

    public void setName(String name){
        isValidName(name, MAX_NAME_LENGTH);
        this.name = name;
    }

    public void setCity(String city){
        isValidCity(city, MAX_CITY_LENGTH);
        this.city = city;
    }

    public void setAddress(String address){
        isValidAddress(address, MAX_ADDRESS_LENGTH);
        this.address = address;
    }

    public void setZipCode(String zipCode){
        isValidZipCode(zipCode, ZIP_CODE_LENGTH);
        this.zipCode = zipCode;
    }

    public void setState(String state){
        isValidState(state, STATE_LENGTH);
        this.state = state;
    }

    private void validateInput(String memberNumber, String name, String city, 
        String address, String zipCode, String state){
            isValidNumber(memberNumber, MEMBER_NUMBER_LENGTH);
            isValidName(name, MAX_NAME_LENGTH);
            isValidCity(city, MAX_CITY_LENGTH);
            isValidAddress(address, MAX_ADDRESS_LENGTH);
            isValidZipCode(zipCode, ZIP_CODE_LENGTH);
            isValidState(state, STATE_LENGTH);
    }

    private void isValidNumber(String number, int requiredLength){
        if(number == null || number.length() != requiredLength
            || !number.chars().allMatch(Character::isDigit)){
                throw new IllegalArgumentException("Invalid member number.");
            }
    }

    private void isValidName(String name, int maxLength){
        if(name == null || name.length() > maxLength
            || !name.chars().allMatch(Character::isAlphabetic)){
            throw new IllegalArgumentException("Invalid member name.");
        }
    }

    private void isValidCity(String city, int maxLength){
        if(city == null || city.length() > maxLength
            || !city.chars().allMatch(Character::isAlphabetic)){
                throw new IllegalArgumentException("Invalid member city.");
        }
    }

    private void isValidAddress(String address, int maxLength){
        if(address == null || city.length() > maxLength){
                throw new IllegalArgumentException("Invalid member address");
        }
    }

    private void isValidZipCode(String zipCode, int requiredLength){
        if(zipCode == null || zipCode.length() > requiredLength
            || !name.chars().allMatch(Character::isDigit)){
            throw new IllegalArgumentException("Invalid member zipcode.");
        }
    }

    private void isValidState(String state, int requiredLength){
        if(zipCode == null || zipCode.length() != requiredLength
            || !name.chars().allMatch(Character::isAlphabetic)){
            throw new IllegalArgumentException("Invalid member state.");
        }
    }

}

