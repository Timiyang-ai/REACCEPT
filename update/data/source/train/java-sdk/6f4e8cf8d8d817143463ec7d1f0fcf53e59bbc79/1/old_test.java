@Test
  public void testTranslate() {
    final String result = "El equipo es increíble IBM Watson";
    testTranslationResult(text, result, service.translate(text, ENGLISH_TO_SPANISH).execute());
    testTranslationResult(text, result, service.translate(text, Language.ENGLISH, Language.SPANISH).execute());
  }