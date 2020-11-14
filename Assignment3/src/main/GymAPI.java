import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * The GymAPI class operates between the model classes and the
 * menuController class. It contains the majority of methods
 * in the application. It contains  two arrayLists one
 * of Member type called members and one of Trainer type.
 * The class also creates an instance of the Scanner class.
 * called trainers.
 * @author Sean Crowley
 * @version 1.0
 */

public class GymAPI {
    private ArrayList<Member> members;
    private ArrayList<Trainer> trainers;
    private Scanner input = new Scanner(System.in);

    /**
     * Constructor that initializes the two ArrayLists
     */
    public GymAPI() {
        this.members = new ArrayList<Member>();
        this.trainers = new ArrayList<Trainer>();
    }

    //Getters

    /**
     * Returns an arraylist of members
     * @return members Arraylist
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * Returns an Arraylist of trainers
     * @return trainer Arraylist
     */
    public  ArrayList<Trainer> getTrainers() {return  trainers;}

    public void addMember(Member member)
    {
       members.add(member);
    }

    /**
     * method that is responsible for adding member Objects to the
     * members Arraylist. The method prompts the user with a series of questions
     * displayed in the console and takes in their inputs and applies them to
     * a new Member object. The method contains a while loop that verifies if
     * the email entered is not already taken by another member or trainer in either
     * arraylists. If chosen Package is either WIT or Package 3 the user will be asked
     * for additional information regarding Student Members. At the end they will be
     * added as a Student Member. Information that take in floats as a parameter
     * are wrapped in a try catch block to prevent the programme from crashing
     * if an invalid input type in input. If package 1 is entered as a chosenPackage the
     * member will be added as a Premium Member otherwise they are added as a standered member.
     * The member is saved in a XML file at the end of the method using the store() method.
     */
    public void addMember() {
        boolean goodInput = false;
        float height = 2;
        float startWeight = 50;
        String studentId = "";
        String collegeName = "";
        System.out.println("Enter Member Email: ");
        String email = input.nextLine();
        while (validTrainerEmailTest(email) || validMemberEmailTest(email))
        {
            System.out.println("Email in use!" + "\n" + "Enter new email: ");
            email = input.nextLine();
        }
        System.out.println("Enter Member Name: ");
        String name = input.nextLine();
        System.out.println("Enter Member Address: ");
        String address = input.nextLine();
        System.out.println("Enter Member Gender: ");
        String gender = input.nextLine();
        System.out.println("Enter Member Chosen Package: ");
        String chosenPackage = input.nextLine();
        if ( chosenPackage.equals("WIT") || chosenPackage.equals("Package 3") )
        {
            System.out.println("Please enter your Student ID: ");
            studentId = input.nextLine();
            System.out.println("Please enter your Colleges Name: ");
            collegeName = input.nextLine();
        }
        while (!goodInput) {
            try {
                System.out.println("Enter Member Height: ");
                height = input.nextFloat();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Number Required");
            }
            try {
                System.out.println("Enter Member Starting Weight: ");
                startWeight = input.nextFloat();
                goodInput = true;
            }  catch (Exception e)
            {
                input.nextLine();
                System.out.println("Number Required");
            }
        }
        System.out.println("Member successfully added");
        if ( chosenPackage.equals("WIT") || chosenPackage.equals("Package 3") )
        {
            Member studentMember = new StudentMember(email,name,address,gender,height,startWeight,chosenPackage,studentId,collegeName);
            members.add(studentMember);
            try {
                store();
            } catch (Exception e) {
                System.err.println("Problem loading from file: " + e);
            }
        }
        else if ( chosenPackage.equals("Package 1") )
        {
            Member premiumMember = new PremiumMember(email,name,address,gender,height,startWeight,chosenPackage);
            members.add(premiumMember);
            try {
                store();
            } catch (Exception e) {
                System.err.println("Problem loading from file: " + e);
            }
        }
        else {
            members.add(new Member(email, name, address, gender, height, startWeight, chosenPackage));
            try {
                store();
            } catch (Exception e) {
                System.err.println("Problem loading from file: " + e);
            }
        }
    }

    public  void addTrainer(Trainer trainer)
    {
        trainers.add(trainer);
    }

