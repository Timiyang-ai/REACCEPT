@Test
  public void testGetDelegate() {
    final TestCacheXmlParser cacheXmlParser = new TestCacheXmlParser();

    assertTrue("delegates should be empty.", cacheXmlParser.getDelegates().isEmpty());

    final MockXmlParser delegate = (MockXmlParser) cacheXmlParser.getDelegate(NAMESPACE_URI);

    assertNotNull("Delegate should be found in classpath.", delegate);

    assertSame("Should have same stack as cacheXmlParser.", cacheXmlParser.stack, delegate.stack);
    assertSame("Should have same stack as cacheXmlParser.", cacheXmlParser.documentLocator,
        delegate.documentLocator);

    assertEquals("Should be exactly 1 delegate.", 1, cacheXmlParser.getDelegates().size());
    assertNotNull("There should be an entry in delegates cache.",
        cacheXmlParser.getDelegates().get(NAMESPACE_URI));
    assertSame("Cached delegate should match the one from get.", delegate,
        cacheXmlParser.getDelegates().get(NAMESPACE_URI));

    final MockXmlParser delegate2 = (MockXmlParser) cacheXmlParser.getDelegate(NAMESPACE_URI);
    assertSame("Delegate should be the same between gets.", delegate, delegate2);
    assertEquals("Should still be exactly 1 delegate.", 1, cacheXmlParser.getDelegates().size());

    assertNull(cacheXmlParser.getDelegate("--nothing-should-use-this-namespace--"));
  }