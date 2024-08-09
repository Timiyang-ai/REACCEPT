@Test
	@Verifies(value = "should return all unvoided visits if includeEnded is set to true", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,Map<VisitAttributeType, Object>,boolean,boolean)")
	public void getVisits_shouldReturnAllUnvoidedVisitsIfIncludeEndedIsSetToTrue() throws Exception {
		executeDataSet(VISITS_WITH_DATES_XML);
		Assert.assertEquals(13, dao.getVisits(null, null, null, null, null, null, null, null, null, true, false).size());
	}