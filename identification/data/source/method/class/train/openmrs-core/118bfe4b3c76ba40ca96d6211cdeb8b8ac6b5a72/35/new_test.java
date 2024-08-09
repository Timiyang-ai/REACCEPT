	@Test
	public void getConceptClassByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "97097dd9-b092-4b68-a2dc-e5e5be961d42";
		ConceptClass conceptClass = Context.getConceptService().getConceptClassByUuid(uuid);
		Assert.assertEquals(1, (int) conceptClass.getConceptClassId());
	}