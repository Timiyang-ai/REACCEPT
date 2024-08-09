	@Test
	public void saveVisit_shouldAddANewVisitToTheDatabase() {
		
		Integer originalSize = visitService.getAllVisits().size();
		Visit visit = new Visit(new Patient(2), new VisitType(1), new Date());
		
		visit = visitService.saveVisit(visit);
		
		assertNotNull(visit.getId());
		assertNotNull(visit.getUuid());
		assertNotNull(visit.getCreator());
		assertNotNull(visit.getDateCreated());
		assertEquals(originalSize + 1, visitService.getAllVisits().size());
	}