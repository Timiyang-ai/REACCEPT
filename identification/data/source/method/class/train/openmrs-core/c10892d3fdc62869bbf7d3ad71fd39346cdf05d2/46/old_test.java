	@Test
	@SkipBaseSetup
	public void getUsersByName_shouldFetchUsersExactlyMatchingTheGivenGivenNameAndFamilyName() throws SQLException {
		initializeInMemoryDatabase();
		executeDataSet(XML_FILENAME);
		authenticate();

		// this generates an error:
		// org.hibernate.QueryException: illegal attempt to dereference 
		// collection [user0_.user_id.names] with element property reference [givenName] 
		// [from org.openmrs.User u where u.names.givenName = :givenName and u.names.familyName 
		// = :familyName and u.voided = false]
		List<User> users = userService.getUsersByName("Susy", "Kingman", false);
		assertEquals(1, users.size());
	}