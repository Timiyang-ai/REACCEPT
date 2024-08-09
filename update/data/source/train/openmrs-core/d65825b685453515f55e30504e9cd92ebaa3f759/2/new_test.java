@Test
	@Verifies(value = "should escape sql wildcards in searchPhrase", method = "getUsers(String, List, Boolean)")
	public void getUsers_shouldEscapeSqlWildcardsInSearchPhrase() throws Exception {
		
		User u = new User();
		u.setPerson(new Person());
		u.getPerson().setGender("M");
		
		String wildcards[] = new String[] { "_" }; // we used to also test %, but UserValidator actually doesn't allow that in usernames. TODO: remove the loop
		//for each of the wildcards in the array, insert a user with a username or names
		//with the wildcards and carry out a search for that user
		for (String wildcard : wildcards) {
			
			PersonName name = new PersonName(wildcard + "cats", wildcard + "and", wildcard + "dogs");
			name.setDateCreated(new Date());
			u.addName(name);
			u.setUsername(wildcard + "test" + wildcard);
			Context.getUserService().createUser(u, "Openmr5xy");
			
			//we expect only one matching name or or systemId  to be returned
			int size = dao.getUsers(wildcard + "ca", null, false, null, null).size();
			assertEquals(1, size);
			
			//if actually the search returned the matching name or system id
			String userName = (dao.getUsers(wildcard + "ca", null, false, null, null).get(0).getUsername());
			assertEquals("Test failed since no user containing the character " + wildcard + " was found, ", wildcard
			        + "test" + wildcard, userName);
			
		}
	}