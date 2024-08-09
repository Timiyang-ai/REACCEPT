public static void removePort( ServerPort serverPort ) {
    ServerPortService service = services.get( serverPort.getServiceName() );
    service.getServerPorts().remove( serverPort );
    serverPorts.remove( serverPort.getId() );
  }