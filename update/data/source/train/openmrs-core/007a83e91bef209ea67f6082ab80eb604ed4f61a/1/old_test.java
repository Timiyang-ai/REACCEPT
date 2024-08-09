@Test
	@Verifies(value = "should return a set of locales with no duplicates", method = "getLocalesInOrder()")
	public void getLocalesInOrder_shouldReturnASetOfLocalesWithNoDuplicates() throws Exception {
		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST,
		        "lu_UG, lu, sw_KE, en_US, en, en, sw_KE", "Test Allowed list of locales");
		Context.getAdministrationService().saveGlobalProperty(gp);
		GlobalProperty defaultLocale = new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE, "lu",
		        "Test Allowed list of locales");
		Context.getAdministrationService().saveGlobalProperty(defaultLocale);
		Locale lu_UG = new Locale("lu", "UG");
		Context.setLocale(lu_UG);
		//note that unique list of locales should be lu_UG, lu, sw_KE, en_US, en
		Assert.assertEquals(5, LocaleUtility.getLocalesInOrder().size());
	}