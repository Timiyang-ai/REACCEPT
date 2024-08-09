@Test
	public void getSearchLocales_shouldExcludeNotAllowedLocales() throws Exception {
		//given
		Context.getAdministrationService().saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST, "en_US, pl, es"));
		
		User user = Context.getAuthenticatedUser();
		user.setUserProperty(OpenmrsConstants.USER_PROPERTY_PROFICIENT_LOCALES, "es_CL, en_US, pl");
		Context.getUserService().saveUser(user, null);
		
		//when
		List<Locale> searchLocales = Context.getAdministrationService().getSearchLocales(Context.getAuthenticatedUser());
		
		//then
		Assert.assertTrue("en_US", searchLocales.contains(new Locale("en", "US")));
		Assert.assertTrue("pl", searchLocales.contains(new Locale("pl")));
		Assert.assertTrue("es", searchLocales.contains(new Locale("es")));
		Assert.assertFalse("es_CL", searchLocales.contains(new Locale("es", "CL")));
	}