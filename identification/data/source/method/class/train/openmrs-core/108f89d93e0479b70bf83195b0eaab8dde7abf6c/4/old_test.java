	@Test
	public void createUser_shouldCreateNewUserWithBasicElements() {
		assertTrue("The context needs to be correctly authenticated to by a user", Context.isAuthenticated());

		User u = new User();
		u.setPerson(new Person());
		
		u.addName(new PersonName("Benjamin", "A", "Wolfe"));
		u.setUsername("bwolfe");
		u.getPerson().setGender("M");
		
		User createdUser = userService.createUser(u, "Openmr5xy");
		
		// if we're returning the object from create methods, check validity
		assertTrue("The user returned by the create user method should equal the passed in user", createdUser.equals(u));
		
		createdUser = userService.getUserByUsername("bwolfe");
		assertTrue("The created user should equal the passed in user", createdUser.equals(u));
	}