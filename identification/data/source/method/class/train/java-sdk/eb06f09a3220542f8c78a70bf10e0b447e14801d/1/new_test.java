@Test
  public void testGetSynonyms() {
    // Call the service and get the synonym for 'difficult' and 'inderior'
    SynonymOptions options = new SynonymOptions().words("difficult", "inferior").limit(3).hops(3);

    List<SynonymResult> synonyms = service.getSynonyms(options);

    Assert.assertNotNull(synonyms);
    Assert.assertFalse(synonyms.isEmpty());
  }