@Test
  public void testAddWord() {
    model = createVoiceModel();
    final Word expected = instantiateWords().get(0);

    AddWordOptions addOptions = new AddWordOptions.Builder()
        .word(expected.getWord())
        .translation(expected.getTranslation())
        .customizationId(model.getCustomizationId())
        .build();
    service.addWord(addOptions).execute();

    ListWordsOptions listOptions = new ListWordsOptions.Builder()
        .customizationId(model.getCustomizationId())
        .build();
    final Words results = service.listWords(listOptions).execute();
    assertEquals(1, results.getWords().size());

    final Word result = results.getWords().get(0);
    assertEquals(expected, result);
    assertEquals(expected.getWord(), result.getWord());
    assertEquals(expected.getTranslation(), result.getTranslation());
  }