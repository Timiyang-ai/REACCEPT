@Test
  public void testIdentify() {
    final List<IdentifiedLanguage> identifiedLanguages = service.identify(text).execute();
    assertNotNull(identifiedLanguages);
    assertFalse(identifiedLanguages.isEmpty());
  }