    /**
     * method that adds a trainer to the trainers arraylist.
     * The method prompts the user with a series of questions
     * displayed in the console and takes in their inputs and applies them to
     * a new Trainer object. The trainer is saved to an XML file using
     * the store method.
     */
    public void addTrainer() {
        System.out.println("Enter Member Email: ");
        String email = input.nextLine();
        while (validTrainerEmailTest(email) || validMemberEmailTest(email))
        {
            System.out.println("Email in use!" + "\n" + "Enter new email: ");
            email = input.nextLine();
        }
        System.out.println("Enter Trainer Name: ");
        String name = input.nextLine();
        System.out.println("Enter Trainer Address: ");
        String address = input.nextLine();
        System.out.println("Enter Trainer Gender: ");
        String gender = input.nextLine();
        System.out.println("Enter your Speciality: ");
        String speciality = input.nextLine();
        trainers.add(new Trainer(email,name,address,gender,speciality));
        try {
            store();
        } catch (Exception e) {
            System.err.println("Problem loading from file: " + e);
        }
        System.out.println("Trainer successfully added");
    }

    /**
     * Method that adds an assessment to the HashMap assessments in the member class.
     * At first the user is asked which member they want to add an assessment to.
     * the listMembers() method is called to display all the members in the Gym.
     * The input is validated using the isValidMember() method. The date of the
     * assessment is requested first as this is the key for which the assessments are
     * stored in the HashMap. Next is the comment followed by the float inputs which are
     * wrapped in the try catch blocks. The new Assessment object is created and using
     * the index the member is located with the .get() method and added to their assessments
     * using .put() method. The store() method is called to save the data.
     * @param trainer The trainer object that is added the assessment
     */
    public void addAssessment(Trainer trainer)
    {
        boolean goodInput = false;
        float weight = 60;
        float waist = 45;
        float thigh = 40;
        //input.nextLine();
        System.out.println("Choose which member you would like to add an Assessment to: ");
        //System.out.println(listMembers());
        listMembers();
        System.out.println("Index:");
        int index = input.nextInt();
        if ( isValidMemberIndex(index) ) {
            input.nextLine();
            System.out.println("Enter Date of Assessment(YY/MM/DD): ");
            String date = input.nextLine();
            System.out.println("Enter Comment: ");
            String comment = input.nextLine();
            while (!goodInput) {
                try {
                    System.out.println("Enter Weight: ");
                    weight = input.nextFloat();
                    goodInput = true;
                } catch (Exception e) {
                    input.nextLine();
                    System.out.println("Number Required");
                }
                try {
                    System.out.println("Enter Waist: ");
                    waist = input.nextFloat();
                    goodInput = true;
                } catch (Exception e) {
                    input.nextLine();
                    System.out.println("Number Required");
                }
                try {
                    System.out.println("Enter Thigh: ");
                    thigh = input.nextFloat();
                    goodInput = true;
                } catch (Exception e) {
                    input.nextLine();
                    System.out.println("Number Required");
                }
            }
            Assessment assessment = new Assessment(weight, thigh, waist, comment, trainer);
            members.get(index).assessments.put(date, assessment);
            try {
                store();
            } catch (Exception e) {
                System.err.println("Problem loading from file: " + e);
            }
        }
    }

    /**
     * method that tests if there are members objects in the members arraylist
     * and if there is print a string with the numbers and return the size.
     * @return an int of the number of members
     */
    public int numberOfMembers() {
        if (members.size() <= 0)
            System.out.println("There are no members in the gym");
        else
            System.out.println("The number of Members in the gym is: " + members.size());
        return members.size();
    }

    /**
     * Method that tests if there are trainer objects in the trainers arraylist
     * and if there is print a string with the numbers and return the size.
     * @return an int of the number of trainers
     */
    public int numberOfTrainers() {
        if (trainers.size() <= 0)
            System.out.println("There are no Trainers working for the gym");
        else
            System.out.println("The number of Trainers on staff in the gym is: " + trainers.size());
        return trainers.size();
    }

    /**
     * Method that tests if the index entered as a parameter exists in
     * the members arraylist. if the index is greater than 0 or more
     * than the size of the list return true otherwise returns false.
     * @param index the index of the member
     * @return boolean - true if valid index
     */
    public boolean isValidMemberIndex(int index) {
        System.out.println("Enter index of Member: ");

        if(index >= 0 && index < members.size())
        {
            System.out.println("This is a valid index for the Member ArrayList");
            return true;
        }
        else {
            System.out.println("This is not a valid index");
            return false;
        }
    }

