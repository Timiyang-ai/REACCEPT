@Test
	@Verifies(value = "should replace tags without ids with database fetched tag", method = "handle(ConceptName,User,Date,String)")
	public void handle_shouldReplaceTagsWithoutIdsWithDatabaseFetchedTag() throws Exception {
		ConceptNameSaveHandler handler = new ConceptNameSaveHandler();
		ConceptName name = new ConceptName();
		name.addTag(ConceptNameTag.PREFERRED); // this tag has a null id
		name.addTag(ConceptNameTag.SHORT); // this tag has a null id
		handler.handle(name, null, null, null);
		for (ConceptNameTag tag : name.getTags()) {
			if (tag.getTag().equals(ConceptNameTag.PREFERRED)) {
				Assert.assertEquals(4, tag.getConceptNameTagId().intValue());
			} else if (tag.getTag().equals(ConceptNameTag.SHORT)) {
				Assert.assertEquals(2, tag.getConceptNameTagId().intValue());
			}
		}
	}