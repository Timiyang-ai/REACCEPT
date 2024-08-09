	@Test
	public void getAllowedLocales_shouldNotFailIfNotGlobalPropertyForLocalesAllowedDefinedYet() {
		adminService.purgeGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_LOCALE_ALLOWED_LIST));
		adminService.getAllowedLocales();
	}