    /**
     * Method that tests if the index entered as a parameter exists in
     * the trainers arraylist. if the index is greater than 0 or more
     * than the size of the list return true otherwise returns false.
     * @param index the index of the trainer
     * @return boolean true if trainer exits in the arraylist
     */
    public boolean isValidTrainerIndex(int index) {
        System.out.println("Enter index of Trainer: ");

        if((index >= 0) && (index < trainers.size()))
        {
            System.out.println("This is a valid index for the Trainer ArrayList");
            return true;
        }
        else {
            System.out.println("This is not a valid index");
            return false;
        }
    }

    /**
     * Method the returns the member object with the email that is
     * entered. Uses a foreach loop to loop through every member in the
     * members arraylist. Compares the email entered with the email in
     * the member objects using the .equals method. If there is a match return
     * that member.
     * @param emailEntered the email of the member
     * @return the member object with the associated email
     */
    public Member searchMembersByEmail(String emailEntered)
    {
        System.out.println("Enter the email of the member you are searching for: ");
        for (Member member : members) {
            if ( member.getEmail().equals(emailEntered) ) {
                System.out.println("Member with the email: " + member.getName());
                return member;
            }
        }
        return null;
    }

    //Duplicate of previous method for use in the application.
    //input.nextLine caused the test to stall.
    //Without the line of code the method did not function correctly in the application.
    public Member searchMembersByEmailMenuVersion(String emailEntered)
    {
        System.out.println("Enter the email of the member you are searching for: ");
        emailEntered = input.nextLine(); //Line cause issues with Gym API Unit Test. Prevent the test from completing.
        for (Member member : members) {
            if ( member.getEmail().equals(emailEntered) ) {
                System.out.println("Member with the email: " + member.getName());
                return member;
            }
        }
        return null;
    }

    /**
     * Method that returns an arraylist of all members that completely or partially
     * match the String taken in. It at first creates a new ArrayList of Strings and then
     * using a for loop finds it finds the member whose name directly matches the name entered
     * by the user and adds it to the arraylist. Also if the name entered contains any character
     * that matches another members name, that name will also be added to the list.
     * @param nameEntered The name the user is searching for
     * @return An Arraylist of Strings with the name being searched for and similar names.
     */
    public ArrayList<String> searchMembersByName(String nameEntered)
    {
        ArrayList<String> memberNames = new ArrayList<>();
        System.out.println("Enter the name of the Member you are searching for: ");
        for (int i = 0; i < members.size(); i++)
        {
            Member member = members.get(i);
            if(member.getName().equals(nameEntered)){
                memberNames.add(nameEntered);
            }
            else if (member.getName().contains(nameEntered)){
                memberNames.add(member.getName());
            }
        }
        System.out.println("Here is a list of Members with that name (or similar): \n" + memberNames);
        return memberNames;
    }

    /**
     * Method that returns an arraylist of all members that completely or partially
     * match the String taken in. It at first creates a new ArrayList of Strings and then
     * using a for loop finds it finds the trainer whose name directly matches the name entered
     * by the user and adds it to the arraylist. Also if the name entered contains any character
     * that matches another trainers name, that name will also be added to the list.
     * @param nameEntered The name of the Trainer being searched for
     * @return An arraylist of Trainers with the name entered or similar names
     */
    public ArrayList<String> searchTrainersByName(String nameEntered)
    {
        ArrayList<String> trainerNames = new ArrayList<>();
        System.out.println("Enter the name of the Member you are searching for: ");
        for (int i = 0; i < trainers.size(); i++)
        {
            Trainer trainer = trainers.get(i);
            if(trainer.getName().equals(nameEntered)) {//|| trainer.getName().contains(nameEntered)){
                trainerNames.add(nameEntered);
            }
            else if (trainer.getName().contains(nameEntered)){
                trainerNames.add(trainer.getName());
        }

        }
        System.out.println("Here is a list of Members with that name (or similar): \n" + trainerNames);
        return trainerNames;
    }

