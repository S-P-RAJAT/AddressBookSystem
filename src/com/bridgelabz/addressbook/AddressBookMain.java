package com.bridgelabz.addressbook;

import java.util.Scanner;

public class AddressBookMain {
	static AddressBookService addressBook = new AddressBookService();
	public static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to Address Book Program");
		int choice;
		do {
			System.out.println("1.Add Contact \n2.View Contact \n3.Edit Contact \n4.Exit");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addressBook.addContact();
				break;
			case 2:
				addressBook.viewContact();
				break;
			case 3:
				addressBook.editContact();
				break;
			case 4:
				break;
			default:
				System.out.println("Choose correct option from above mentioned option only!!");
				break;
			}
		} while (choice != 4);
		sc.close();
	}
}
