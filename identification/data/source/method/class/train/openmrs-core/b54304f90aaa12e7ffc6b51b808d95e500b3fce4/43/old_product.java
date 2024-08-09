public int compare(User user1, User user2) {
		
		// test for null cases (sorting them to be last in a list)
		if (user1 == null) {
			return 1;
		} else if (user2 == null) {
			return -1;
		}
		
		// delegate to the personByNameComparator to sort by person names
		return PersonByNameComparator.comparePersonsByName(user1.getPerson(), user2.getPerson());
	}