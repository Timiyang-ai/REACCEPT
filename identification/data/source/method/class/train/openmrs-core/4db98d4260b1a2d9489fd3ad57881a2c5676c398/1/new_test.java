	@Test
	public void getVisits_shouldReturnAllUnvoidedVisitsIfIncludeEndedIsSetToTrue() {
		executeDataSet(VISITS_WITH_DATES_XML);
		Assert.assertEquals(13, dao.getVisits(null, null, null, null, null, null, null, null, null, true, false).size());
	}