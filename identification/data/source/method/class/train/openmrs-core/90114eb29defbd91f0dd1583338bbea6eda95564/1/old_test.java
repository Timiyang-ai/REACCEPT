@Test
	@Verifies(value = "should assign mapping global property visit type", method = "beforeCreateEncounter(Encounter)")
	public void beforeCreateEncounter_shouldAssignMappingGlobalPropertyVisitType() throws Exception {
		Encounter encounter = Context.getEncounterService().getEncounter(1);
		Assert.assertNull(encounter.getVisit());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(encounter.getEncounterDatetime());
		calendar.set(Calendar.YEAR, 1900);
		
		encounter.setEncounterDatetime(calendar.getTime());
		
		GlobalProperty gp = new GlobalProperty(OpenmrsConstants.GP_ENCOUNTER_TYPE_TO_VISIT_TYPE_MAPPING,
		        "3:4, 5:2, 1:2, 2:2");
		Context.getAdministrationService().saveGlobalProperty(gp);
		
		new ExistingOrNewVisitAssignmentHandler().beforeCreateEncounter(encounter);
		
		Assert.assertNotNull(encounter.getVisit());
		
		//The visit needs to be persisted, else the assert below will throw
		//org.hibernate.TransientObjectException: object references an unsaved transient 
		//instance - save the transient instance before flushing: org.openmrs.Visit
		Visit visit = encounter.getVisit();
		encounter.setVisit(null);
		Context.getVisitService().saveVisit(visit);
		encounter.setVisit(visit);
		
		//should be set according to: 1:2 encounterTypeId:visitTypeId
		Assert.assertEquals(1, encounter.getEncounterType().getEncounterTypeId().intValue());
		Assert.assertEquals(Context.getVisitService().getVisitType(2), encounter.getVisit().getVisitType());
	}