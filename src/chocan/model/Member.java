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
            if(isValidInput(memberNumber, name, city, address, zipCode, state)){          
            this.memberNumber = memberNumber;
            this.name = name;
            this.city = city;
            this.address = address;
            this.zipCode = zipCode;
            this.state = state;
        }
    }

    public String getMemberNumber(){
        return memberNumber;
    }

    public String getName(){
        return name;
    }

    private boolean isValidInput(String memberNumber, String name, String city, 
        String address, String zipCode, String state){
            if(isValidNumber(memberNumber, MEMBER_NUMBER_LENGTH) && isValidName(name, MAX_NAME_LENGTH) 
                    && isValidCity(city, MAX_CITY_LENGTH) && isValidAddress(address, MAX_ADDRESS_LENGTH) 
                    && isValidZipCode(zipCode, ZIP_CODE_LENGTH) && isValidState(state, STATE_LENGTH)){
            return true;
        }
    }

    private boolean isValidNumber(String number, int requiredLength){
        if(number != null && number.length() == requiredLength
            && number.chars().allMatch(Character::isDigit)){
                return true;
            }
        throw new IllegalArgumentException("Invalid member number.");
    }

    private boolean isValidName(String name, int maxLength){
        if(name != null && name.length() == maxLength
            && name.chars().allMatch(Character::isAlphabetic)){
                return true;
            }
        throw new IllegalArgumentException("Invalid member name.");
    }
}