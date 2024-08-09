@Test
  public void testRemoveExtension() {
    final MockImpl m = new MockImpl();
    final MockExtension extension = new MockExtension();
    m.getExtensionPoint().addExtension(MockExtension.class, extension);

    final Iterable<Extension<MockInterface>> extensions = m.getExtensionPoint().getExtensions();
    assertNotNull(extensions);

    final Iterator<Extension<MockInterface>> i = extensions.iterator();

    // first and only entry should be our extension.
    final Extension<MockInterface> actual = i.next();
    assertSame(extension, actual);

    // should not be able to remove it via iterator.
    try {
      i.remove();
      fail("Expected UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      // ignore
    }

    m.getExtensionPoint().removeExtension(MockExtension.class);
    assertEquals(0, m.extensionPoint.extensions.size());

    // extensions should be empty
    final Iterable<Extension<MockInterface>> extensionsRemoved = m.getExtensionPoint().getExtensions();
    try {
      extensionsRemoved.iterator().next();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException e) {
      // ignore
    }
  }