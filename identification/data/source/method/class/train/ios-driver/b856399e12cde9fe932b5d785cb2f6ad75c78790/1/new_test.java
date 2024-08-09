  @Test
  public void getRegexPattern() {
    Assert.assertEquals(LanguageDictionary.getRegexPattern(
        "%@"),
        "(.*){1}");
    Assert.assertEquals(LanguageDictionary.getRegexPattern(
        "\\%@"),
        "\\%@");
    Assert.assertEquals(LanguageDictionary.getRegexPattern(
        "[](){}.?*+"),
        "\\[\\]\\(\\)\\{\\}\\.\\?\\*\\+");
    Assert.assertEquals(LanguageDictionary.getRegexPattern(
        "% %%"),
        "% %%");
    Assert.assertEquals(LanguageDictionary.getRegexPattern(
        "(%@ %1$@ %23$@ %d %@ %1$@ %54$@ %d % %%)."),
        "\\((.*){1} (.*){1} (.*){1} (.*){1} (.*){1} (.*){1} (.*){1} (.*){1} % %%\\)\\.");
  }