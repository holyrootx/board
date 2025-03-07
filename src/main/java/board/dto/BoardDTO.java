package board.dto;
import java.sql.Timestamp;

public class BoardDTO {
    private int board_no;
    private int user_no;
    private String title;
    private String content;
    private Timestamp create_at;
    private Timestamp update_at;
    private Timestamp delete_at;

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public Timestamp getDelete_at() {
        return delete_at;
    }

    public void setDelete_at(Timestamp delete_at) {
        this.delete_at = delete_at;
    }
}