  @Test
  public void callExtensionPointTest() throws Exception {
    PluginMockInterface pluginInterface = mock( PluginMockInterface.class );
    when( pluginInterface.getName() ).thenReturn( TEST_NAME );
    when( pluginInterface.getMainType() ).thenReturn( (Class) ExtensionPointInterface.class );
    when( pluginInterface.getIds() ).thenReturn( new String[] {"testID"} );

    ExtensionPointInterface extensionPoint = mock( ExtensionPointInterface.class );
    when( pluginInterface.loadClass( ExtensionPointInterface.class ) ).thenReturn( extensionPoint );

    PluginRegistry.addPluginType( ExtensionPointPluginType.getInstance() );
    PluginRegistry.getInstance().registerPlugin( ExtensionPointPluginType.class, pluginInterface );

    final LogChannelInterface log = mock( LogChannelInterface.class );

    ExtensionPointHandler.callExtensionPoint( log, "noPoint", null );
    verify( extensionPoint, never() ).callExtensionPoint( any( LogChannelInterface.class ), any() );

    ExtensionPointHandler.callExtensionPoint( log, TEST_NAME, null );
    verify( extensionPoint, times( 1 ) ).callExtensionPoint( eq( log ), isNull() );
  }