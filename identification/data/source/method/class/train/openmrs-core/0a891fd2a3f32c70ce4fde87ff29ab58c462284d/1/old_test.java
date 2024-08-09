	@Test
	public void getVisitAssignmentHandlers_shouldReturnTheNoAssignmentHandler() {
		
		List<EncounterVisitHandler> handlers = Context.getEncounterService().getEncounterVisitHandlers();
		
		boolean found = false;
		for (EncounterVisitHandler handler : handlers) {
			if (handler instanceof NoVisitAssignmentHandler)
				found = true;
		}
		
		Assert.assertTrue("The basic 'no assignment' handler was not found", found);
	}