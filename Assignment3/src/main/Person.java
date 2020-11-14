/**
 * The Person class is the parent class to the member class and the Trainer class
 * It is an object that holds information that is relevant to all users of the
 * application. The class has four parameters and one constructor.
 *
 * @author Sean Crowley
 * @version 1.0
 */

public class Person {
    private String name;
    private String email;
    private String address;
    private String gender;

    /**
     * Constructor for the Person class
     * @param email Email of the user
     * @param name Name of the user
     * @param address Address of the user
     * @param gender Gender of the user
     */
    public Person(String email, String name, String address, String gender) {
        setName(name);
        this.email = email;
        this.address = address;
        setGender(gender);
    }

    //Getters

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return Gender
     */
    public String getGender() {
        return gender;
    }

    //Setters

    /**
     * Updates the Name to the value passed in. Validation prevents the name
     * from being over thirty characters long. If name those exceed the limit
     * the substring will take the first thirty characters as the name.
     * @param name The new Name
     */
    public void setName(String name) {
        if(name.length() > 30)
            this.name = name.substring(0,30);
        else
            this.name = name;

    }

    /**
     * Updates the Address to the value passed in.
     * @param address The new Address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Updates the Gender to the value passed in. Validation prevents the value
     * passed in from being anything but M or F. If the input is neither of the
     * two Unspecified is substituted.
     * @param gender The new Gender
     */
    public void setGender(String gender) {
        if ((gender.equals("M")) || (gender.equals("F")))
            this.gender = gender;
        else
            this.gender = "Unspecified";
    }

    /**
     * Converts the details of the object into a String format
     * @return The objects information in a String format
     */
    @Override
    public String toString() {
        return "Person: " +
                "name = " + name + '\'' +
                ", email = " + email + '\'' +
                ", address = " + address + '\'' +
                ", gender = " + gender + '\'' +
                ' ';
    }
}
