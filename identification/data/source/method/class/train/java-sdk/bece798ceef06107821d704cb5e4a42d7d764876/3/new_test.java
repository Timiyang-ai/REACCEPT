@Test
  public void testGetLanguage() {
    final Map<String, Object> params = new HashMap<String, Object>();
    params.put(AlchemyLanguage.URL, "http://news.google.fr/");
    final Language language = service.getLanguage(params);
    Assert.assertNotNull(language);
  }