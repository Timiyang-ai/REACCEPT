	@Test
	public void getEncountersNotAssignedToAnyVisit_shouldReturnTheUnvoidedEncountersNotAssignedToAnyVisit() {
		executeDataSet(UNIQUE_ENC_WITH_PAGING_XML);
		List<Encounter> encs = Context.getEncounterService().getEncountersNotAssignedToAnyVisit(
		    Context.getPatientService().getPatient(10));
		Assert.assertEquals(2, encs.size());
		Assert.assertNull(encs.get(0).getVisit());
		Assert.assertNull(encs.get(1).getVisit());
	}