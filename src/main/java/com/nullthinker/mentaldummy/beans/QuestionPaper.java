package com.nullthinker.mentaldummy.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CollectionId;

import org.hibernate.annotations.Type;

@Entity
@Table(name="question_bank")
public class QuestionPaper implements Serializable {
	
	private static final long serialVersionUID = 1031975032076728057L;

	@Id @Column(name="question_paper_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private BigInteger questionPaperID;
	
	@Column(name="subject_name")
	@NotNull(message="Subject Name should not be null")
	@Size(min=1,max=100, message="Subject Name should be between 1 to 100 chars")
	private String subjectName;
	
	@Column(name="subtopic_name")
	@NotNull(message="Topic Name should not be null")
	@Size(min=1,max=100, message="Subtopic name should be between 1 to 100")
	private String subtopicName;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="questions_list",joinColumns= {@JoinColumn(name="question_paper_id")})
	@Column(name="questions")
	@Lob
//	@GenericGenerator(name="id_gen", strategy="identity")
	@SequenceGenerator(name="id_gen")
	@CollectionId(
			columns = @Column(name="question_id"), 
	        type=@Type(type="integer"), 
	        generator = "id_gen"
			)
	private List<StringBuilder> questions;
	
	@Column(name="user_id")
	private BigInteger submittedBy;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "questionPaper", cascade = CascadeType.ALL)
	private QuestionPaperOtherDetails otherDetails;
	
	public BigInteger getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(BigInteger submittedBy) {
		this.submittedBy = submittedBy;
	}

	public BigInteger getQuestionPaperID() {
		return questionPaperID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubtopicName() {
		return subtopicName;
	}

	public void setSubtopicName(String subtopicName) {
		this.subtopicName = subtopicName;
	}

	public List<StringBuilder> getQuestions() {
		return questions;
	}

	public void setQuestions(List<StringBuilder> questions) {
		this.questions = questions;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public QuestionPaperOtherDetails getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(QuestionPaperOtherDetails otherDetails) {
		this.otherDetails = otherDetails;
	}
	
}
