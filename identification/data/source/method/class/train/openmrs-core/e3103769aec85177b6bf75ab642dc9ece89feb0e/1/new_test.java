	@Test
	public void file2url_shouldReturnNullIfFileIsNull() throws MalformedURLException {
		URL nullURL = ModuleUtil.file2url(null);
		Assert.assertNull(nullURL);
	}