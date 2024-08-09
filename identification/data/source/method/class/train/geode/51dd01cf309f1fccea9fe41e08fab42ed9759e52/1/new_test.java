@Test
  public void testGetDelegate() {
    final TestCacheXmlParser cacheXmlParser = new TestCacheXmlParser();
    assertThat(cacheXmlParser.getDelegates()).as("delegates should be empty.").isEmpty();

    final MockXmlParser delegate = (MockXmlParser) cacheXmlParser.getDelegate(NAMESPACE_URI);
    assertThat(delegate).as("Delegate should be found in classpath.").isNotNull();
    assertThat(cacheXmlParser.stack).as("Should have same stack as cacheXmlParser.")
        .isSameAs(delegate.stack);
    assertThat(cacheXmlParser.documentLocator).as("Should have same stack as cacheXmlParser.")
        .isSameAs(delegate.documentLocator);
    assertThat(cacheXmlParser.getDelegates().size()).as("Should be exactly 1 delegate.")
        .isEqualTo(1);
    assertThat(cacheXmlParser.getDelegates().get(NAMESPACE_URI))
        .as("There should be an entry in delegates cache.").isNotNull();
    assertThat(cacheXmlParser.getDelegates().get(NAMESPACE_URI))
        .as("Cached delegate should match the one from get.").isSameAs(delegate);

    final MockXmlParser delegate2 = (MockXmlParser) cacheXmlParser.getDelegate(NAMESPACE_URI);
    assertThat(delegate2).as("Delegate should be the same between gets.").isSameAs(delegate);
    assertThat(cacheXmlParser.getDelegates().size()).as("Should still be exactly 1 delegate.")
        .isEqualTo(1);
    assertThat(cacheXmlParser.getDelegate("--nothing-should-use-this-namespace--")).isNull();
  }