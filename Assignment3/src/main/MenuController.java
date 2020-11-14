import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is the top level class for the project.
 * It implements a console-based Gym Management System.
 * The application communicates via input/output from the terminal.
 *
 * The class uses an instance of the GymAPI class,Scanner,Member and Trainer.
 * A switch statement is used to create a menu for the user to navigate
 * the different sub-menus. The user can exit or return to previous menus
 * (depending on the current menu) by pressing 0.
 * The class also contains a LinkedHashMap of ChosenPackage, and an instance of Trainer
 * and Member classes.
 *
 * @author Sean Crowley
 * @version 1.0
 */
public class MenuController {
    public GymAPI gymAPI;
    public LinkedHashMap<String, String> chosenPackage;
    public Scanner input = new Scanner(System.in);
    private Member member;
    private Trainer trainer;

    /**
     * Menu Controller constructor creates an instance of the gymApi,
     * chosenPackage HashMap and the method for filling the map.
     */
    public MenuController()
    {
        gymAPI = new GymAPI();
        chosenPackage = new LinkedHashMap<String, String>();
        fillChosenPackage();
    }

    public static void main(String[] args)
    {
        MenuController app = new MenuController();
        app.runMainMenu();
    }

    /**
     *  The mainMenu is the first menu that is displayed upon starting the
     *  application. It reads the option entered by the user and returns it.
     *  Here the user can Login to their account or register.
     * @return users choice
     */
    private int mainMenu()
    {
        System.out.println("Welcome to the PlayGym application.");
        System.out.println("======================================");
        System.out.println("Login to your account by pressing ===========> (1)");
        System.out.println("Dont have an account? Register by pressing ==> (2)");
        System.out.println("To Exit press ===============================> (0)");
        System.out.println("===>>");
        return input.nextInt();
    }

    /**
     * The login menu asks the user to login as either a member or a trainer.
     * It reads the option entered by the user and returns it.
     * @return The users input
     */
    private int loginMenu()
    {
        System.out.println("Login Menu.");
        System.out.println("======================================");
        System.out.println("Login as a Member ===> (1)");
        System.out.println("Login as a Trainer ==> (2)");
        System.out.println("Return to Main Menu => (0)");
        System.out.println("======================================>>");
        return input.nextInt();
    }

    /**
     * The register menu asks the user to register as a member or a trainer.
     * It also as an option for viewing the package details.
     * It reads the option entered by the user and returns it.
     * @return the users input
     */
    private int registerMenu()
    {
        System.out.println("Registration Menu.");
        System.out.println("======================================");
        System.out.println("Register as a new Member => (1)");
        System.out.println("Register as a Trainer ====> (2)");
        System.out.println("View Package Details =====> (3)");
        System.out.println("Return to Main Menu ======> (0)");
        System.out.println("===>>");
        return input.nextInt();
    }

    /**
     * The member menu has two options as well as an option to enter a sub-menu.
     * Here you can view the profile of the member or update the profiles information.
     * It reads the option entered by the user and returns it.
     * @return
     */
    private int memberMenu()
    {
        System.out.println("Member Options:");
        System.out.println("======================");
        System.out.println("View your Profile =======> (1)");
        System.out.println("Update your Profile =====> (2)");
        System.out.println("Progress menu ===========> (3)");
        System.out.println("Exit ====================> (4)");
        System.out.println("===>>");
        return input.nextInt();
    }

    /**
     *  The trainer menu can add a new member, list all Members in the gym
     *  or search for a members information by email. It can also access the
     *  Assessment sub-menu.
     *  It reads the option entered by the user and returns it.
     * @return the users input
     */
    private int trainerMenu()
    {
        System.out.println("Trainer Options:");
        System.out.println("=====================");
        System.out.println("Add a new Member ===========> (1)");
        System.out.println("List all Members ===========> (2)");
        System.out.println("Search for Member by email => (3)");
        System.out.println("Assessment menu ============> (4)");
        System.out.println("Exit =======================> (5)");
        System.out.println("===>>");
        return input.nextInt();
    }

    /**
     * The assessment menu allows a trainer to add an assessment or update a comment
     * on an existing assessment.
     * It reads the option entered by the user and returns it.
     * @return the users input
     */
    private int assessmentMenu()
    {
        System.out.println("Assessment Menu:");
        System.out.println("=====================");
        System.out.println("Add an Assessment for a Member ===> (1)");
        System.out.println("Update comment on an Assessment ==> (2)");
        System.out.println("Return to Previous Menu ==========> (0)");
        System.out.println("===>>");
        return input.nextInt();
    }

