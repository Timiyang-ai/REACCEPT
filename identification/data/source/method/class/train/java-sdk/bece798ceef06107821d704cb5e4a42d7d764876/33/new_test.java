@Test
  public void testGetAuthors() {
    final Map<String, Object> params = new HashMap<String, Object>();
    params
        .put(AlchemyLanguage.URL,
            "http://www.politico.com/blogs/media/2012/02/detroit-news-ed-upset-over-romney-edit-115247.html");
    final DocumentAuthors authors = service.getAuthors(params);
    Assert.assertNotNull(authors);
  }