    /**
     * Method the returns the trainer object with the email that is
     * entered. Uses a foreach loop to loop through every trainer in the
     * trainers arraylist. Compares the email entered with the email in
     * the trainer objects using the .equals method. If there is a match return
     * that trainer.
     * @param emailEntered email of the trainer being searched for
     * @return the Trainer object with the matching email
     */
    public Trainer searchTrainersByEmail(String emailEntered)
    {
        System.out.println("Enter the email of the Trainer you are searching for: ");
        for (int i =0; i < trainers.size(); i++)
        {
            Trainer trainer = trainers.get(i);
            if (trainer.getEmail().equals(emailEntered)){
                System.out.println("Trainer with that email: " + trainer.getName());
                return  trainer;
            }
        }
        return null;
    }

    /**
     * method that lists all of the members in the gym. A for loop
     * is used to add every member to a String that prints to the
     * console.
     * @return the members arraylist
     */
    public ArrayList<Member> listMembers()
    {
        String listOfMembers = "";
        for (int i =0; i< members.size(); i++)
        {
            listOfMembers = listOfMembers + i + ": " + members.get(i) + "\n";
        }
        System.out.println(listOfMembers);
        return members;
    }

    /**
     * method that lists the members whose weight is considered ideal
     * using the method in the GymUtility class isIdealWeight().
     * It first creates a new arraylist of members.
     * Uses a for each loop that gets the latest assessment from the member.
     * If no assessments exist then it returns an empty list. The assessment
     * weight is passed into the isIdealWeight method. If it returns true the
     * member is added to the arraylist.
     * @return an arraylist of members with ideal weight.
     */
    public ArrayList<Member> listMembersWithIdealWeight() {
        ArrayList<Member> membersWithIdealWeight = new ArrayList<>();
        for (Member member : members) {
            Assessment assessment = member.latestAssessment();
            if ( member.latestAssessment() == null )
            {
                membersWithIdealWeight = membersWithIdealWeight;
            }
            else if (GymUtility.isIdealBodyWeight(member, assessment))
            {
                membersWithIdealWeight.add(member);
            }
        }
        return membersWithIdealWeight;
    }

    /**
     * method that creates an arraylist with members of a specific BMI category.
     * Uses a foreach loop and adds a member and their latest assessment to the method
     * calculateBMI from the GymUtility class. this returns a bmi which is added to another
     * method from the the GymUtility class determineBMICategory which returns a String of the
     * category the bmi falls under. the resulting string is assigned to the group string
     * and is compared to the category input by the user. If and else if statements test
     * the input string against the members category. If the input entered matches
     * the members BMI category they are added to the arraylist.
     * @param category the BMI category the user is searching for
     * @return an arraylist of members under a certain BMI category
     */
    public ArrayList<Member> listMembersBySpecificBMICategory(String category)
    {
        ArrayList<Member> membersByBMI = new ArrayList<>();
        if ( members.size() > 0 ) {
            for (Member member : members) {
                double bmi = GymUtility.calculateBMI(member, member.latestAssessment());
                String group = GymUtility.determineBMICategory(bmi);
                if ( !group.toLowerCase().contains(category.toLowerCase()) ) {
                    System.out.println("Invalid input / No Matches");
                }
                else if ( group.equals(category) ) {
                    membersByBMI.add(member);
                    System.out.println("Here are a list of members in the category: " + group + "\n" + member.getName() + "\n");
                }
                else if ( group.contains(category) ) {
                    membersByBMI.add(member);
                    System.out.println("Here are a list of members in the category (or similar): " + group + "\n" + member.getName() + "\n");
                }
                else if ( group.toLowerCase().contains(category.toLowerCase()) ) {
                    membersByBMI.add(member);
                    System.out.println("Here are a list of members in the category: " + group + "\n" + member.getName() + "\n");
                }
            }
        }
        else
            System.out.println("No Members registered");
            return membersByBMI;
    }

