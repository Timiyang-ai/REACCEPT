@Test
	@Verifies(value = "should match strings hashed with sha1 algorithm", method = "hashMatches(String,String)")
	public void hashMatches_shouldMatchStringsHashedWithSha1Algorithm() throws Exception {
		Assert.assertTrue(Security.hashMatches("4a1750c8607d0fa237de36c6305715c223415189", "test"
		        + "c788c6ad82a157b712392ca695dfcf2eed193d7f"));
	}