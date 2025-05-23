@Test
	@Verifies(value = "should update encounter successfully", method = "saveEncounter(Encounter)")
	public void saveEncounter_shouldUpdateEncounterSuccessfully()
			throws Exception {
		EncounterService es = Context.getEncounterService();

		// get the encounter from the database
		Encounter encounter = es.getEncounter(1);

		// save the current values for comparison later
		Patient origPatient = encounter.getPatient();
		Location origLocation = encounter.getLocation();
		Date origDate = encounter.getEncounterDatetime();
		EncounterType origEncType = encounter.getEncounterType();

		// add values that are different than the ones in the initialData.xml
		// file
		Location loc2 = new Location(2);
		EncounterType encType2 = new EncounterType(2);
		Date d2 = new Date();
		Patient pat2 = new Patient(2);

		encounter.setLocation(loc2);
		encounter.setEncounterType(encType2);
		encounter.setEncounterDatetime(d2);
		encounter.setPatient(pat2);

		// save to the db
		es.saveEncounter(encounter);

		// fetch that encounter from the db
		Encounter newestEnc = es.getEncounter(encounter.getEncounterId());

		assertFalse("The location should be different", origLocation
				.equals(loc2));
		assertTrue("The location should be different", newestEnc.getLocation()
				.equals(loc2));
		assertFalse("The enc should have changed", origEncType.equals(encType2));
		assertTrue("The enc type needs to have been set", newestEnc
				.getEncounterType().equals(encType2));
		assertFalse("Make sure the dates changed slightly", origDate.equals(d2));
		assertTrue("The date needs to have been set", newestEnc
				.getEncounterDatetime().equals(d2));
		assertFalse("The patient should be different", origPatient.equals(pat2));
		assertTrue("The patient should have been set", newestEnc.getPatient()
				.equals(pat2));
	}