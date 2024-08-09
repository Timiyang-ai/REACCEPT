@Test
	@Verifies(value = "should close all unvoided active visit matching the specified visit types", method = "stopVisits()")
	public void stopVisits_shouldCloseAllUnvoidedActiveVisitMatchingTheSpecifiedVisitTypes() throws Exception {
		executeDataSet("org/openmrs/api/include/VisitServiceTest-includeVisitsAndTypeToAutoClose.xml");
		String[] visitTypeNames = StringUtils.split(Context.getAdministrationService().getGlobalProperty(
		    OpenmrsConstants.GP_VISIT_TYPES_TO_AUTO_CLOSE), ",");
		
		String openVisitsQuery = "SELECT visit_id FROM visit WHERE voided = 0 AND date_stopped IS NULL AND visit_type_id IN (SELECT visit_type_id FROM visit_type WHERE NAME IN ('"
		        + StringUtils.join(visitTypeNames, "','") + "'))";
		int activeVisitCount = Context.getAdministrationService().executeSQL(openVisitsQuery, true).size();
		//sanity check
		Assert.assertTrue("There should be some active visits for this test to be valid", activeVisitCount > 0);
		
		//close any unvoided open visits
		service.stopVisits();
		
		activeVisitCount = Context.getAdministrationService().executeSQL(openVisitsQuery, true).size();
		
		//all active unvoided visits should have been closed
		Assert.assertTrue("Not all active unvoided vists were closed", activeVisitCount == 0);
	}