    /**
     * method that returns  a string of a Members details in both Imperial and
     * Metric format. If members exits a foreach loop is used to retrieve the members
     * latest assessment and add all their relevant details to a string. For the metric
     * information the values are presented as is but rounded using Math.round for readability.
     * For the Imperial details the weight is converted to pounds by multiplying the value by 2.2.
     * The height is multiplied by 10 for metric then divide by 10 to reduce the number to 1 decimal place.
     * Then the height is multiplied by 39.37 to convert it to inches.
     * @return A string a with a members details in both metric and imperial format
     */
    public String listMemberDetailsImperialAndMetric()
    {
        String noMembers = ("No registered members");
        String listOfMembersDetails = "";
        if(members.size() > 0) {
            for (Member member : members) {
                Assessment assessment = member.latestAssessment();
                listOfMembersDetails = listOfMembersDetails + member.getName() + ": " + Math.round(assessment.getWeight()) + " kg (" + Math.round(assessment.getWeight() * 2.2) + " lbs) " + (Math.round(member.getHeight() * 10) / 10.0) + " metres (" +  + Math.round(member.getHeight() * 39.37) + " inches)." + "\n";
            }
            System.out.println(listOfMembersDetails);
            return listOfMembersDetails;
        }
        else
            System.out.println("No registered members");
            return noMembers;
    }

