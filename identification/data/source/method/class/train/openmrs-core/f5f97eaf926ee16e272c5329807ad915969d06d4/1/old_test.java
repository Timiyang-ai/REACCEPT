	@Test
	public void getPossibleOutcomes_shouldGetOutcomesForASet() {
		executeDataSet(PROGRAM_WITH_OUTCOMES_XML);
		
		List<Concept> possibleOutcomes = Context.getProgramWorkflowService().getPossibleOutcomes(4);
		assertEquals(4, possibleOutcomes.size());
	}