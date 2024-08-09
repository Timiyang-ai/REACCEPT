public static void addService( ServerPortService service ) {
    if ( !services.contains( service.getServiceName() ) ) {
      services.put( service.getServiceName(), service );
    }
  }