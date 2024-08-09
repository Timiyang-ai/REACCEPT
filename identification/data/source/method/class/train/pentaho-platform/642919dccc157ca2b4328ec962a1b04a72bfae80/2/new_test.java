  @Test
  public void addPortTest() {
    ServerPortRegistry.clear();
    Service service = new Service( "foo", "foo description" );
    ServerPortRegistry.addService( service );

    ServerPort serverPort1 = new ServerPort( "id1", "description", 60001, "foo" ); // fully specified
    ServerPort serverPort2 = new ServerPort( "id2", "description", 60101, "bla" ); // No service pre-defined
    ServerPort serverPort3 = new ServerPort( "id3", "description", 60201 ); // No service name defined
    ServerPortRegistry.addPort( serverPort1 );
    ServerPortRegistry.addPort( serverPort2 );
    ServerPortRegistry.addPort( serverPort3 );

    assertEquals( serverPort1, ServerPortRegistry.getPort( "id1" ) );
    assertEquals( serverPort2, ServerPortRegistry.getPort( "id2" ) );
    assertEquals( serverPort3, ServerPortRegistry.getPort( "id3" ) );

    assertEquals( "Unknown Description", ServerPortRegistry.getService(
        ServerPortRegistry.getPort( "id2" ).getServiceName() ).getServiceDescription() );
    assertEquals( "", ServerPortRegistry.getService( ServerPortRegistry.getPort( "id3" ).getServiceName() )
        .getServiceName() );
    assertEquals( "Unknown Description", ServerPortRegistry.getService(
        ServerPortRegistry.getPort( "id3" ).getServiceName() ).getServiceDescription() );
  }