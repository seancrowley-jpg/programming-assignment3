/**
 * The Trainer class is a child class of the Person class. It holds information
 * regarding the Trainer users of the application.
 * @author Sean Crowley
 * @version 1.0
 */
public class Trainer extends Person {
    private String specialty;

    /**
     * Constructor of the objects of the Trainer class.
     * @param email Email of the Trainer
     * @param name Name of the trainer
     * @param address Address of the Trainer
     * @param gender Gender of the Trainer
     * @param specialty Specialty of the Trainer
     */
    public Trainer(String email, String name, String address, String gender, String specialty)
    {
        super(email, name, address, gender);
        this.specialty = specialty;
    }
    // Getter

    /**
     * Returns the Specialty of the trainer
     * @return specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    //Setters

    /**
     * Updates the Specialty of the Trainer with the passed in parameter
     * @param specialty The new Specialty
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Creates a String of the information of the Trainer object
     * @return Trainer information
     */
    @Override
    public String toString() {
        return super.toString()+
                "Trainer: " +
                "specialty='" + specialty + '\'' +
                ' ';
    }
}
