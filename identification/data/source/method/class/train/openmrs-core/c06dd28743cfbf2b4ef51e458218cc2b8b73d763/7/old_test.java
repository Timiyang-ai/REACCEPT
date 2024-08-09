	@Test
	public void retireVisitType_shouldRetireGivenVisitType() {
		VisitType visitType = visitService.getVisitType(1);
		assertNotNull(visitType);
		Assert.assertFalse(visitType.getRetired());
		assertNull(visitType.getRetireReason());
		
		visitService.retireVisitType(visitType, "retire reason");
		
		visitType = visitService.getVisitType(1);
		assertNotNull(visitType);
		assertTrue(visitType.getRetired());
		assertEquals("retire reason", visitType.getRetireReason());
		
		//Should not change the number of visit types.
		assertEquals(3, visitService.getAllVisitTypes().size());
	}