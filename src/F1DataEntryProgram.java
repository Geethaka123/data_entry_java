import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class F1DataEntryProgram {

    // Method to write headers to CSV file
    private static void writeHeaders(PrintWriter writer) {
        writer.println("TeamName,CarCode,DriverName,GrandPrix,PositionFinished,FastestLap");
    }

    // Method to get the file name from user input
    private static String getFileName(Scanner scanner) {
        System.out.print("What would you like to name your CSV file? ");
        return scanner.nextLine().trim() + ".csv";
    }

    // Method to validate if a string is non-empty
    private static boolean isValidString(String value) {
        return !value.isEmpty();
    }

    // Method to validate if a string can be parsed as a non-negative double
    private static boolean isValidDouble(String value) {
        try {
            double number = Double.parseDouble(value);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to validate if a string can be parsed as an integer
    private static boolean isValidInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the FIA F1 Data Entry Program");

        // Prompt for the number of F1 Teams
        int numTeams = 0;
        while (true) {
            System.out.print("How many F1 Teams are there? ");
            String input = scanner.nextLine().trim();
            if (isValidInteger(input)) {
                numTeams = Integer.parseInt(input);
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        // Arrays to store team data
        String[] teamNames = new String[numTeams];
        String[] carCodes = new String[numTeams];
        String[] driverNames = new String[numTeams];
        String[] grandPrixs = new String[numTeams];
        int[] positionsFinished = new int[numTeams];
        double[] fastestLaps = new double[numTeams];

        // Enter F1 Team data
        for (int i = 0; i < numTeams; i++) {
            System.out.println("Enter the data for Team " + (i + 1) + ":");
            System.out.print("Team Name: ");
            teamNames[i] = scanner.nextLine().trim();
            while (!isValidString(teamNames[i])) {
                System.out.println("Team Name cannot be empty. Please enter again:");
                System.out.print("Team Name: ");
                teamNames[i] = scanner.nextLine().trim();
            }
            System.out.print("Car Code: ");
            carCodes[i] = scanner.nextLine().trim();
            while (!isValidString(carCodes[i])) {
                System.out.println("Car Code cannot be empty. Please enter again:");
                System.out.print("Car Code: ");
                carCodes[i] = scanner.nextLine().trim();
            }
            System.out.print("Driver Name: ");
            driverNames[i] = scanner.nextLine().trim();
            while (!isValidString(driverNames[i])) {
                System.out.println("Driver Name cannot be empty. Please enter again:");
                System.out.print("Driver Name: ");
                driverNames[i] = scanner.nextLine().trim();
            }
            System.out.print("Grand Prix: ");
            grandPrixs[i] = scanner.nextLine().trim();
            while (!isValidString(grandPrixs[i])) {
                System.out.println("Grand Prix cannot be empty. Please enter again:");
                System.out.print("Grand Prix: ");
                grandPrixs[i] = scanner.nextLine().trim();
            }

            // Validate Position Finished
            while (true) {
                System.out.print("Position Finished: ");
                String position = scanner.nextLine().trim();
                if (isValidInteger(position)) {
                    positionsFinished[i] = Integer.parseInt(position);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer for Position Finished.");
                }
            }

            // Validate Fastest Lap
            while (true) {
                System.out.print("Fastest Lap: ");
                String fastestLap = scanner.nextLine().trim();
                if (isValidDouble(fastestLap)) {
                    fastestLaps[i] = Double.parseDouble(fastestLap);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a non-negative double for Fastest Lap.");
                }
            }
        }

        // Display current data
        System.out.println("The current data looks like this:");
        for (int i = 0; i < numTeams; i++) {
            System.out.printf("%s,%s,%s,%s,%d,%.3f%n", teamNames[i], carCodes[i], driverNames[i], grandPrixs[i], positionsFinished[i], fastestLaps[i]);
        }

        // Prompt if user wants to enter more data
        while (true) {
            System.out.print("Would you like to enter more data (Y or N)? ");
            String moreDataOption = scanner.nextLine().trim();
            if (moreDataOption.equalsIgnoreCase("Y")) {
                System.out.println("Enter the data for the additional Team:");
                System.out.print("Team Name: ");
                String teamName = scanner.nextLine().trim();
                while (!isValidString(teamName)) {
                    System.out.println("Team Name cannot be empty. Please enter again:");
                    System.out.print("Team Name: ");
                    teamName = scanner.nextLine().trim();
                }
                System.out.print("Car Code: ");
                String carCode = scanner.nextLine().trim();
                while (!isValidString(carCode)) {
                    System.out.println("Car Code cannot be empty. Please enter again:");
                    System.out.print("Car Code: ");
                    carCode = scanner.nextLine().trim();
                }
                System.out.print("Driver Name: ");
                String driverName = scanner.nextLine().trim();
                while (!isValidString(driverName)) {
                    System.out.println("Driver Name cannot be empty. Please enter again:");
                    System.out.print("Driver Name: ");
                    driverName = scanner.nextLine().trim();
                }
                System.out.print("Grand Prix: ");
                String grandPrix = scanner.nextLine().trim();
                while (!isValidString(grandPrix)) {
                    System.out.println("Grand Prix cannot be empty. Please enter again:");
                    System.out.print("Grand Prix: ");
                    grandPrix = scanner.nextLine().trim();
                }

                // Validate Position Finished
                int positionFinished = 0;
                while (true) {
                    System.out.print("Position Finished: ");
                    String position = scanner.nextLine().trim();
                    if (isValidInteger(position)) {
                        positionFinished = Integer.parseInt(position);
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter an integer for Position Finished.");
                    }
                }

                // Validate Fastest Lap
                double fastestLap = 0.0;
                while (true) {
                    System.out.print("Fastest Lap: ");
                    String fastestLapInput = scanner.nextLine().trim();
                    if (isValidDouble(fastestLapInput)) {
                        fastestLap = Double.parseDouble(fastestLapInput);
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a non-negative double for Fastest Lap.");
                    }
                }

                // Expand arrays to accommodate new data
                String[] newTeamNames = new String[numTeams + 1];
                String[] newCarCodes = new String[numTeams + 1];
                String[] newDriverNames = new String[numTeams + 1];
                String[] newGrandPrixs = new String[numTeams + 1];
                int[] newPosFinished = new int[numTeams + 1];
                double[] newFastestLaps = new double[numTeams + 1];

                // Copy existing data to new arrays
                System.arraycopy(teamNames, 0, newTeamNames, 0, numTeams);
                System.arraycopy(carCodes, 0, newCarCodes, 0, numTeams);
                System.arraycopy(driverNames, 0, newDriverNames, 0, numTeams);
                System.arraycopy(grandPrixs, 0, newGrandPrixs, 0, numTeams);
                System.arraycopy(positionsFinished, 0, newPosFinished, 0, numTeams);
                System.arraycopy(fastestLaps, 0, newFastestLaps, 0, numTeams);

                // Add new data
                newTeamNames[numTeams] = teamName;
                newCarCodes[numTeams] = carCode;
                newDriverNames[numTeams] = driverName;
                newGrandPrixs[numTeams] = grandPrix;
                newPosFinished[numTeams] = positionFinished;
                newFastestLaps[numTeams] = fastestLap;

                // Update arrays with new data
                teamNames = newTeamNames;
                carCodes = newCarCodes;
                driverNames = newDriverNames;
                grandPrixs = newGrandPrixs;
                positionsFinished = newPosFinished;
                fastestLaps = newFastestLaps;

                numTeams++; // Increment the number of teams

                // Display current data
                System.out.println("The current data looks like this:");
                for (int i = 0; i < numTeams; i++) {
                    System.out.printf("%s,%s,%s,%s,%d,%.3f%n", teamNames[i], carCodes[i], driverNames[i], grandPrixs[i], positionsFinished[i], fastestLaps[i]);
                }
            } else if (moreDataOption.equalsIgnoreCase("N")) {
                // Write data to CSV file
                try (PrintWriter writer = new PrintWriter(new FileWriter(getFileName(scanner)))) {
                    writeHeaders(writer);

                    for (int i = 0; i < numTeams; i++) {
                        writer.printf("%s,%s,%s,%s,%d,%.3f%n", teamNames[i], carCodes[i], driverNames[i], grandPrixs[i], positionsFinished[i], fastestLaps[i]);
                    }

                    System.out.println("Data has been successfully stored in the CSV file.");
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                break;
            } else {
                System.out.println("Invalid option. Please enter Y or N.");
            }
        }
    }
}
