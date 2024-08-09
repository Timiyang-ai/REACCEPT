	@Test
	public void getDefaultLocale_shouldNotFailWithBogusGlobalPropertyValue() {
		Context.getAdministrationService().saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST, "en_GB, asdfasdf"));
		
		Context.getAdministrationService().saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE, "asdfasdf"));
		
		// check for nonnullness
		Assert.assertNotNull(LocaleUtility.getDefaultLocale());
		
		Context.getAdministrationService().saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE, ""));
	}