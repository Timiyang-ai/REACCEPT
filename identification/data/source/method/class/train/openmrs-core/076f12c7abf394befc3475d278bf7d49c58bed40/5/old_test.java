	@Test
	public void getPatients_shouldForceSearchStringToBeGreaterThanMinsearchcharactersGlobalProperty() throws Exception {
		// make sure we can get patients with the default of 3
		assertEquals(1, Context.getPatientService().getPatients("Colle").size());
		
		Context.clearSession();
		Context.getAdministrationService().saveGlobalProperty(
		    new GlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_MIN_SEARCH_CHARACTERS, "4"));
		
		assertEquals(0, Context.getPatientService().getPatients("Col").size());
	}