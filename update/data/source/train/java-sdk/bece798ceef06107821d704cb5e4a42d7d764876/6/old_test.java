@Test
	public void testGetLanguage() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.URL, "http://news.google.fr/");
		Language language = service.getLanguage(params);
		Assert.assertNotNull(language);
	}