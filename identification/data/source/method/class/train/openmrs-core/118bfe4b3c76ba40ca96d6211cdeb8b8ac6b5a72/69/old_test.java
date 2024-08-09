	@Test
	public void getConceptAnswerByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "b1230431-2fe5-49fc-b535-ae42bc849747";
		ConceptAnswer conceptAnswer = Context.getConceptService().getConceptAnswerByUuid(uuid);
		Assert.assertEquals(1, (int) conceptAnswer.getConceptAnswerId());
	}