package model;
/**
 * @author liz
 * The entry object
 */
public class Entry {

    private int cardNumber;
    private String filePath;
    private int contest;
    private String entryName;
    private int entryNumber;

    /** Constructor()
     * @param entry - the entry unique number
     * @param cardNumber - user card number
     * @param filePath - path for the entry being submitted
     * @param contest - which contest the entry is being submitted to
     * @param entryName - the entries title*/
    public Entry(int entry,int cardNumber, String filePath, int contest, String entryName) {
        this.cardNumber = cardNumber;
        this.filePath = filePath;
        this.contest = contest;
        this.entryName = entryName;
        this.entryNumber = entry;

    }

    /** What contest?
     * @return the contest
     * */
    public int getContest() {
        return contest;
    }

    /** What card number?
     * @return the card number for the user
     * */
    public int getCardNumber() {
        return cardNumber;
    }

    /** What entry?
     * @return the name of the entry submitted*/
    public String getEntryName() {
        return entryName;
    }

    /** What is the path?
     *
     * @return the path of the file submitted
     * */
    public String getFilePath() {
        return filePath;
    }

    /** What entry?
     * @return the entry number that is unique
     * */
    public int getEntryNumber() {
        return entryNumber;
    }

    @Override
    public String toString() {
        return entryNumber + "," + cardNumber + "," + filePath + "," + entryName +
        		"," + contest;
    }

    @Override
    public boolean equals(Object theObject) {
        boolean result = false;
        if(theObject == null) result = false;
        final Entry theOther = (Entry) theObject;
        if (this.getClass().equals(theObject.getClass())  &&
                (this.entryName.equals(theOther.entryName) &&
                (this.cardNumber == theOther.cardNumber) &&
                (this.contest) == theOther.contest) &&
                (this.filePath.equals(theOther.filePath)) &&
                (this.entryNumber == theOther.entryNumber) &&
                (this.toString().equals(theOther.toString())))
            result = true;
        return result;
    }

    @Override
    public int hashCode(){
        int result = 0;
        result = (int) this.entryNumber / 11;
        return result;
    }

}
