	@Test
	public void unretireLocationTag_shouldUnretireRetiredLocationTag() {
		LocationService ls = Context.getLocationService();
		LocationTag tag = ls.getLocationTagByName("Test Retired Tag");
		
		Assert.assertTrue(tag.getRetired());
		
		LocationTag newTag = ls.unretireLocationTag(tag);
		
		Assert.assertEquals(tag, newTag);
		Assert.assertFalse(tag.getRetired());
		Assert.assertNull(tag.getRetiredBy());
		Assert.assertNull(tag.getRetireReason());
	}