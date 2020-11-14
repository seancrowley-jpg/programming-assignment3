/**
 * The assessment class is used to store the data of an individual members assessment
 * entered by the trainer. The class has five parameters and two constructors.
 *
 * @author Sean Crowley
 * @version 1.0
 */

public class Assessment {

    private float weight;
    private float thigh;
    private float waist;
    private String comment;
    Trainer trainer;

    /**
     * Constructor for objects of class Assessment
     * @param weight Weight in (kg) of the member
     * @param thigh Size of the thigh in (cm)
     * @param waist Size of the waist in (cm)
     * @param comment Comment added by the trainer
     */

    public Assessment(float weight, float thigh, float waist, String comment) {
        this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
    }

    /**
     * Overloaded constructor with extra trainer object
     * @param weight Weight in (kg) of the member
     * @param thigh Size of the thigh in (cm)
     * @param waist Size of the waist in (cm)
     * @param comment Comment added by the trainer
     * @param trainer Trainer who added the assessment
     */

    public Assessment(float weight, float thigh, float waist, String comment, Trainer trainer) {
        this.weight = weight;
        this.thigh = thigh;
        this.waist = waist;
        this.comment = comment;
        this.trainer = trainer;
    }

    //Getters

    /**
     * @return Weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @return Thigh
     */

    public float getThigh() {
        return thigh;
    }

    /**
     * @return Waist
     */

    public float getWaist() {
        return waist;
    }

    /**
     * @return Comment
     */

    public String getComment() {
        return comment;
    }

    //Setters

    /**
     * Updates the Weight to the value passed as a parameter
     * @param weight The new Weight
     */

    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Updates the Thigh to the value passed as a parameter
     * @param thigh The new Thigh
     */

    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    /**
     * Updates the Waist to the value passed as a parameter
     * @param waist The new Waist
     */

    public void setWaist(float waist) {
        this.waist = waist;
    }

    /**
     * Updates the Comment to the value passed as a parameter
     * @param comment The new Comment
     */

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Creates a String of the details in the Assessment object
     * @return Details of the Assessment in String format
     */
    @Override
    public String toString() {
        return  " weight = " + weight +
                ", thigh = " + thigh +
                ", waist = " + waist +
                ", comment = " + comment + "}\n";
    }
}
