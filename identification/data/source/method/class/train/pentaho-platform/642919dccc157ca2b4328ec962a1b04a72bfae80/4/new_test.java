  @Test
  public void addServiceTest() {
    ServerPortRegistry.clear();
    Service service = new Service( "foo", "foo description" );
    ServerPortRegistry.addService( service );

    Service readService = ServerPortRegistry.getService( "foo" );
    assertEquals( service.getServiceDescription(), readService.getServiceDescription() );
  }