    /**
     * The progress menu is accessed by members to view the progress of
     * their weight statistic in option 1 or their waist measurement in option 2
     * It reads the option entered by the user and returns it.
     * @return users input
     */
    private int progressMenu()
    {
        System.out.println("Progress Menu:");
        System.out.println("=====================");
        System.out.println("View Progress by Weight ===> (1)");
        System.out.println("View Progress by Waist Measurement ==> (2)");
        System.out.println("Return to Previous Menu ==========> (0)");
        System.out.println("===>>");
        return input.nextInt();
    }

    /**
     * method that executes different methods based in the user input
     * It at first loads the data from the members and the trainers file
     * then runs a switch statement that waits for the user input.
     */
    private void runMainMenu()
    {
        try {
            gymAPI.load();
        } catch (Exception e) {
            System.err.println("Problem loading from file: " + e);
        }
        int option = mainMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    runLoginMenu();
                    break;
                case 2:
                    runRegisterMenu();
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = mainMenu();
        }
        System.out.println("Exiting...");
    }

    /**
     * method that executes different methods based in the user input.
     * The login methods here return a object for the next member to
     * manipulate/view.
     */
    private void runLoginMenu()
    {
        int option = loginMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    member= gymAPI.loginMember(input.nextLine());
                    runMemberMenu();
                    break;
                case 2:
                    trainer = gymAPI.loginTrainer(input.nextLine());
                    runTrainerMenu();
                    break;
                    }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = loginMenu();
            }
    }

    /**
     * method that executes different methods based in the user input.
     * Used to add new users to the application
     */
    private void runRegisterMenu()
    {
        int option = registerMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    gymAPI.addMember();
                    break;
                case 2:
                    gymAPI.addTrainer();
                    break;
                case 3:
                    displayChosenPackageDetails();
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = registerMenu();
        }
    }

    /**
     * Method that executes different methods based in the user input.
     * Has access to the Progress sub-Menu.
     */
    private void runMemberMenu()
    {
        int option = memberMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    gymAPI.viewMemberProfile(member);
                    break;
                case 2:
                    gymAPI.updateMemberProfile(member);
                    break;
                case 3:
                    runProgressMenu();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = memberMenu();
        }
    }

    /**
     * Method that executes different methods based in the user input.
     * Has access to the Assessment sub-menu
     */
    private void runTrainerMenu()
    {
        int option = trainerMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    gymAPI.addMember();
                    break;
                case 2:
                    gymAPI.listMembers();
                    break;
                case 3:
                    gymAPI.searchMembersByEmailMenuVersion(input.nextLine());
                    break;
                case 4:
                    runAssessmentMenu();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = trainerMenu();
        }
    }

    /**
     * Method that executes different methods based in the user input.
     * Sub-Menu of Trainer menu.
     */
    private void runAssessmentMenu()
    {
        int option = assessmentMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    gymAPI.addAssessment(trainer);
                    break;
                case 2:
                    gymAPI.updateComment();
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = assessmentMenu();
        }
    }

    /**
     * Method that executes different methods based in the user input.
     * Sub-Menu of Member menu.
     */
    private void runProgressMenu()
    {
        int option = progressMenu();
        while (option != 0)
        {
            switch (option)
            {
                case 1:
                    gymAPI.showWeightProgress(member);
                    break;
                case 2:
                    gymAPI.showWaistProgress(member);
                    break;
            }
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();
            option = progressMenu();
        }
    }

    /**
     * method that adds the key,value pairs to the chosenPackage HashMap.
     */
    private void fillChosenPackage()
    {
        chosenPackage.put("Package 1", "Allowed access anytime to gym.\nFree access to all classes.\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes.\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 3", "Allowed access to gym at off-peak times. \n€5 fee for all classes. \nNo access to deluxe changing rooms.");
        chosenPackage.put("WIT", "Allowed access to gym during term time.\n€4 fee for all classes.  \nNo access to deluxe changing rooms.");
    }

    /**
     * method for displaying the information in the chosenPackage HashMap.
     * Uses the Map.Entry method in a foreach loop of the LinkedHashMap with entry.Set()
     * to return a set vie
     */
    private void displayChosenPackageDetails()
    {
        System.out.println("Enter the key when registering to choose Package");
        System.out.println("=================================================");
        for (Map.Entry<String, String> entry : chosenPackage.entrySet()) {
            String packName = entry.getKey();
            String details = entry.getValue();
            System.out.println("Key: " + packName +"\n"+ "Details:  " + details + "\n");
        }
    }


}
