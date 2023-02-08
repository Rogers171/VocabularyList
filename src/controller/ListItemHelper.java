/**
 * @author Adell - amrogers5
 * CIS175 - Spring 2023
 * Feb 5, 2023
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.VocabularyItem;

public class ListItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VocabularyList");
	
	public void insertItem(VocabularyItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<VocabularyItem>showAllItems(){
		EntityManager em= emfactory.createEntityManager();
		List<VocabularyItem> allItems = em.createQuery("SELECT i FROM VocabularyItem i").getResultList();
		return allItems;
	}
	
	public void deleteItem(VocabularyItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VocabularyItem> typedQuery = em.createQuery("select li from VocabularyItem li where li.word = :selectedWord and li.definition = :selectedDefinition", VocabularyItem.class);
		
		typedQuery.setParameter("selectedWord", toDelete.getWord());
		typedQuery.setParameter("selectedDefinition", toDelete.getDefinition());
		
		typedQuery.setMaxResults(1);
		
		VocabularyItem result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public VocabularyItem searchForVocabById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		VocabularyItem found = em.find(VocabularyItem.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updateVocab(VocabularyItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	/**
	 * @param wordName
	 * @return
	 */
	public List<VocabularyItem> searchForVocabByWord(String wordName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VocabularyItem> typedQuery = em.createQuery("select li from VocabularyItem li where li.word = :selectedWord", VocabularyItem.class);
		typedQuery.setParameter("selectedWord", wordName);
		List<VocabularyItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * @param definitionName
	 * @return
	 */
	public List<VocabularyItem> searchForVocabByDefinition(String definitionName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<VocabularyItem> typedQuery = em.createQuery("select li from VocabularyItem li where li.definition = :selectedDefinition", VocabularyItem.class);
		typedQuery.setParameter("selectedDefinition", definitionName);
		List<VocabularyItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
