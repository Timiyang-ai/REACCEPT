@Test
	@Verifies(value = "should return the next unvoided active visit matching the specified types and startDate", method = "getNextVisit(Visit,Collection<VisitType>,Date)")
	public void getNextVisit_shouldReturnTheNextUnvoidedActiveVisitMatchingTheSpecifiedTypesAndStartDate() throws Exception {
		executeDataSet(VISITS_INCLUDE_VISITS_TO_AUTO_CLOSE_XML);
		ArrayList<VisitType> visitTypes = new ArrayList<VisitType>();
		visitTypes.add(dao.getVisitType(4));
		Calendar cal = Calendar.getInstance();
		cal.set(2005, 0, 4, 23, 59, 59);
		cal.set(Calendar.MILLISECOND, 999);
		Assert.assertEquals(105, dao.getNextVisit(dao.getVisit(1), visitTypes, cal.getTime()).getVisitId().intValue());
	}