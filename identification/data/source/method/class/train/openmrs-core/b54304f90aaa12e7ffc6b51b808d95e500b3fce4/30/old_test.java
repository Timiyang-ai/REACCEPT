	@Test
	public void compare_shouldSortUsersByPersonNames() {
		
		Person person1 = new Person();
		person1.addName(new PersonName("givenName", "middleName", "familyName"));
		User user1 = new User(person1);
		Person person2 = new Person();
		person2.addName(new PersonName("givenName", "middleNamf", "familyName"));
		User user2 = new User(person2);
		Person person3 = new Person();
		person3.addName(new PersonName("givenName", "middleNamg", "familyName"));
		User user3 = new User(person3);
		Person person4 = new Person();
		person4.addName(new PersonName("givenName", "middleNamh", "familyName"));
		User user4 = new User(person4);
		
		List<User> listToSort = new ArrayList<>();
		// add the users randomly
		listToSort.add(user3);
		listToSort.add(user1);
		listToSort.add(user4);
		listToSort.add(user2);
		
		// sort the list with userByNameComparator
		listToSort.sort(new UserByNameComparator());
		
		// make sure that the users are sorted in the expected order
		Iterator<User> it = listToSort.iterator();
		Assert.assertTrue("Expected user1 to be the first in the sorted user list but wasn't", user1.equals(it.next()));
		Assert.assertTrue("Expected user2 to be the second in the sorted user list but wasn't", user2.equals(it.next()));
		Assert.assertTrue("Expected user3 to be the third in the sorted user list but wasn't", user3.equals(it.next()));
		Assert.assertTrue("Expected user4 to be the fourth in the sorted user list but wasn't", user4.equals(it.next()));
	}