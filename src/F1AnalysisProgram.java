import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Team {
    // Private attributes to store team information
    private String teamName;
    private String carCode;
    private String driverName;
    private String grandPrix;
    private int positionFinished;
    private double fastestLap;

    // Constructors to initialize team objects with provided information
    public Team(String teamName, String carCode, String driverName, String grandPrix, int positionFinished, double fastestLap) {
        this.teamName = teamName;
        this.carCode = carCode;
        this.driverName = driverName;
        this.grandPrix = grandPrix;
        this.positionFinished = positionFinished;
        this.fastestLap = fastestLap;
    }

    // Accessor methods (getters) to retrieve team information
    public String getTeamName() {
        return teamName;
    }

    public String getCarCode() {
        return carCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getGrandPrix() {
        return grandPrix;
    }

    public int getPositionFinished() {
        return positionFinished;
    }

    public double getFastestLap() {
        return fastestLap;
    }

    // Mutator methods (setters) to modify team information
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setGrandPrix(String grandPrix) {
        this.grandPrix = grandPrix;
    }

    public void setPositionFinished(int positionFinished) {
        this.positionFinished = positionFinished;
    }

    public void setFastestLap(double fastestLap) {
        this.fastestLap = fastestLap;
    }
}

public class F1AnalysisProgram {
    // Array to store teams data
    private static Team[] teams;

    // Counter to track the number of teams
    private static int teamCount = 0;

    // Main method to start the program
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Display welcome message
        System.out.println("Welcome to the FIA F1 Analysis Program");

        String filename;
        boolean fileReadSuccessfully = false;
        // Prompt user to enter filename until valid
        while (!fileReadSuccessfully) {
            System.out.print("Enter the name of the file containing the data: ");
            filename = scanner.nextLine();
            // Attempt to read data from CSV file
            fileReadSuccessfully = readDataFromCSV(filename);

            if (!fileReadSuccessfully) {
                System.out.println("Error reading file. Please try again.");
            }
        }

        boolean exitProgram = false;
        // Continue program until user chooses to exit
        while (!exitProgram) {
            System.out.print("An All Teams analysis or a Single Team analysis? (Type 'All' or 'Single'): ");
            String analysisType = scanner.nextLine().toLowerCase();

            switch (analysisType) {
                case "all":
                    performAllTeamsAnalysis();
                    break;
                case "single":
                    performSingleTeamAnalysis(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 'All' or 'Single'.");
            }

            System.out.print("Would you like to exit? (yes/no): ");
            String exitChoice = scanner.nextLine().toLowerCase();
            if (exitChoice.equals("yes")) {
                exitProgram = true;
            }
        }

        scanner.close();
    }


    // Method to read data from CSV file
    private static boolean readDataFromCSV(String filename) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Counting the number of lines in the file
            int lineCount = 0;
            while (br.readLine() != null) {
                lineCount++;
            }
            teams = new Team[lineCount]; // Initializing the array with the correct size
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return false; 
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skip header line to make sure only get data
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Team team = new Team(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Double.parseDouble(data[5]));
                teams[teamCount++] = team; // Adding team to the array
            }
            return true; 
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return false; 
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false; 
        }
    }
    // Method to perform analysis on all teams
    private static void performAllTeamsAnalysis() {
    // Display teams that completed the race
    System.out.println("\n===============================");
    System.out.println("Teams that completed the race:");
    System.out.println("===============================");
    listCompletedTeams();

    // Display teams that did not complete the race
    System.out.println("\n=================================");
    System.out.println("Teams that did not complete the race:");
    System.out.println("=================================");
    listIncompleteTeams();

    // Display fastest team and combined fastest time
    System.out.println("\n===============================");
    System.out.println("Fastest Team:");
    System.out.println("===============================");
    calculateFastestTeam();

    // Display teams sorted by fastest lap time (Descending)
    System.out.println("\n===============================================");
    System.out.println("Teams sorted by fastest lap time (Descending):");
    System.out.println("===============================================");
    sortTeamsByFastestLap();

    // Display drivers sorted by fastest lap time (Descending)
    System.out.println("\n===============================================");
    System.out.println("Drivers sorted by fastest lap time (Descending):");
    System.out.println("===============================================");
    sortDriversByFastestLapDescending();

    // Display drivers sorted by fastest lap time (Ascending)
    System.out.println("\n===============================================");
    System.out.println("Drivers sorted by fastest lap time (Ascending):");
    System.out.println("===============================================");
    sortDriversByFastestLapAscending();
}


    // Method to list completed teams
    private static void listCompletedTeams() {
    for (Team team : teams) {
        if (team != null && team.getPositionFinished() > 0) {
            System.out.println(team.getTeamName());
        }
    }
    }

    // Method to list incomplete teams
    private static void listIncompleteTeams() {
        for (Team team : teams) {
            if (team != null && team.getPositionFinished() == -1) {
                System.out.println(team.getTeamName());
            }
        }
    }

    // Method to calculate fastest team
    private static void calculateFastestTeam() {
    String fastestTeamName = "";
    double fastestCombinedTime = Double.MAX_VALUE;

    for (Team team : teams) {
        if (team == null) {
            continue; // Skip null elements
        }

        double combinedTime = 0.0;
        for (int i = 0; i < 2; i++) {
            // If the driver did not finish the race, their time is set to 205.50 seconds
            double driverFastestLap = team.getFastestLap();
            if (team.getPositionFinished() == -1) {
                driverFastestLap = 205.50;
            }
            combinedTime += driverFastestLap;
        }
        if (combinedTime < fastestCombinedTime) {
            fastestCombinedTime = combinedTime;
            fastestTeamName = team.getTeamName();
        }
    }

    System.out.println("Fastest Team: " + fastestTeamName);
    System.out.println("Combined Fastest Time: " + fastestCombinedTime + " seconds");
}



    // Method to sort teams by fastest lap time
    private static void sortTeamsByFastestLap() {
    for (int i = 0; i < teamCount; i++) {
        if (teams[i] == null) {
            continue; // Skip null elements
        }
        for (int j = i + 1; j < teamCount; j++) {
            if (teams[j] == null) {
                continue; // Skip null elements
            }
            if (teams[i].getFastestLap() < teams[j].getFastestLap()) {
                Team temp = teams[i];
                teams[i] = teams[j];
                teams[j] = temp;
            }
        }
    }
    for (int i = 0; i < teamCount; i++) {
        if (teams[i] != null) {
            System.out.println(teams[i].getTeamName() + ": " + teams[i].getFastestLap());
        }
    }
    }


    // Method to sort drivers by fastest lap time in descending order
    private static void sortDriversByFastestLapDescending() {
        String[] allDrivers = new String[teamCount];
        double[] fastestLaps = new double[teamCount];
        int count = 0;

        // Populate allDrivers and fastestLaps arrays
        for (int i = 0; i < teamCount; i++) {
            Team team = teams[i];
            if (team != null && team.getPositionFinished() > 0) { // Check for null reference
                allDrivers[count] = team.getDriverName();
                fastestLaps[count] = getFastestDriverLap(team.getDriverName());
                count++;
            }
        }

        // Sorting the arrays in descending order based on fastest lap time
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (fastestLaps[j] > fastestLaps[i]) {
                    // Swap driver names
                    String tempDriver = allDrivers[i];
                    allDrivers[i] = allDrivers[j];
                    allDrivers[j] = tempDriver;

                    // Swap fastest lap times
                    double tempLap = fastestLaps[i];
                    fastestLaps[i] = fastestLaps[j];
                    fastestLaps[j] = tempLap;
                }
            }
        }

        // Printing the sorted list
        System.out.println("Drivers sorted by fastest lap time (descending):");
        for (int i = 0; i < count; i++) {
            System.out.println(allDrivers[i] + ": " + fastestLaps[i]);
        }
    }


    // Method to get fastest lap time for a specific driver
    private static double getFastestDriverLap(String driverName) {
    double fastestLap = Double.MAX_VALUE;
    for (Team team : teams) {
        if (team != null && team.getDriverName().equalsIgnoreCase(driverName)) {
            fastestLap = Math.min(fastestLap, team.getFastestLap());
        }
    }
    return fastestLap;
    }


    // Method to sort drivers by fastest lap time in ascending order
    private static void sortDriversByFastestLapAscending() {
    String[] allDrivers = new String[teamCount];
    double[] fastestLaps = new double[teamCount];
    int count = 0;

    // Populate allDrivers and fastestLaps arrays
    for (int i = 0; i < teamCount; i++) {
        Team team = teams[i];
        if (team.getPositionFinished() > 0) { // Only consider drivers who completed the race
            allDrivers[count] = team.getDriverName();
            fastestLaps[count] = getFastestDriverLap(team.getDriverName());
            count++;
        }
    }

    // Sorting the arrays in ascending order based on fastest lap time
    for (int i = 0; i < count - 1; i++) {
        for (int j = i + 1; j < count; j++) {
            if (fastestLaps[j] < fastestLaps[i]) {
                // Swap driver names
                String tempDriver = allDrivers[i];
                allDrivers[i] = allDrivers[j];
                allDrivers[j] = tempDriver;

                // Swap fastest lap times
                double tempLap = fastestLaps[i];
                fastestLaps[i] = fastestLaps[j];
                fastestLaps[j] = tempLap;
            }
        }
    }

    // Printing the sorted list
    for (int i = 0; i < count; i++) {
        System.out.println(allDrivers[i] + ": " + fastestLaps[i]);
    }
}


    // Method to perform analysis on a single team
    private static void performSingleTeamAnalysis(Scanner scanner) {
        System.out.print("Which Team? ");
        String teamName = scanner.nextLine();
        System.out.println("\nAnalysis for Team '" + teamName + "':");
        System.out.print("Filter teams based on Car Code? (yes/no): ");
        String filterChoice = scanner.nextLine().toLowerCase();

        if (filterChoice.equals("yes")) {
            System.out.print("Enter Car Code: ");
            String carCode = scanner.nextLine();
            filterTeamsByCarCode(teamName, carCode);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    // Method to filter teams by car code
    private static void filterTeamsByCarCode(String teamName, String carCode) {
    String[] drivers = new String[teamCount];
    int count = 0;
    for (int i = 0; i < teamCount; i++) {
        Team team = teams[i];
        if (team.getTeamName().equalsIgnoreCase(teamName) && team.getCarCode().equalsIgnoreCase(carCode)) {
            drivers[count++] = team.getDriverName();
        }
    }

    if (count == 0) {
        System.out.println("No drivers found for the specified team and car code.");
    } else {
        System.out.println("Drivers for Team '" + teamName + "' with Car Code '" + carCode + "', fastest driver listed first:");
        // Sort the drivers array
        Arrays.sort(drivers, 0, count, String.CASE_INSENSITIVE_ORDER);
        // Print the sorted list
        for (int i = 0; i < count; i++) {
            System.out.println(drivers[i]);
        }
    }
    }

}

