	@Test
	public void setUserActivationKey_shouldCreateUserActivationKey() throws Exception {
		User u = new User();
		u.setPerson(new Person());
		u.addName(new PersonName("Benjamin", "A", "Wolfe"));
		u.setUsername("bwolfe");
		u.getPerson().setGender("M");
		Context.getAdministrationService().setGlobalProperty(OpenmrsConstants.GP_HOST_URL,
		    "http://localhost:8080/openmrs/admin/users/changePassword.form/{activationKey}");
		User createdUser = userService.createUser(u, "Openmr5xy");
		assertNull(dao.getLoginCredential(createdUser).getActivationKey());
		expectedException.expect(MessageException.class);
		assertEquals(createdUser, userService.setUserActivationKey(createdUser));
		assertNotNull(dao.getLoginCredential(createdUser).getActivationKey());
	}