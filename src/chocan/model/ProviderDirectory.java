package chocan.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
//Tyler Druskoff
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

    private static final Map<String, Service> SERVICES = Map.ofEntries(
    Map.entry("123456", new Service("123456", "Physical Therapy", 150.00)),
    Map.entry("234567", new Service("234567", "Dental", 90.00)),
    Map.entry("345678", new Service("345678", "Chiropractic Adjustment", 110.00)),
    Map.entry("456789", new Service("456789", "Vision Exam", 75.00)),
    Map.entry("567890", new Service("567890", "Massage Therapy", 95.00)),
    Map.entry("678901", new Service("678901", "Nutritional Counseling", 85.00)),
    Map.entry("789012", new Service("789012", "Pediatric Checkup", 120.00)),
    Map.entry("890123", new Service("890123", "General Consultation", 60.00)),
    Map.entry("901234", new Service("901234", "Dermatology Screening", 180.00)),
    Map.entry("112233", new Service("112233", "Mental Health Counseling", 140.00)),
    Map.entry("223344", new Service("223344", "Allergy Testing", 160.00)),
    Map.entry("334455", new Service("334455", "Lab Work Panel", 130.00))
    );
    public static boolean hasService(String serviceCode){
        return SERVICES.containsKey(serviceCode);
    }

    public static String getNameByCode(String code){
        return SERVICES.get(code).getName();
    }

    public static double getFeeByCode(String code){
        return SERVICES.get(code).getFee();
    }

    public static String getCodeByName(String name){
        for (Service s : SERVICES.values()){
            if (s.getName().equalsIgnoreCase(name)){
                return s.getCode();
            }
        }
        return null;
    }

    public static ArrayList<Service> getAllServices(){
        return new ArrayList<>(SERVICES.values());
    }

    public static void writeProviderDirectory(Path directory) throws IOException{
        String fileName = "provider_directory.txt";
        Path file = directory.resolve(fileName);
        ArrayList<Service> services = getAllServices();
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
            for(Service service : services){
                writer.println(service.getCode() + "\t" + service.getName() + "\t" + service.getFee());
            }
        }
    }
}
