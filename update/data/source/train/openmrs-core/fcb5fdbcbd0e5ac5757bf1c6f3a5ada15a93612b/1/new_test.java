@Test
	public void saveEncounter_shouldCascadeUpdateTheObsdatetimesToAllObs() {
		EncounterService es = Context.getEncounterService();
		// Create a new Encounter
		Encounter enc = buildEncounter();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) -1);
		enc.setEncounterDatetime(calendar.getTime());

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

		Obs obs2 = buildObs();
		enc.addObs(obs2);

		//confirm that save and new enc id are cascaded to obs groupMembers
		//even though childObs aren't directly associated to enc
		assertNotNull("save succeeds without error", es.saveEncounter(enc));
		assertTrue("enc save succeeds", enc.getId() > 0);

		// update encounterDatetime, all encounter's Obs datetime should be udpated with new datetime
		enc.setEncounterDatetime(new Date());
		assertNotNull("save succeeds without error", es.saveEncounter(enc));
		Date encounterDatetime = DateUtil.truncateToSeconds(enc.getEncounterDatetime());

		for (Obs o : enc.getAllFlattenedObs(false)) {
			assertEquals("encounter datetime propagated", DateUtil.truncateToSeconds(o.getObsDatetime()), encounterDatetime);
		}
		
	}