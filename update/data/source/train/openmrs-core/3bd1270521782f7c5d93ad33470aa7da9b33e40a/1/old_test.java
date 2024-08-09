@Test
	@Verifies(value = "should close the next unvoided active visit matching the specified arguments", method = "stopNextActiveVisit(Visit,List<QVisitType;>)")
	public void stopNextActiveVisit_shouldCloseTheNextUnvoidedActiveVisitMatchingTheSpecifiedArguments() throws Exception {
		ArrayList<VisitType> visitTypes = new ArrayList<VisitType>();
		visitTypes.add(service.getVisitType(1));
		Visit stoppedVisit = service.stopNextActiveVisit(service.getVisit(2), visitTypes);
		Assert.assertEquals(4, stoppedVisit.getVisitId().intValue());
	}