@Test
  public void testGetWord() {
    model = createVoiceModel();
    final List<CustomWord> expected = instantiateCustomWords();

    AddWordsOptions addOptions = new AddWordsOptions.Builder()
        .customizationId(model.getCustomizationId())
        .words(expected)
        .build();
    service.addWords(addOptions).execute();

    GetWordOptions getOptions = new GetWordOptions.Builder()
        .customizationId(model.getCustomizationId())
        .word(expected.get(0).getWord())
        .build();
    final Translation translation = service.getWord(getOptions).execute();
    assertEquals(expected.get(0).getTranslation(), translation.getTranslation());
  }