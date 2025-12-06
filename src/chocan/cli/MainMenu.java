package chocan.cli;
import chocan.model.*;
import chocan.report.*;
import chocan.service.ChocAnSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Owen Taylor
public class MainMenu {

    private final ChocAnSystem system;

    public MainMenu(ChocAnSystem system) 
    {
        this.system = system;
    }

    public void weeklyAccounting(){
        ArrayList<MemberReport> memberReportList = system.generateAllMemberReports();
        ArrayList<ProviderReport> providerReportList = system.generateAllProviderReports();
        SummaryReport summaryReport = system.generateSummaryReport();
        
        for(MemberReport report : memberReportList){
            System.out.println(report.getMember().getName());
            System.out.println(report.getMember().getMemberNumber());
            System.out.println(report.getMember().getAddress());
            System.out.println(report.getMember().getCity());
            System.out.println(report.getMember().getState());
            System.out.println(report.getMember().getZipCode());
            for(MemberServiceSummary summary : report.getMemberServiceList()){
                System.out.println("\t" + summary.getDateOfService() +  "\t");
                System.out.println("\t" + summary.getProviderName() + "\t");
                System.out.println("\t" + summary.getServiceName());
            }
        }
        System.out.println();
        for(ProviderReport report : providerReportList){
            System.out.println(report.getProvider().getName());
            System.out.println(report.getProvider().getProviderNumber());
            System.out.println(report.getProvider().getAddress());
            System.out.println(report.getProvider().getCity());
            System.out.println(report.getProvider().getState());
            System.out.println(report.getProvider().getZipCode());
            for(ProviderServiceSummary summary : report.getProviderServiceList()){
                System.out.println("\t Date serviced: " + summary.getServiceDate());
                System.out.println("\t Date and time received: " + summary.getDateTimeReceived());
                System.out.println("\t Member name: " + summary.getMemberName());
                System.out.println("\t Member number: " + summary.getMemberNumber());
                System.out.println("\t Service Code: " + summary.getServiceCode());
                System.out.println("\t Fee: $" + summary.getFee());
                System.out.println();
            }
        }
        System.out.println();
            ArrayList<Provider> providersToPay  = new ArrayList<Provider>(summaryReport.getProvidersToPay());
            ArrayList<Integer> providerServiceCount = new ArrayList<Integer>(summaryReport.getProviderServiceCount());
            ArrayList<Double> providerTotalFees = new ArrayList<Double>(summaryReport.getProviderTotalFees());
        for(int i = 0; i < providersToPay.size(); i++){
            System.out.println("Provider name: " + providersToPay.get(i).getName()
                                + "\t Total services: " + providerServiceCount.get(i)
                                + "\t Total fees: $" + providerTotalFees.get(i));
        }
        System.out.println("Total number of providers: " + summaryReport.getNumProviders());
        System.out.println("Total number of services: " + summaryReport.getNumServices());
        System.out.println("Fee for all providers: $" + summaryReport.getOverallFee());
    }

    public void start() throws IOException 
    {
        String command = "";
        while(command != "exit"){
            System.out.println("Clarify user type or print weekly accounting procedure");
            System.out.println("Operator (O), Provider (P), Manager (M), Weekly Accounting (A) (or <exit> to stop):");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            command = br.readLine();
            
            String username;
            String password;

            switch (command) {
                case "P":
                    System.out.println("Enter provider number");
                    String providerNumber = br.readLine();
                    if(system.verifyProvider(providerNumber)) {
                        ProviderMenu PMenu = new ProviderMenu(system);
                        PMenu.MenuStart();
                    }
                    else{
                        System.out.println("Invalid provider number");
                    }
                    break;
                case "O":
                    System.out.println("Enter username");
                    username = br.readLine();
                    System.out.println("Enter password");
                    password = br.readLine();
                    if(system.verifyOperator(username, password)) {
                        OperatorMenu OMenu = new OperatorMenu(system);
                        OMenu.MenuStart();
                    }
                    else {
                        System.out.println("Invalid credentials");
                    }
                    break;
                case "M":
                    System.out.println("Enter username: ");
                    username = br.readLine();
                    System.out.println("Enter password: ");
                    password = br.readLine();
                    if(system.verifyManager(username, password)) {
                        ManagerMenu MMenu = new ManagerMenu(system);
                        MMenu.start();
                    }
                    else {
                        System.out.println("Invalid credentials");
                    }
                    break;
                case "A":
                    weeklyAccounting();
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    break;
            }
        }
    }
}