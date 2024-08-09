  @Test
  public void formatToCase() {
    TurkishMorphology morphology = TurkishMorphology.builder()
        .disableCache()
        .setLexicon("kış", "şiir", "Aydın", "Google [Pr:gugıl]")
        .build();

    String[] inputs =
        {"aydında", "googledan", "Google", "şiirde", "kışçığa", "kış"};

    String[] expectedDefaultCase =
        {"Aydın'da", "Google'dan", "Google", "şiirde", "kışçığa", "kış"};
    String[] expectedLowerCase =
        {"aydın'da", "google'dan", "google", "şiirde", "kışçığa", "kış"};
    String[] expectedUpperCase =
        {"AYDIN'DA", "GOOGLE'DAN", "GOOGLE", "ŞİİRDE", "KIŞÇIĞA", "KIŞ"};
    String[] expectedCapitalCase =
        {"Aydın'da", "Google'dan", "Google", "Şiirde", "Kışçığa", "Kış"};
    String[] expectedUpperRootLowerEndingCase =
        {"AYDIN'da", "GOOGLE'dan", "GOOGLE", "ŞİİRde", "KIŞçığa", "KIŞ"};

    testCaseType(morphology, inputs, expectedDefaultCase,
        DEFAULT_CASE);
    testCaseType(morphology, inputs, expectedLowerCase,
        LOWER_CASE);
    testCaseType(morphology, inputs, expectedUpperCase,
        UPPER_CASE);
    testCaseType(morphology, inputs, expectedCapitalCase,
        TITLE_CASE);
    testCaseType(morphology, inputs, expectedUpperRootLowerEndingCase,
        UPPER_CASE_ROOT_LOWER_CASE_ENDING);
  }