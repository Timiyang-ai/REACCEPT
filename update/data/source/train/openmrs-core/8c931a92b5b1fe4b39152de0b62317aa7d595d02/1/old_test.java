@Test
	@Verifies(value = "should erase the visit from the database", method = "purgeVisit(Visit)")
	public void purgeVisit_shouldEraseTheVisitFromTheDatabase() throws Exception {
		VisitService vs = Context.getVisitService();
		Integer originalSize = vs.getAllVisits().size();
		Visit visit = Context.getVisitService().getVisit(1);
		vs.purgeVisit(visit);
		Assert.assertEquals(originalSize - 1, vs.getAllVisits().size());
	}