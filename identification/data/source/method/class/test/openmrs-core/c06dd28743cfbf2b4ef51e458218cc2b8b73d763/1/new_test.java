	@Test
	public void purgeVisitType_shouldDeleteGivenVisitType() {
		VisitType visitType = visitService.getVisitType(3);
		assertNotNull(visitType);
		
		visitService.purgeVisitType(visitType);
		
		visitType = visitService.getVisitType(3);
		assertNull(visitType);
		
		//Should reduce the existing number of visit types.
		assertEquals(2, visitService.getAllVisitTypes().size());
	}