public static void addService( Service service ) {
    if ( !services.contains( service.getServiceName() ) ) {
      services.put( service.getServiceName(), service );
    }
  }