import javax.print.attribute.IntegerSyntax;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lizmiller on 5/12/16.
 */
public class UserDatabaseManager {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final int USER_CARD_NUMBER_IDX = 0;
    private static final int USER_NAME_IDX = 1;
    private static final int USER_AGE_IDX = 2;
    private static final int USER_LOGIN_CREDENTIAL_IDX = 3;
    private static final int USER_IS_ADMIN_IDX = 4;
    private static final int USER_IS_JUDGE_IDX = 5;
    private static final int HEADER_SIZE = 6;
    private static final String FILE_HEADER = "CardNumber,Name,Age,Pin,isAdmin,isJudge";
    private String fileName;
    private Map<Integer,User> userMap;
    private Map<Integer,User> judgeMap;


    public UserDatabaseManager(String filename)  {
        this.fileName = filename;
        userMap = new HashMap<>();
        judgeMap = new HashMap<>();


    }

    public User checkCredientals(int cardNumber, String pin) {
        //check user map first then judge map
        User user = null;
        if(userMap.containsKey(cardNumber)) {
            User foundUser = userMap.get(cardNumber);
            if(foundUser.getLoginCredential().equals(pin)) {
                user = foundUser;
            }
        } else if (judgeMap.containsKey(cardNumber)) {
            User foundUser = judgeMap.get(cardNumber);
            if(foundUser.getLoginCredential().equals(pin)) {
                user = foundUser;
            }
        }
        return user;
    }


    public Map<Integer,User> getUserMap() {
        return userMap;
    }

    public Map<Integer,User> getJudgeMap() {
        return judgeMap;
    }

    public void readCsvFile() {
        BufferedReader fileReader = null;
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));
            //Reading in the header
            fileReader.readLine();
            while((line = fileReader.readLine()) != null ) {
                String[] userInfo = line.split(COMMA_DELIMITER);
                if (userInfo.length > 0) {
                    User userData = new User(Integer.parseInt(userInfo[USER_CARD_NUMBER_IDX]),(userInfo[USER_NAME_IDX]),
                            Integer.parseInt(userInfo[USER_AGE_IDX]), (userInfo[USER_LOGIN_CREDENTIAL_IDX]),
                            Boolean.parseBoolean(userInfo[USER_IS_ADMIN_IDX]), Boolean.parseBoolean(userInfo[USER_IS_JUDGE_IDX]));
                    if (Boolean.parseBoolean(userInfo[USER_IS_JUDGE_IDX])) {
                        judgeMap.put(Integer.parseInt(userInfo[USER_CARD_NUMBER_IDX]), userData);
                    } else {
                        userMap.put(Integer.parseInt(userInfo[USER_CARD_NUMBER_IDX]), userData);
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}