@Test
  public void testIdentify() {
    final List<IdentifiedLanguage> identifiedLanguages = service.identify(text);
    assertNotNull(identifiedLanguages);
    assertFalse(identifiedLanguages.isEmpty());
  }