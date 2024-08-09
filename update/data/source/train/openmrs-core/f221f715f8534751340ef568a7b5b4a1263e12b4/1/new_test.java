@Test
	public void getSearchLocales_shouldIncludeCurrentlySelectedFullLocaleAndLangugage() throws Exception {
		//given
		Context.getAdministrationService().saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST, "en_GB"));
		User user = Context.getAuthenticatedUser();
		user.setUserProperty(OpenmrsConstants.USER_PROPERTY_PROFICIENT_LOCALES, "");
		Context.getUserService().saveUser(user, null);
		Context.setLocale(new Locale("en", "GB"));
		
		//when
		List<Locale> searchLocales = Context.getAdministrationService().getSearchLocales(Context.getAuthenticatedUser());
		
		//then
		Assert.assertEquals(Context.getLocale(), searchLocales.get(0));
		Assert.assertEquals(new Locale(Context.getLocale().getLanguage()), searchLocales.get(1));
	}