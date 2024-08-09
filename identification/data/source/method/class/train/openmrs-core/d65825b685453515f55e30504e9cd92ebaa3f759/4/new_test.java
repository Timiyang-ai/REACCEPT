@Test
	@Verifies(value = "should create new user with basic elements", method = "saveUser(User,String)")
	public void saveUser_shouldCreateNewUserWithBasicElements() throws Exception {
		assertTrue("The context needs to be correctly authenticated to by a user", Context.isAuthenticated());
		
		UserService us = Context.getUserService();
		
		User u = new User();
		u.setPerson(new Person());
		
		u.addName(new PersonName("Benjamin", "A", "Wolfe"));
		u.setUsername("bwolfe");
		u.getPerson().setGender("M");
		
		User createdUser = us.createUser(u, "Openmr5xy");
		
		// if we're returning the object from create methods, check validity
		assertTrue("The user returned by the create user method should equal the passed in user", createdUser.equals(u));
		
		createdUser = us.getUserByUsername("bwolfe");
		assertTrue("The created user should equal the passed in user", createdUser.equals(u));
	}