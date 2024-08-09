public static void addPort( ServerPort serverPort ) {
    if ( serverPorts.get( serverPort.getId() ) != null && serverPorts.get( serverPort.getId() ).getValue() != null ) {
      throw new IllegalStateException( "Another port has already been assigned with this ID." );
    }

    // add to port list
    serverPorts.put( serverPort.getId(), serverPort );
    String serviceName = serverPort.getServiceName();

    // add to service
    Service service = services.get( serviceName );
    if ( service == null ) {
      logger.warn( "Server Port added with no service.  Adding service without description" );
      // add the service (with no description) on the fly rather than throw an exception
      service = new Service( serverPort.getServiceName(), "Unknown Description" );
      services.put( serviceName, service );
    }
    Set<ServerPort> servicePorts = service.getServerPorts();
    servicePorts.add( serverPort );
  }