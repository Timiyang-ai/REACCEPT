	@Test
	public void getPatientIdentifiers_shouldLimitByResultsByLocation() {
		Location location = Context.getLocationService().getLocation(3); // there is only one identifier in the test database for location 3
		List<PatientIdentifier> patientIdentifiers = dao.getPatientIdentifiers(null, new ArrayList<>(),
		    Collections.singletonList(location), new ArrayList<>(), null);
		Assert.assertEquals(1, patientIdentifiers.size());
		Assert.assertEquals("12345K", patientIdentifiers.get(0).getIdentifier());
	}