package user.dto;
import java.sql.Date;
public class UserDTO {
    private int user_no;
    private String user_id;
    private String user_pass;
    private String user_email;
    private String user_name;
    private String user_gender;
    private Date user_birth;

    public UserDTO(){
        super();
    }
    public UserDTO(String user_id, String user_pass, String user_email, String user_name, String user_gender, Date user_birth) {
        this.user_id = user_id;
        this.user_pass = user_pass;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_birth = user_birth;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "user_no=" + user_no +
                ", user_id='" + user_id + '\'' +
                ", user_pass='" + user_pass + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_birth=" + user_birth +
                '}';
    }
}
