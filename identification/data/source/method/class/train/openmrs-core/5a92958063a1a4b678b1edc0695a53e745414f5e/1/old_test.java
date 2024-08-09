@Test
	@Verifies(value = "should get encounters by type", method = "getEncounters(Patient,Location,Date,Date,Collection<QForm;>,Collection<QEncounterType;>,null)")
	public void getEncounters_shouldGetEncountersByType() throws Exception {
		List<EncounterType> types = new Vector<EncounterType>();
		types.add(new EncounterType(1));
		List<Encounter> encounters = Context.getEncounterService().getEncounters(null, null, null, null, null, types, true);
		assertEquals(5, encounters.size());
	}