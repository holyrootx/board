package comment.dto;

import java.sql.Timestamp;

public class commentDTO {
    private int comment_no;
    private int user_no;
    private int board_no;
    private String content;
    private Timestamp create_at;
    private Timestamp update_at;
    private Timestamp delete_at;
    private int ref;

    public int getComment_no() {
        return comment_no;
    }

    public void setComment_no(int comment_no) {
        this.comment_no = comment_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
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

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }
}
