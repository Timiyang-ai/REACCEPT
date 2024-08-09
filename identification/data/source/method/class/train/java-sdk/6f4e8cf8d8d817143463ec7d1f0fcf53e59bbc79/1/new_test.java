@Test
  public void testTranslate() {
    for(String text : texts) {
      testTranslationResult(text, translations.get(text), service.translate(text, ENGLISH_TO_SPANISH).execute());
      testTranslationResult(text, translations.get(text), service.translate(text, ENGLISH, SPANISH).execute());
    }
  }