	@Test
	public void url2file_shouldReturnNullGivenNullParameter() {
		assertNull(OpenmrsUtil.url2file(null));
	}