	@Test
	public void getConceptByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "0cbe2ed3-cd5f-4f46-9459-26127c9265ab";
		Concept concept = Context.getConceptService().getConceptByUuid(uuid);
		Assert.assertEquals(3, (int) concept.getConceptId());
	}