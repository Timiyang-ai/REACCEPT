@Test
  public void testAddExtension() {
    final MockImpl m = new MockImpl();
    final MockExtension extension = new MockExtension();

    m.getExtensionPoint().addExtension(extension);
    assertEquals(1, m.extensionPoint.extensions.size());

    final Iterable<Extension<MockInterface>> extensions = m.getExtensionPoint().getExtensions();
    assertNotNull(extensions);
    final Iterator<Extension<MockInterface>> iterator = extensions.iterator();

    // first and only entry should be our extension.
    final Extension<MockInterface> actual = iterator.next();
    assertSame(extension, actual);

    // should only be one extension in the iterator.
    try {
      iterator.next();
      fail("Expected NoSuchElementException.");
    } catch (NoSuchElementException e) {
      // ignore
    }
  }