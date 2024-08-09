	@Test
	public void getSearchLocales_shouldExcludeNotAllowedLocales() {
		//given
		adminService.saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST, "en_US, en_GB, pl, es"));
		
		User user = Context.getAuthenticatedUser();
		user.setUserProperty(OpenmrsConstants.USER_PROPERTY_PROFICIENT_LOCALES, "es_CL, en_US, pl");
		Context.getUserService().saveUser(user);
		
		//when
		List<Locale> searchLocales = adminService.getSearchLocales();
		
		//then
		assertTrue("en_US", searchLocales.contains(new Locale("en", "US")));
		assertTrue("pl", searchLocales.contains(new Locale("pl")));
		assertTrue("es", searchLocales.contains(new Locale("es")));
		assertFalse("es_CL", searchLocales.contains(new Locale("es", "CL")));
	}