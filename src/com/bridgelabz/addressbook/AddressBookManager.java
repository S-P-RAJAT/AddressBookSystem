package com.bridgelabz.addressbook;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AddressBookManager {
	public static final Scanner sc = new Scanner(System.in);
	public static final int bookCount[] = {0};

	public static HashMap<String, List<Contact>> contactNamesByCity = new HashMap<String, List<Contact>>();
	public static HashMap<String, List<Contact>> contactNamesByState = new HashMap<String, List<Contact>>();
	
	public void start() {
		boolean found = false;
		System.out.println("Welcome to Address Book Program\n");
		int choice, addressBookNumber = -1;
		List<AddressBookIF> addressBook = new ArrayList<>();

		do {
			System.out.println(
					"\n1.Create new Address Book \n2.Open Address Book \n3.Books List \n4.Search by City Name \n5.Search by State Name  \n6.Exit");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {

			case 1:


					System.out.print("Enter the address book name: ");
					String addressBookName = sc.nextLine();
					addressBook.add(new AddressBookImpl(addressBookName));

				break;

			case 2:
				System.out.print("Enter the address book name: ");
				Predicate<AddressBookIF> addressBookNamePredicate = n -> n.getAddressBookName().equals(sc.nextLine());
				AddressBookIF addressBookObject = addressBook.stream()
						.filter(addressBookNamePredicate)
						.findFirst()
						.orElse(null);

				if (addressBookObject ==null) {
					System.out.println("No book with such name exists!");
				} else{
					addressBookObject.openAddressbook();
				}
				break;

			case 3:
				if (addressBook.size() == 0) {
					System.out.println("No address books are created yet!");
				}
				bookCount[0] = 0;
				addressBook.stream().forEach(p -> System.out.println((++bookCount[0])+". "+p.getAddressBookName()));
				break;
			case 4:
				System.out.println("Enter the city name: ");
				String city = sc.nextLine();

				if (contactNamesByCity.get(city) == null) {
					System.out.println("No records founds with city name: " + city);
				} else {
					System.out.println("1. List all contacts in " + city + "\n2. Search for a person in " + city);
					if (sc.nextInt() == 2) {
						sc.nextLine();
						System.out.println("Enter First Name: ");
						String firstName = sc.nextLine();
						System.out.println("Enter Last Name: ");
						String lastName = sc.nextLine();
						found = false;
						for (Contact person : contactNamesByCity.get(city)) {
							if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
								found = true;
								System.out.println("Found!\n" + person);
							}
						}
						if (!found) {
							System.out.println("No users exist with such name in " + city);
						}
					} else {
						int count = 0;
						StringBuilder output = new StringBuilder();
						for (Contact person : contactNamesByCity.get(city)) {
							output.append(person.toString());
							count++;
						}
						System.out.println(count + " records found!\n" + output);
					}

				}
				break;

			case 5:
				System.out.println("Enter the state name: ");
				String state = sc.nextLine();
				if (contactNamesByState.get(state) == null) {
					System.out.println("No records founds with state name: " + state);
				} else {
					System.out.println("1. List all contacts in " + state + "\n2. Search for a person in " + state);
					if (sc.nextInt() == 2) {
						sc.nextLine();

						System.out.println("Enter First Name: ");
						String firstName = sc.nextLine();
						System.out.println("Enter Last Name: ");
						String lastName = sc.nextLine();
						found = false;
						for (Contact person : contactNamesByState.get(state)) {
							if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
								found = true;
								System.out.println("Found!\n" + person);
							}
						}
						if (!found) {
							System.out.println("No users exist with such name in " + state);
						}
					} else {
						int count = 0;
						StringBuilder output = new StringBuilder();
						for (Contact person : contactNamesByState.get(state)) {
							output.append(person.toString());
							count++;
						}
						System.out.println(count + " records found!\n" + output);
					}
				}
				break;
			case 6:
				break;

			default:
				System.out.println("Choose correct option from above mentioned option only!!");
				break;
			}
		} while (choice != 6);
		sc.close();
	}
}
