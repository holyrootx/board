package board.dto;
import java.sql.Timestamp;
public class BoardDetailDTO {
    private String board_author;
    private int board_no;
    private String board_title;
    private String board_content;
    private Timestamp board_create_at;
    private Timestamp board_update_at;
    private String comment_author;
    private Timestamp comment_create_at;
    private String comment_content;
    private Timestamp comment_update_at;

    public String getBoard_author() {
        return board_author;
    }

    public void setBoard_author(String board_author) {
        this.board_author = board_author;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public Timestamp getBoard_create_at() {
        return board_create_at;
    }

    public void setBoard_create_at(Timestamp board_create_at) {
        this.board_create_at = board_create_at;
    }

    public Timestamp getBoard_update_at() {
        return board_update_at;
    }

    public void setBoard_update_at(Timestamp board_update_at) {
        this.board_update_at = board_update_at;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public Timestamp getComment_create_at() {
        return comment_create_at;
    }

    public void setComment_create_at(Timestamp comment_create_at) {
        this.comment_create_at = comment_create_at;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Timestamp getComment_update_at() {
        return comment_update_at;
    }

    public void setComment_update_at(Timestamp comment_update_at) {
        this.comment_update_at = comment_update_at;
    }
}
