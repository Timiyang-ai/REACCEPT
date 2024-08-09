@Test
	@Verifies(value = "should encodeStringsToFortyCharacters", method = "encodeString(String)")
	public void encodeString_shouldEncodeStringsToFortyCharacters() throws Exception {
		String hash = Security.encodeString("test" + "c788c6ad82a157b712392ca695dfcf2eed193d7f");
		Assert.assertEquals(40, hash.length());
	}