@Test
	public void saveEncounter_shouldCascadeSaveToContainedObs() {
		EncounterService es = Context.getEncounterService();
		// First, create a new Encounter
		Encounter enc = buildEncounter();
		
		//add an obs to the encounter
		Obs groupObs = new Obs();
		Concept c = Context.getConceptService().getConcept(1);
		groupObs.setConcept(c);
		
		// add an obs to the group
		Obs childObs = new Obs();
		childObs.setConcept(c);
		childObs.setValueNumeric(50d);
		groupObs.addGroupMember(childObs);
		enc.addObs(groupObs);
		
		//confirm that save and new enc id are cascaded to obs groupMembers
		//even though childObs aren't directly associated to enc
		assertNotNull("save succeeds without error", es.saveEncounter(enc));
		assertTrue("enc save succeeds", enc.getId() > 0);
		
		assertNotNull("obs save succeeds", groupObs.getObsId());
		assertEquals("encounter id propogated", groupObs.getEncounter().getId(), enc.getId());
		assertEquals("encounter time propogated", groupObs.getObsDatetime(), enc.getEncounterDatetime());
		assertNotNull("obs save succeeds", childObs.getObsId());
		assertEquals("encounter id propogated", childObs.getEncounter().getId(), enc.getId());
		assertEquals("encounter time propogated", childObs.getObsDatetime(), enc.getEncounterDatetime());
		
	}