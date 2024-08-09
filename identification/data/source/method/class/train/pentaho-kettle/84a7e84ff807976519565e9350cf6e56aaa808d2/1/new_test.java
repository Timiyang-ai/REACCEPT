  @Test
  public void addExtensionPointTest() throws KettlePluginException {
    ExtensionPointMap.getInstance().addExtensionPoint( pluginInterface );
    assertEquals( ExtensionPointMap.getInstance().getTableValue( TEST_NAME, "testID" ), extensionPoint );

    // Verify cached instance
    assertEquals( ExtensionPointMap.getInstance().getTableValue( TEST_NAME, "testID" ), extensionPoint );
    verify( pluginInterface, times( 1 ) ).loadClass( any( Class.class ) );
  }