package chocan.model;

import java.util.Map;

public final class ProviderDirectory {

    public static class Service {

        private final String code;
        private final String name;
        private final double fee; 

        public Service(String code, String name, double fee) {
            this.code = code;
            this.name = name;
            this.fee = fee;
        }

        public String getCode(){
            return code;
        }

        public String getName(){
            return name;
        }

        public Double getFee(){
            return fee;
        }
    }

    private static final Map<String, Service> SERVICES = Map.of(
        "123456", new Service("123456", "Physical Therapy", 150.00),
        "234567", new Service("234567", "Dental", 90.00)
    );

    public static boolean hasService(String serviceCode){
        return SERVICES.containsKey(serviceCode);
    }

    public static String getCodeByName(String name){
    for (Service s : SERVICES.values()){
        if (s.getName().equalsIgnoreCase(name)){
            return s.getCode();
        }
    }
        return null;
    }
}
