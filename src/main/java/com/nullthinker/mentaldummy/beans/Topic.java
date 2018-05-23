package com.nullthinker.mentaldummy.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="subtopic_details")
@XmlRootElement
public class Topic implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -1107383971269126634L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subtopic_id",nullable = false)
	@XmlElement
	private int topicID;
	
	@Column(name="subtopic_name")
	@XmlElement
	@NotNull(message="Topic Name should not be null")
	@Size(min=1,max=100, message="Subtopic name should be between 1 to 100")
	private String topicName;
	
	@ManyToOne
	private Subject subject;
	
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "Topic [topicID=" + topicID + ", topicName=" + topicName + ", subject=" + subject + "]";
	}
}
