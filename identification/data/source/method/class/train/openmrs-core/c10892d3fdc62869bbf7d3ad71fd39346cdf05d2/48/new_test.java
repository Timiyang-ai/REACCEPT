	@Test
	public void retireLocationTag_shouldRetireLocationTagSuccessfully() {
		LocationService ls = Context.getLocationService();
		
		// Get all tags.
		List<LocationTag> tagsBeforeRetired = ls.getAllLocationTags(true);
		List<LocationTag> tagsNotRetiredBefore = ls.getAllLocationTags(false);
		
		// Get a non-retired tag
		LocationTag tag = ls.getLocationTag(1);
		
		LocationTag retiredTag = ls.retireLocationTag(tag, "Just Testing");
		
		// make sure its still the same object
		assertEquals(retiredTag, tag);
		
		// Get all tags again.
		List<LocationTag> tagsAfterRetired = ls.getAllLocationTags(true);
		List<LocationTag> tagsNotRetiredAfter = ls.getAllLocationTags(false);
		
		// Make sure that all the values were filled in
		assertTrue(retiredTag.getRetired());
		assertNotNull(retiredTag.getDateRetired());
		assertEquals(Context.getAuthenticatedUser(), retiredTag.getRetiredBy());
		assertEquals("Just Testing", retiredTag.getRetireReason());
		
		// Both tag lists that include retired should be equal.
		assertEquals(tagsBeforeRetired, tagsAfterRetired);
		
		// Both tag lists that do not include retired should not be the same.
		assertNotSame(tagsNotRetiredBefore, tagsNotRetiredAfter);
	}