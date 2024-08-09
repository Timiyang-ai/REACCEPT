  @Test
  public void testaddNode() {
    mLog.info( "testaddNode.." );

    try {
      NodeHelper.addNode( node, "PREFIX", "TEST_NODE" );
    } catch ( Exception e ) {
      fail();
    }
  }