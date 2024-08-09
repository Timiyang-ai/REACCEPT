@Test
	public void testGetTitle() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.URL, "http://www.techcrunch.com/");
		DocumentTitle title = service.getTitle(params);
		Assert.assertNotNull(title);
	}