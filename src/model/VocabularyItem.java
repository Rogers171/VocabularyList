/**
 * @author Adell - amrogers5
 * CIS175 - Spring 2023
 * Feb 5, 2023
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vocabulary")
public class VocabularyItem {
	//Attributes
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="Word")
	private String word;
	
	@Column(name="Definition")
	private String definition;
	
	
	//Getters and Setters
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	//Constructors
	public VocabularyItem() {
		super();
	}
	public VocabularyItem(String word, String definition) {
		super();
		this.word = word;
		this.definition = definition;
	}
	
	//Helper Method
	public String returnVocabularyDetails() {
		return this.word + ": " + this.definition;
	}
}
