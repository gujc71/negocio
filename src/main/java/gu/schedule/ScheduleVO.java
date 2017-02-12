package gu.schedule;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class ScheduleVO {

    private String schno;
    private String schwriter;
    private Date schstdt;
    private Date scheddt; 
    private String schrep; 
    private String schplace; 
    private String schtitle; 
    private String schcont;  
    private String schmember; 
    private String schopen;
    private String schday;
	private String schkn;
    private String schsttm;
    private String schedtm;
    
    
    public String getSchkn() {
		return schkn;
	}
	public void setSchkn(String schkn) {
		this.schkn = schkn;
	}
	public String getSchsttm() {
		return schsttm;
	}
	public void setSchsttm(String schsttm) {
		this.schsttm = schsttm;
	}
	public String getSchedtm() {
		return schedtm;
	}
	public void setSchedtm(String schedtm) {
		this.schedtm = schedtm;
	}
	
	public String getSchno() {
		return schno;
	}
	public void setSchno(String schno) {
		this.schno = schno;
	}
	public String getSchwriter() {
		return schwriter;
	}
	public void setSchwriter(String schwriter) {
		this.schwriter = schwriter;
	}
	public Date getSchstdt() {
		return schstdt;
	}
	public void setSchstdt(Date schstdt) {
		this.schstdt = schstdt;
	}
	public Date getScheddt() {
		return scheddt;
	}
	public void setScheddt(Date scheddt) {
		this.scheddt = scheddt;
	}
	public String getSchrep() {
		return schrep;
	}
	public void setSchrep(String schrep) {
		this.schrep = schrep;
	}
	public String getSchplace() {
		return schplace;
	}
	public void setSchplace(String schplace) {
		this.schplace = schplace;
	}
	public String getSchtitle() {
		return schtitle;
	}
	public void setSchtitle(String schtitle) {
		this.schtitle = schtitle;
	}
	public String getSchcont() {
		return schcont;
	}
	public void setSchcont(String schcont) {
		this.schcont = schcont;
	}
	public String getSchmember() {
		return schmember;
	}
	public void setSchmember(String schmember) {
		this.schmember = schmember;
	}
	public String getSchopen() {
		return schopen;
	}
	public void setSchopen(String schopen) {
		this.schopen = schopen;
	}
	public String getSchday() {
		return schday;
	}
	public void setSchday(String schday) {
		this.schday = schday;
	}

    
 
    
}