    /**
     * Method used when adding a new member to prevent duplicate emails.
     * Uses a foreach loop to find if the email entered exists in another
     * member object.
     * @param email the email being used to add another member
     * @return boolean returns true if email entered exits within another existing member
     */
    public boolean validMemberEmailTest(String email)
    {
        for (Member member: members)
        {
            if (member.getEmail().equals(email) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used when adding a new member to prevent duplicate emails.
     * Uses a foreach loop to find if the email entered exists in another
     * trainer object.
     * @param email email enterd to add another trainer
     * @return boolean returns true if email entered exits within another existing trainer
     */
    public boolean validTrainerEmailTest(String email)
    {
        for (Trainer trainer: trainers)
        {
            if (trainer.getEmail().equals(email) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * method used for logging a member into the application.
     * returns the member object that matches the email entered.
     * Uses a foreach loop and if statement that returns a member
     * if it matches the email entered. returns null and exits the
     * programme if the email entered finds no match
     * @param email email of the member logging in
     * @return the member object with the matching email
     */
    public Member loginMember(String email)
    {
        System.out.println("Enter your Member email");
        email = input.nextLine();
        for (Member member : members) {
            if ( member.getEmail().equals(email) ) {
                System.out.println("Welcome");
                return member;
            }
        }
        System.out.println("Access Denied");
        System.exit(0);
        return null;
    }

    /**
     * method used for logging a member into the application.
     * returns the member object that matches the email entered.
     * Uses a foreach loop and if statement that returns a member
     * if it matches the email entered. returns null and exits the
     * programme if the email entered finds no match.
     * @param email the email of the trainer trying to log in
     * @return the trainer object with the matching email
     */
    public Trainer loginTrainer(String email)
    {
        System.out.println("Enter your email");
        email = input.nextLine();
        for (Trainer trainer : trainers) {
            if ( trainer.getEmail().equals(email) ) {
                System.out.println("Welcome");
                return trainer;
            }
        }
        System.out.println("Access Denied");
        System.exit(0);
        return null;
    }

    /**
     * method that prints a String to the console of the members details
     * and all of their assessments.
     * @param member the member object being viewed
     */
    public void viewMemberProfile(Member member)
    {
        System.out.println(member.toString());
        //System.out.println(member.getAssessments());
    }

    /**
     * method that uses setters to reenter the information of a member.
     * sets the new information to the values passed in by the user.
     * Uses try catch blocks to catch Exceptions and prevent the programme
     * from crashing if a invalid input is passed. Saves the info with the store()
     * method.
     * @param member the member object with updated values
     */
    public void updateMemberProfile(Member member)
    {
        boolean goodInput = false;
        float height = 2;
        float startWeight = 75;
        System.out.println("Enter Member Name: ");
        String name = input.nextLine();
        member.setName(name);
        System.out.println("Enter Member Address: ");
        String address = input.nextLine();
        member.setAddress(address);
        System.out.println("Enter Member Gender: ");
        String gender = input.nextLine();
        member.setGender(gender);
        System.out.println("Enter Member Chosen Package: ");
        String chosenPackage = input.nextLine();
        member.setChosenPackage(chosenPackage);
        while (!goodInput) {
            try {
                System.out.println("Enter Member Height: ");
                height = input.nextFloat();
                goodInput = true;
                member.setHeight(height);
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Number Required");
            }
            try {
                System.out.println("Enter Member Starting Weight: ");
                startWeight = input.nextFloat();
                goodInput = true;
                member.setStartWeight(startWeight);
            }  catch (Exception e)
            {
                input.nextLine();
                System.out.println("Number Required");
            }
        }
        try {
            store();
        } catch (Exception e) {
            System.err.println("Problem loading from file: " + e);
        }
    }

    /**
     * method that allows the trainer to change the comment on a
     * assessment. Uses the index passed by the trainer to pick the
     * desired member. Then the trainer enters the date of the assessment
     * which is used to find the value(assessment) associated with the key(date).
     * The new comment is then entered and a setter is used to create the new comment.
     */
    public void updateComment() {
        listMembers();
        System.out.println("Choose which Members assessment comment you want to edit: ");
        int memberIndex = input.nextInt();
        if ( isValidMemberIndex(memberIndex) ) {
            Member member = members.get(memberIndex);
            System.out.println(member.assessments);
            System.out.println("Enter the date of the assessment you want to edit: ");
            input.nextLine();
            String assessmentKey = input.nextLine();
            Assessment assessment = member.assessments.get(assessmentKey);
            System.out.println("Enter new comment: ");
            String comment = input.nextLine();
            assessment.setComment(comment);
            try {
                store();
            } catch (Exception e) {
                System.err.println("Problem loading from file: " + e);
            }
        }
    }

    /**
     * Method that lists all of the members assessments in order with the
     * weight value for each assessment. Creates a new TreeMap which sorts the key values
     * by their natural order in this case the dates of the assessment and passes the members assessments into it.
     * Uses a foreach loop with Map.Entry which returns a collection view of the TreeMap. The entry key
     * is assigned to a String called date and the assessment to the entry.getValue().
     * A String is printed to the console with the date and the associated weight.
     * @param member the logged in member
     */
    public  void showWeightProgress(Member member)
    {
        Map<String, Assessment> sortedMap = new TreeMap<>(member.assessments);
        for (Map.Entry<String, Assessment> entry : sortedMap.entrySet()) {
            String date = entry.getKey();
            Assessment assessment = entry.getValue();
            System.out.println("Date: " + date + " Weight: " + assessment.getWeight() + " kg");
        }
    }

    /**
     * Method that lists all of the members assessments in order with the
     * weight value for each assessment. Creates a new TreeMap which sorts the key values
     * by their natural order in this case the dates of the assessment and passes the members assessments into it.
     * Uses a foreach loop with Map.Entry which returns a collection view of the TreeMap. The entry key
     * is assigned to a String called date and the assessment to the entry.getValue().
     * A String is printed to the console with the date and the associated weight.
     * @param member the logged in member
     */
    public  void showWaistProgress(Member member)
    {
        Map<String, Assessment> sortedMap = new TreeMap<>(member.assessments);
        for (Map.Entry<String, Assessment> entry : sortedMap.entrySet()) {
            String date = entry.getKey();
            Assessment assessment = entry.getValue();
            System.out.println("Date: " + date + " Weight: " + assessment.getWaist() + " cm");
        }
    }

    /**
     * Method used to save data to an XML file. Writes to two files.
     * One for the members and one for trainers.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void store() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());

        Class<?>[] classes = new Class[] { Member.class, Trainer.class, StudentMember.class, PremiumMember.class, Assessment.class};

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectOutputStream outMember = xstream.createObjectOutputStream(new FileWriter("members.xml"));
        outMember.writeObject(members);
        outMember.close();
        ObjectOutputStream outTrainer = xstream.createObjectOutputStream(new FileWriter("trainers.xml"));
        outTrainer.writeObject(trainers);
        outTrainer.close();
    }

    /**
     * Method for loading data from an XML file. Reads from to files.
     * A Members.xml file and a Trainers.xml file.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());

        Class<?>[] classes = new Class[] { Member.class,Trainer.class, StudentMember.class, PremiumMember.class, Assessment.class };

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);


        ObjectInputStream isMember = xstream.createObjectInputStream(new FileReader("members.xml"));
        members = (ArrayList<Member>) isMember.readObject();
        isMember.close();
        ObjectInputStream isTrainer = xstream.createObjectInputStream(new FileReader("trainers.xml"));
        trainers = (ArrayList<Trainer>) isTrainer.readObject();
        isTrainer.close();
    }
}
