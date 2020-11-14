/**
 * The GymUtility class is used for calculating analytics in
 * the application. It contains only static methods and
 * has no fields.
 * @author Sean Crowley
 * @version 1.0
 */
public class GymUtility {
    /**
     * method that calculates the Body Mass Index(BMI) of the member
     * using an assessments weight value and the members height.
     * @param member The member the Bmi is being calculated on
     * @param assessment the assessments weight value
     * @return the calculated BMI
     */
    public static double calculateBMI(Member member, Assessment assessment) {
        return assessment.getWeight() / (member.getHeight() * member.getHeight());
    }

    /**
     * method that returns a String detailing what BMI category 
     * the member falls into. Uses a number of if and else if statements
     * to determine the correct category.
     * @param bmiValue the bmi of the member
     * @return A String stating which category the bmi falls under
     */
    public static String determineBMICategory(double bmiValue) {
        String bmiCategory;
        if (bmiValue < 16) {
            //System.out.println("SEVERELY UNDERWEIGHT");
            bmiCategory = ("SEVERELY UNDERWEIGHT");
            return bmiCategory;
        } else if ((bmiValue >= 16) && (bmiValue < 18.5)) {
            //System.out.println("UNDERWEIGHT");
            bmiCategory = ("UNDERWEIGHT");
            return bmiCategory;
        } else if ((bmiValue >= 18.5) && (bmiValue < 25)) {
            //System.out.println("NORMAL");
            bmiCategory = ("NORMAL");
            return bmiCategory;
        } else if ((bmiValue >= 25) && (bmiValue < 30)) {
            //System.out.println("OVERWEIGHT");
            bmiCategory = ("OVERWEIGHT");
            return bmiCategory;
        } else if ((bmiValue >= 30) && (bmiValue < 35)) {
            //System.out.println("MODERATELY OBESE");
            bmiCategory = ("MODERATELY OBESE");
            return bmiCategory;
        } else if (bmiValue >= 35) {
            //System.out.println("SEVERELY OBESE");
            bmiCategory = ("SEVERELY OBESE");
            return bmiCategory;
        } else return null;
    }

    /**
     * method that determines whether the member is an ideal weight
     * based on the devine formula. The formula used for males is 50 + 0.9 * ((height * 100) - 152)
     * with the female formula substituting the first number 50 for 45.5.
     * If gender is unspecified the female formula is used.
     * At first the members gender is tested to get the correct formula.
     * if and else statements test if the calculated weight matches the members assessment weight.
     * Both weights are rounded and and if they match the method returns true.
     * @param member the member being assessed
     * @param assessment the assessment being tested- usually the latest
     * @return Boolean True if ideal false if not
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        double devine = 50 + 0.9 * ((member.getHeight() * 100) - 152);
        double devineF = 45.5 + 0.9 * ((member.getHeight() * 100) - 152);
        if ( (member.getGender().equals("M")) && (Math.round(devine) != Math.round(assessment.getWeight()))) {
            System.out.println("Your weight " + assessment.getWeight() + " is not an ideal weight ");
            System.out.println("ideal weight for your height: " + devine);
            return false;
        } else if ( (member.getGender().equals("M")) && (Math.round(devine) == Math.round(assessment.getWeight()))) {
            System.out.println("This is an ideal weight");
            System.out.println("ideal weight for your height: " + devine);
            return true;
        }
        if (((member.getGender().equals("F")) || (member.getGender().equals("Unspecified"))) && (Math.round(devineF) != Math.round(assessment.getWeight()))) {
            System.out.println("Your weight " + assessment.getWeight() + " is not an ideal weight ");
            System.out.println("ideal weight for your height: " + devineF);
            return false;
        } else if (((member.getGender().equals("F")) || (member.getGender().equals("Unspecified"))) && (Math.round(devineF) == Math.round(assessment.getWeight()))) {
            System.out.println("This is an ideal weight");
            System.out.println("ideal weight for your height: " + Math.round(devineF));
            return true;
        }
        else return false;
    }
}
