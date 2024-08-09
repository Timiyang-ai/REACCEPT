@Test
	public void getSearchLocales_shouldIncludeCurrentlySelectedFullLocaleAndLangugage() throws Exception {
		//when
		List<Locale> searchLocales = Context.getAdministrationService().getSearchLocales(Context.getAuthenticatedUser());
		
		//then
		Assert.assertEquals(Context.getLocale(), searchLocales.get(0));
		Assert.assertEquals(new Locale(Context.getLocale().getLanguage()), searchLocales.get(1));
	}