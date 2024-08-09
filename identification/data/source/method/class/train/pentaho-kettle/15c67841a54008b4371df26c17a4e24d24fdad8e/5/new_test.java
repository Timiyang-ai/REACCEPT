  @Test
  public void getTreeObjects_getStepByName() {
    SpoonTreeDelegate std = spy( new SpoonTreeDelegate( spoon ) );

    Tree selection = mock( Tree.class );
    Tree core = mock( Tree.class );
    TreeItem item = mock( TreeItem.class );
    PluginInterface step = mock( PluginInterface.class );
    PluginRegistry registry = mock( PluginRegistry.class );

    TreeItem[] items = new TreeItem[] { item };

    when( ConstUI.getTreeStrings( item ) ).thenReturn( new String[] { "Output", "Delete" } );
    when( PluginRegistry.getInstance() ).thenReturn( registry );

    doReturn( items ).when( core ).getSelection();
    doReturn( null ).when( item ).getData( anyString() );
    doReturn( step ).when( registry ).findPluginWithName( StepPluginType.class, "Delete" );

    spoon.showJob = false;
    spoon.showTrans = true;

    TreeSelection[] ts = std.getTreeObjects( core, selection, core );

    assertEquals( 1, ts.length );
    assertEquals( step, ts[ 0 ].getSelection() );
  }