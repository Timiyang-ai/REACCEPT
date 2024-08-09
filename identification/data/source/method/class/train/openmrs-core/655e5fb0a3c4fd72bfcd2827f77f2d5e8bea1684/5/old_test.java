	@Test
	public void becomeUser_shouldChangeLocaleWhenBecomeAnotherUser() {
		UserService userService = Context.getUserService();
		
		User user = new User(new Person());
		user.addName(new PersonName("givenName", "middleName", "familyName"));
		user.getPerson().setGender("M");
		user.setUserProperty(OpenmrsConstants.USER_PROPERTY_DEFAULT_LOCALE, "pt_BR");
		userService.createUser(user, "TestPass123");
		
		Context.becomeUser(user.getSystemId());
		
		Locale locale = Context.getLocale();
		Assert.assertEquals("pt", locale.getLanguage());
		Assert.assertEquals("BR", locale.getCountry());
		
		Context.logout();
	}