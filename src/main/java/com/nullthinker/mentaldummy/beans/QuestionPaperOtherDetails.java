package com.nullthinker.mentaldummy.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="question_other_details")
public class QuestionPaperOtherDetails implements Serializable{
	
	private static final long serialVersionUID = -7863191837724449300L;

	@Id @Column(name="other_details_id") 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int otherDetailsID;
	
	@Column(name="duration")
	@NotNull(message="Duration should not be null")
	private int duration;
	
	@Column(name="passing_marks")
	private double passingMarks;
	
	@Column(name="pass_key")
	@NotNull(message="Pass key should not be null")
	@Size(min=1,max=20,message="Pass key should be between 1 to 20 chars")
	private String passKey;
	
	@Column(name="diff_level")
	@NotNull(message="Diffeculty Level should not be null")
	@Size(min=1,max=6,message="It should be between 1 to 6 chars")
	private String diffLevel;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private QuestionPaper questionPaper;

	public int getOtherDetailsID() {
		return otherDetailsID;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPassingMarks() {
		return passingMarks;
	}

	public void setPassingMarks(double passingMarks) {
		this.passingMarks = passingMarks;
	}

	public String getPassKey() {
		return passKey;
	}

	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}

	public String getDiffLevel() {
		return diffLevel;
	}

	public void setDiffLevel(String diffLevel) {
		this.diffLevel = diffLevel;
	}

	public QuestionPaper getQuestionPaper() {
		return questionPaper;
	}

	public void setQuestionPaper(QuestionPaper questionPaper) {
		this.questionPaper = questionPaper;
	}

	@Override
	public String toString() {
		return "QuestionPaperOtherDetails [otherDetailsID=" + otherDetailsID + ", duration=" + duration
				+ ", passingMarks=" + passingMarks + ", passKey=" + passKey + ", diffLevel=" + diffLevel
				+ ", questionPaper=" + questionPaper + "]";
	}
	
	
}
