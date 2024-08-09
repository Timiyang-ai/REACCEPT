  @Test
  public void vowelCountTest() {
    TurkishAlphabet alphabet = TurkishAlphabet.INSTANCE;
    String[] entries = {"a", "aa", "", "bb", "bebaba"};
    int[] expCounts = {1, 2, 0, 0, 3};
    int i = 0;
    for (String entry : entries) {
      Assert.assertEquals(expCounts[i++], alphabet.vowelCount(entry));
    }
  }