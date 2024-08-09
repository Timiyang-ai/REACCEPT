@Test
  public void testGetTitle() {
    final Map<String, Object> params = new HashMap<String, Object>();
    params.put(AlchemyLanguage.URL, "http://www.techcrunch.com/");
    final DocumentTitle title = service.getTitle(params);
    Assert.assertNotNull(title);
  }