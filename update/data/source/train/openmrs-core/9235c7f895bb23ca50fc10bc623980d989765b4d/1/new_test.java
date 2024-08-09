@Test
	@Verifies(value = "should replace tags without ids with database fetched tag", method = "handle(ConceptName,User,Date,String)")
	public void handle_shouldReplaceTagsWithoutIdsWithDatabaseFetchedTag() throws Exception {
		ConceptNameSaveHandler handler = new ConceptNameSaveHandler();
		ConceptName name = new ConceptName();
		name.addTag(ConceptNameTag.PREFERRED); // this tag has a null id
		handler.handle(name, null, null, null);
		ConceptNameTag newTag = name.getTags().iterator().next();
		Assert.assertEquals(4, newTag.getConceptNameTagId().intValue());
	}