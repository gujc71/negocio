package gu.survey;

public class SurveyVO {
	private String bgno;
	private String surno;
	private String surtitle;
	private String surcontents;
	private String surdate;
	private String surperiod;
	private String surstartdate;
	private String surenddate;
	private String questioncount;
	private String surstate;
	private String userno;
	private String usernm;
	private String surresponsor;
	
	public String getSurresponsor() {
		return surresponsor;
	}
	public void setSurresponsor(String surresponsor) {
		this.surresponsor = surresponsor;
	}
	public String getBgno() {
		return bgno;
	}
	public void setBgno(String bgno) {
		this.bgno = bgno;
	}
	public String getSurno() {
		return surno;
	}
	public void setSurno(String surno) {
		this.surno = surno;
	}
	public String getSurtitle() {
		return surtitle;
	}
	public void setSurtitle(String surtitle) {
		this.surtitle = surtitle;
	}
	public String getSurdate() {
		return surdate;
	}
	public void setSurdate(String surdate) {
		this.surdate = surdate;
	}
	public String getSurperiod() {
		return surperiod;
	}
	public void setSurperiod(String surperiod) {
		this.surperiod = surperiod;
	}
	public String getQuestioncount() {
		return questioncount;
	}
	public void setQuestioncount(String questioncount) {
		this.questioncount = questioncount;
	}
	public String getSurstate() {
		return surstate;
	}
	public void setSurstate(String surstate) {
		this.surstate = surstate;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getSurstartdate() {
		return surstartdate;
	}
	public void setSurstartdate(String surstartdate) {
		this.surstartdate = surstartdate;
	}
	public String getSurenddate() {
		return surenddate;
	}
	public void setSurenddate(String surenddate) {
		this.surenddate = surenddate;
	}
	public String getSurcontents() {
		return surcontents;
	}
	public void setSurcontents(String surcontents) {
		this.surcontents = surcontents;
	}
	@Override
	public String toString() {
		String reval = "bgno = " + this.bgno 
				+ " questioncount= " + this.questioncount
				+ " surdate= " + this.surdate
				+ " surno= " + this.surno
				+ " surperiod= " + this.surperiod
				+ " surstartdate= " + this.surstartdate
				+ " surenddate= " + this.surenddate
				+ " surresponsor= " + this.surresponsor
				+ " surstate= " + this.surstate
				+ " surtitle= " + this.surtitle
				+ " surcontents= " + this.surcontents
				+ " usernm= " + this.usernm
				+ " userno= " + this.userno;
		return reval;
	}	
}
