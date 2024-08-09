public static void removePort( ServerPort serverPort ) {
    Service service = services.get( serverPort.getServiceName() );
    service.getServerPorts().remove( serverPort );
    serverPorts.remove( serverPort.getId() );
  }