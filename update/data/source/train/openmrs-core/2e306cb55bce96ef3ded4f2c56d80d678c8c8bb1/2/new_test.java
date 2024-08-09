@Test
	@Verifies(value = "should encodeStringsTo128Characters", method = "encodeString(String)")
	public void encodeString_shouldEncodeStringsTo128Characters() throws Exception {
		String hash = Security.encodeString("test" + "c788c6ad82a157b712392ca695dfcf2eed193d7f");
		Assert.assertEquals(HASH_LENGTH, hash.length());
	}