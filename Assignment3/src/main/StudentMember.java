/**
 * Child class of the Member class. The class has two
 * extra parameters studentId and collegeName
 * @author Sean Crowley
 * @version 1.0
 */
public class StudentMember extends Member {
    private String studentId;
    private String collegeName;

    /**
     *
     * @param email Email of the Student Member
     * @param name Name of the Student Member
     * @param address Address of the Student Member
     * @param gender Gender of the Student Member
     * @param height Height of the Student Member
     * @param startWeight Starting Weight of the Student Member
     * @param chosenPackage Chosen Package of the Student Member
     * @param studentId Student ID of the Student Member
     * @param collegeName College Name of the Student Member
     */
    public StudentMember(String email, String name, String address, String gender,
                         float height, float startWeight, String chosenPackage,
                         String studentId, String collegeName)
    {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        this.studentId = studentId;
        this.collegeName = collegeName;
    }

    /**
     * method that sets the chosen package to the one associated
     * with the collegeName. If collegeName is not recognised
     * sets it to Package 3
     * @param chosenPackage The students new chosen Package
     */
    @Override
    public void chosenPackage(String chosenPackage) {
        if ( collegeName.equals("WIT") )
        {
            chosenPackage = "WIT";
        }
        else {
            chosenPackage = "Package 3";
        }
        super.setChosenPackage(chosenPackage);
    }

    // Getters

    /**
     * Returns the collegeName
     * @return collegeName
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Returns the studentID
     * @return studentID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the Student Member details object
     * in a String format.
     * @return details of the Student Member
     */
    @Override
    public String toString() {
        return super.toString() +
                "StudentMember:" +
                "studentId='" + studentId + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ' ';
    }
}
