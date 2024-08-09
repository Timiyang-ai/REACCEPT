	@Test
	public void saveVisitType_shouldSaveNewVisitType() {
		List<VisitType> visitTypes = visitService.getVisitTypes("Some Name");
		assertEquals(0, visitTypes.size());
		
		VisitType visitType = new VisitType("Some Name", "Description");
		visitService.saveVisitType(visitType);
		
		visitTypes = visitService.getVisitTypes("Some Name");
		assertEquals(1, visitTypes.size());
		
		//Should create a new visit type row.
		assertEquals(4, visitService.getAllVisitTypes().size());
	}