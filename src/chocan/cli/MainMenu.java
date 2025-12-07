package chocan.cli;
import chocan.model.*;
import chocan.report.*;
import chocan.service.ChocAnSystem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//Owen Taylor
public class MainMenu {

    private final ChocAnSystem system;

    public MainMenu(ChocAnSystem system) 
    {
        this.system = system;
    }

    public void weeklyAccounting() {
        ArrayList<MemberReport> memberReportList = system.generateAllMemberReports();
        ArrayList<ProviderReport> providerReportList = system.generateAllProviderReports();
        SummaryReport summaryReport = system.generateSummaryReport();

        Path memberDir = Paths.get("data", "reports", "member");
        Path providerDir = Paths.get("data", "reports", "provider");
        Path summaryDir = Paths.get("data", "reports", "summary");

        try {
            Files.createDirectories(memberDir);
            Files.createDirectories(providerDir);
            Files.createDirectories(summaryDir);

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
                writeMemberReport(memberDir, report);
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
                writeProviderReport(providerDir, report);
            }
            System.out.println();

            writeSummaryReport(summaryDir, summaryReport);
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
        } catch(IOException e) {e.printStackTrace();}
    }

    public void start() throws IOException 
    {
        String command = "";
        String exit = "exit";
        while(!command.equals(exit)){
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
                        ProviderMenu PMenu = new ProviderMenu(system, providerNumber);
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
                    if(!command.equals(exit)){
                        System.out.println("Unknown command: " + command);
                    }
                    break;
            }
        }
        system.saveData("data/");
    }

    private void writeMemberReport(Path reportsDir, MemberReport report) throws IOException {
        String memberNumber = report.getMember().getMemberNumber();
        String memberName = report.getMember().getName();

        String fileName = "member_" + memberNumber + "_" + memberName + ".txt";
        Path file = reportsDir.resolve(fileName);

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {

            // Header section
            writer.println("Member Report");
            writer.println("==========================");
            writer.println("Member Name:     " + report.getMember().getName());
            writer.println("Member Number:   " + report.getMember().getMemberNumber());
            writer.println("Address:         " + report.getMember().getAddress());
            writer.println("City:            " + report.getMember().getCity());
            writer.println("State:           " + report.getMember().getState());
            writer.println("ZIP Code:        " + report.getMember().getZipCode());
            writer.println();
            writer.println("Services Received:");
            writer.println("--------------------------");
            writer.println();

            // Service summary list
            for (MemberServiceSummary summary : report.getMemberServiceList()) {
                writer.println("Date of Service:   " + summary.getDateOfService());
                writer.println("Provider Name:     " + summary.getProviderName());
                writer.println("Service Name:      " + summary.getServiceName());
                writer.println();
            }

            // Footer
            writer.println("==========================");
            writer.println("End of Member Report");
        }
    }

    private void writeProviderReport(Path reportsDir, ProviderReport report) throws IOException {
        String providerNumber = report.getProvider().getProviderNumber();
        String providerName = report.getProvider().getName();

        String fileName = "provider_" + providerNumber + "_" + providerName + ".txt";
        Path file = reportsDir.resolve(fileName);

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {

            // Header section
            writer.println("Provider Report");
            writer.println("==========================");
            writer.println("Provider Name:   " + report.getProvider().getName());
            writer.println("Provider Number: " + report.getProvider().getProviderNumber());
            writer.println("Address:         " + report.getProvider().getAddress());
            writer.println("City:            " + report.getProvider().getCity());
            writer.println("State:           " + report.getProvider().getState());
            writer.println("ZIP Code:        " + report.getProvider().getZipCode());
            writer.println();
            writer.println("Services Provided:");
            writer.println("--------------------------");
            writer.println();

            // Service summary list
            for (ProviderServiceSummary summary : report.getProviderServiceList()) {
                writer.println("Date of Service:        " + summary.getServiceDate());
                writer.println("Date/Time Received:     " + summary.getDateTimeReceived());
                writer.println("Member Name:            " + summary.getMemberName());
                writer.println("Member Number:          " + summary.getMemberNumber());
                writer.println("Service Code:           " + summary.getServiceCode());
                writer.println("Fee:                    $" + summary.getFee());
                writer.println();
            }

            // Footer
            writer.println("==========================");
            writer.println("End of Provider Report");
        }
    }
    private void writeSummaryReport(Path reportsDir, SummaryReport summaryReport) throws IOException {
        String fileName = "summary_weekly.txt";
        Path file = reportsDir.resolve(fileName);

        ArrayList<Provider> providersToPay = new ArrayList<>(summaryReport.getProvidersToPay());
        ArrayList<Integer> providerServiceCount = new ArrayList<>(summaryReport.getProviderServiceCount());
        ArrayList<Double> providerTotalFees = new ArrayList<>(summaryReport.getProviderTotalFees());

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {

            writer.println("Weekly Summary Report");
            writer.println("=============================");
            writer.println();

            for (int i = 0; i < providersToPay.size(); i++) {
                Provider p = providersToPay.get(i);

                writer.println("Provider Name:      " + p.getName());
                writer.println("Provider Number:    " + p.getProviderNumber());
                writer.println("Total Services:     " + providerServiceCount.get(i));
                writer.println("Total Fees:         $" + providerTotalFees.get(i));
                writer.println();
            }

            writer.println("-----------------------------");
            writer.println("Total Providers: " + summaryReport.getNumProviders());
            writer.println("Total Services:  " + summaryReport.getNumServices());
            writer.println("Overall Fees:    $" + summaryReport.getOverallFee());
            writer.println("-----------------------------");
            writer.println();
            writer.println("End of Summary Report");
        }
    }
}