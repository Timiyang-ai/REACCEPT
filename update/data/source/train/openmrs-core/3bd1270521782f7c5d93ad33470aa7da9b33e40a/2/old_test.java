@Test
	@Verifies(value = "should return the next unvoided active visit matching the specified types", method = "getNextVisitToClose(Visit,Collection<QVisitType;>)")
	public void getNextVisitToClose_shouldReturnTheNextUnvoidedActiveVisitMatchingTheSpecifiedTypes() throws Exception {
		executeDataSet(VISITS_INCLUDE_VISITS_TO_AUTO_CLOSE_XML);
		ArrayList<VisitType> visitTypes = new ArrayList<VisitType>();
		visitTypes.add(dao.getVisitType(4));
		Assert.assertEquals(104, dao.getNextVisitToClose(dao.getVisit(1), visitTypes).getVisitId().intValue());
	}