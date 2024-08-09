	@Test
	public void unretireVisitType_shouldUnretireGivenVisitType() {
		VisitType visitType = visitService.getVisitType(3);
		assertNotNull(visitType);
		assertTrue(visitType.getRetired());
		assertEquals("Some Retire Reason", visitType.getRetireReason());
		
		visitService.unretireVisitType(visitType);
		
		visitType = visitService.getVisitType(3);
		assertNotNull(visitType);
		Assert.assertFalse(visitType.getRetired());
		assertNull(visitType.getRetireReason());
		
		//Should not change the number of visit types.
		assertEquals(3, visitService.getAllVisitTypes().size());
	}