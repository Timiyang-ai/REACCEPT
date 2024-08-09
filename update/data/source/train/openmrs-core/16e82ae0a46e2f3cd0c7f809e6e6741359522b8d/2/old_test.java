@Test
	@Verifies(value = "should delete property from database if not in list", method = "saveGlobalProperties(List<QGlobalProperty;>)")
	public void saveGlobalProperties_shouldDeletePropertyFromDatabaseIfNotInList() throws Exception {
		List<GlobalProperty> globalProperties = Context.getAdministrationService().getAllGlobalProperties();
		GlobalProperty firstGlobalProperty = globalProperties.remove(0);
		Context.getAdministrationService().saveGlobalProperties(globalProperties);
		Assert.assertNull(Context.getAdministrationService().getGlobalProperty(firstGlobalProperty.getProperty()));
	}