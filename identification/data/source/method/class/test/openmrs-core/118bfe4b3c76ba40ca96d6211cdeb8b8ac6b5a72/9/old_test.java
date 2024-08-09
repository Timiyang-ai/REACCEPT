	@Test
	public void getConceptNameTagByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "9e9df183-2328-4117-acd8-fb9bf400911d";
		ConceptNameTag conceptNameTag = Context.getConceptService().getConceptNameTagByUuid(uuid);
		Assert.assertEquals(1, (int) conceptNameTag.getConceptNameTagId());
	}