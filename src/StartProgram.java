/**
 * @author Adell - amrogers5
 * CIS175 - Spring 2023
 * Feb 5, 2023
 */

import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.VocabularyItem;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListItemHelper lih = new ListItemHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a word: ");
			String word = in.nextLine();
			System.out.print("Enter its definition: ");
			String definition = in.nextLine();
			
			VocabularyItem toAdd = new VocabularyItem(word, definition);
			lih.insertItem(toAdd);
		}

		private static void deleteAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter the word to delete: ");
			String word = in.nextLine();
			System.out.print("Enter the definition to delete: ");
			String definition = in.nextLine();
			
			VocabularyItem toDelete = new VocabularyItem(word, definition);
			lih.deleteItem(toDelete);
		}

		private static void editAnItem() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Word");
			System.out.println("2 : Search by Definition");
			int searchBy = in.nextInt();
			in.nextLine();
			
			List<VocabularyItem> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the word: ");
				String wordName = in.nextLine();
				foundItems = lih.searchForVocabByWord(wordName);
				
			} else {
				System.out.print("Enter the definition: ");
				String definitionName = in.nextLine();
				foundItems = lih.searchForVocabByDefinition(definitionName);

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (VocabularyItem l : foundItems) {
					System.out.println(l.getID() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				VocabularyItem toEdit = lih.searchForVocabById(idToEdit);
				System.out.println("Retrieved " + toEdit.getDefinition() + " from " + toEdit.getWord());
				System.out.println("1 : Update Word");
				System.out.println("2 : Update Definition");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Word: ");
					String newWord = in.nextLine();
					toEdit.setWord(newWord);
				} else if (update == 2) {
					System.out.print("New Defintion: ");
					String newDefinition = in.nextLine();
					toEdit.setDefinition(newDefinition);
				}

				lih.updateVocab(toEdit);

			} else {
				System.out.println("---- No results found");
			}
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our useful vocabulary list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add a vocab item");
				System.out.println("*  2 -- Edit a vocab item");
				System.out.println("*  3 -- Delete a vocab item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   じゃ、またね!   "); //"See ya!" (goodbye!)
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<VocabularyItem> allItems = lih.showAllItems();
			for(VocabularyItem singleItem : allItems) {
				System.out.println(singleItem.returnVocabularyDetails());
			}
		}

	}

