package com.nullthinker.mentaldummy.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @Table(name="subject_details"
,uniqueConstraints = {@UniqueConstraint(columnNames="subject_name")}
)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject implements Serializable {
	
	private static final long serialVersionUID = -6894540606635777874L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subject_id")
	@XmlElement
	private int subjectID;
	
	@NotNull(message="Subject Name should not be null")
	@Size(min=1,max=100, message="Subject Name should be between 1 to 100 chars")
	@Column(name="subject_name")
	@XmlElement
	private String subjectName;
	
	@NotNull(message="Topics should not be null")
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="subject_id")
	@XmlElement
	List<Topic> topics;
	
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", subjectName=" + subjectName + ", topics=" + topics + "]";
	}
}
