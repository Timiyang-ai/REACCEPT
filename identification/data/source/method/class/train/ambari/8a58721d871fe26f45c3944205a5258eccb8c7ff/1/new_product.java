public boolean isValidService(String stackName, String version,
                                String serviceName) throws AmbariException {

    boolean exist = false;
    try {
      ServiceInfo info= getServiceInfo(stackName, version, serviceName);
      if (info != null) {
        exist = true;
      }
    } catch (ObjectNotFoundException e) {
    }
    return exist;
  }