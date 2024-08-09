  @Test
  public void removePortTest() {
    ServerPortRegistry.clear();
    Service service = new Service( "foo", "foo description" );
    ServerPortRegistry.addService( service );
    ServerPort serverPort1 = new ServerPort( "id1", "description", 60001, "foo" );
    ServerPort serverPort2 = new ServerPort( "id2", "description", 60101, "foo" );
    ServerPortRegistry.addPort( serverPort1 );
    ServerPortRegistry.addPort( serverPort2 );

    assertEquals( 2, ServerPortRegistry.getService( "foo" ).getServerPorts().size() );
    ServerPortRegistry.removePort( serverPort1 );
    assertEquals( 1, ServerPortRegistry.getService( "foo" ).getServerPorts().size() );
    assertEquals( null, ServerPortRegistry.getPort( "id1" ) );

  }