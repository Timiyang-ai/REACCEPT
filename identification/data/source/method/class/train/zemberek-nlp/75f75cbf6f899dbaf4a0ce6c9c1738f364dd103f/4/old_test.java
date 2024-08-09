  @Test
  public void toWordsTest() throws IOException {
    LmVocabulary vocabulary = new LmVocabulary("a", "b", "c", "d", "e");
    int[] indexes = vocabulary.toIndexes("a", "e", "b");
    Assert.assertEquals("a e b", Joiner.on(" ").join(vocabulary.toWords(indexes)));
    indexes = vocabulary.toIndexes("a", "e", "foo");
    Assert.assertEquals("a e <unk>", Joiner.on(" ").join(vocabulary.toWords(indexes)));
  }