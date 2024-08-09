@Test
  public void testGetIdentifiableLanguages() {
    final List<IdentifiableLanguage> languages = service.getIdentifiableLanguages();
    assertNotNull(languages);
    assertTrue(!languages.isEmpty());
  }