  @Test
  public void contains() throws IOException {
    LmVocabulary vocabulary = new LmVocabulary("Hello", "World");

    int helloIndex = vocabulary.indexOf("Hello");
    int worldIndex = vocabulary.indexOf("World");
    Assert.assertTrue(vocabulary.contains(helloIndex));
    Assert.assertTrue(vocabulary.contains(worldIndex));

    int unkIndex = vocabulary.indexOf("Foo");
    Assert.assertEquals(vocabulary.getUnknownWordIndex(), unkIndex);

    Assert.assertTrue(vocabulary.containsAll(helloIndex, worldIndex));
    Assert.assertFalse(vocabulary.containsAll(-1, 2));

    Assert.assertTrue(vocabulary.contains("Hello"));
    Assert.assertTrue(vocabulary.contains("World"));
    Assert.assertFalse(vocabulary.contains("Foo"));
    Assert.assertFalse(vocabulary.containsAll("Hello", "Foo"));
    Assert.assertTrue(vocabulary.containsAll("Hello", "World"));
  }