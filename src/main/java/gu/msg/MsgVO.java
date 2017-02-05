package gu.msg;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class MsgVO {

    private String msgno;
    private String msgcontent; 
    private String senuser; 
    private String revuser; 
    private String sendate; 
    private String revdate;  
    private String sendelyn; 
    private String fileyn;
    private String revdelyn;
    private String userno;
    
    /* 첨부파일 */
    private List<MultipartFile> uploadfile;


    public String getMsgno() {
        return msgno;
    }

    public void setMsgno(String msgno) {
        this.msgno = msgno;
    }

    public String getMsgcontent() {
        return msgcontent; 
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public String getSenuser() {
        return senuser;
    }

    public void setSenuser(String senuser) {
        this.senuser = senuser;
    }

    public String getRevuser() {
        return revuser.replaceAll("(?i)<script", "&lt;script");
    }

    public void setRevuser(String revuser) {
        this.revuser = revuser;
    }

    public String getSendate() {
        return sendate;
    }

    public void setSendate(String sendate) {
        this.sendate = sendate;
    }

    public String getRevdate() {
        return revdate;
    }

    public void setRevdate(String revdate) {
        this.revdate = revdate;
    }

    public String getsendelyn() {
        return sendelyn;
    }

    public void setsendelyn(String sendelyn) {
        this.sendelyn = sendelyn;
    }

    public List<MultipartFile> getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(List<MultipartFile> uploadfile) {
        this.uploadfile = uploadfile;
    }

    public String getFilecnt() {
        return fileyn;
    }

    public void setFilecnt(String fileyn) {
        this.fileyn = fileyn;
    }

    public String getRevdelyn() {
        return revdelyn;
    }

    public void setRevdelyn(String revdelyn) {
        this.revdelyn = revdelyn;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

}
