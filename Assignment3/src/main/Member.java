import java.util.*;

/**
 * The Member class is a child of the Person class. It stores information specific
 * to members of the gym. It also contains a Hashmap of assessment objects.
 *
 * @version 1.0
 * @author Sean Crowley
 */
public class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;
    public HashMap<String, Assessment> assessments;

    /**
     * Constructor of the Member Class.
     * @param email Email of the Member.
     * @param name Name of the Member.
     * @param address Address of the Member.
     * @param gender Gender of the Member.
     * @param height Height of the Member.
     * @param startWeight The Starting Weight of the Member.
     * @param chosenPackage The Chosen Package of the Member. Determines which
     *                      child class (if any) the member is assigned to.
     */
    public Member(String email, String name, String address,
                  String gender, float height, float startWeight,
                  String chosenPackage)
    {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
        assessments = new HashMap<String, Assessment>();
    }

    // Getters

    /**
     * Returns the members Height
     * @return height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Returns the startWeight of the member
     * @return starWeight
     */
    public float getStartWeight() {
        return startWeight;
    }

    /**
     * Returns the chosenPackage of the Member
     * @return chosenPackage
     */
    public  String getChosenPackage() {
        return chosenPackage;
    }

    /**
     * Returns the Hashmap of Assessment-String,Assessment objects
     * @return assessments
     */
    public HashMap<String, Assessment> getAssessments() {
        return assessments;
    }

    // Setters

    /**
     * Updates the height of the member to the value passed in.
     * Validation sets the height to 2 if the height is below 1 or above 3.
     * @param height the new Height
     */
    public void setHeight(float height) {
        if((height >= 1) && (height <= 3))
            this.height = height;
        else
            this.height = 2;
    }

    /**
     * Updates the startWeight of the member to the value passed in.
     * Validation sets the startWeight to 70 if value is less than 35 or more than
     * 250.
     * @param startWeight The new startWeight
     */
    public void setStartWeight(float startWeight) {
        if((startWeight >= 35) && (startWeight <= 250))
            this.startWeight = startWeight;
        else
            this.startWeight = 70;
    }

    /**
     * Updates the chosenPackage of the member to the parameter passed in.
     * @param chosenPackage the new chosenPackage
     */
    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    /**
     * Method that returns the latest assessment for the member. If assessments
     * exist for the member a new TreeMap is created with the assessments
     * added into it. The TreeMap will sort the keys (which are dates) in their
     * natural order. The last key (which would be the latest date) is returned
     * using the .lastKey() method. If no assessments exist it returns null.
     * @return The latest assessment for the member
     */
    public Assessment latestAssessment() {
        if ( assessments.size() > 0) {
            TreeMap<String, Assessment> sortedAssessments = new TreeMap<>(assessments);
            return sortedAssessments.get(sortedAssessments.lastKey());
        }
        else{
            System.out.println("No Assessments for this member");
            return null;
        }
    }

    /**
     * When called this method creates a new SortedSet of Strings called dates
     * which is a new TreeSet of the members assessments keySet. The dates are then
     * returned
     * @return SortedSet called dates
     */
    public SortedSet<String> sortedAssessmentDates()
    {
        SortedSet<String> dates = new TreeSet<>(assessments.keySet());
        return dates;
    }

    /**
     * method that sets the chosenPackage to the parameter
     * passes in.
     * @param chosenPackage the new chosenPackage
     */
    public void chosenPackage(String chosenPackage){
        setChosenPackage(chosenPackage);
    }

    /**
     * Creates a String of the members details in a String format.
     * @return Details of the Member in String format
     */
    @Override
    public String toString() {
        return super.toString()+
                "\nMember: " +
                "height = " + height +
                ", startWeight = " + startWeight +
                ", chosenPackage = '" + chosenPackage + '\'' +
                " \nAssessments = \n" + assessments ;
    }
}

