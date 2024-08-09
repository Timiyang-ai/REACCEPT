	@Test
	public void getEncounters_shouldGetEncountersByLocation() {
		EncounterSearchCriteria encounterSearchCriteria = new EncounterSearchCriteriaBuilder().setLocation(new Location(1))
		        .setIncludeVoided(true).createEncounterSearchCriteria();
		
		List<Encounter> encounters = Context.getEncounterService().getEncounters(encounterSearchCriteria);
		assertEquals(6, encounters.size());
	}