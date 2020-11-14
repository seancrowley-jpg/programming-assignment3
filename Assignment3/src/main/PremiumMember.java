/**
 * Child class of the Member class. Premium Members have no
 * defining parameters.
 * @author Sean Crowley
 * @version 1.0
 */
public class PremiumMember extends Member  {
    /**
     * Constructor for object of class Premium Member
     * @param email Email of the Premium Member
     * @param name Name of the Premium Member
     * @param address Address of the Premium Member
     * @param gender Gender of the Premium Member
     * @param height Height of the Premium Member
     * @param startWeight Starting Weight of the Premium Member
     * @param chosenPackage ChosenPackage of the Premium Member
     */
    public PremiumMember(String email, String name, String address, String gender,
                         float height, float startWeight, String chosenPackage)
    {
        super(email, name, address, gender, height, startWeight, chosenPackage);
    }

    /**
     * method that sets the chosenPackage to the parameter
     * passes in.
     * @param chosenPackage the new chosenPackage
     */
    public void chosenPackage(String chosenPackage) {
        super.setChosenPackage(chosenPackage);
    }

    /**
     * Builds a String of the Premium Member objects details
     * @return Details of the Premium Member
     */
    @Override
    public String toString() {
        return super.toString() +
                "PremiumMember{}";
    }
}
