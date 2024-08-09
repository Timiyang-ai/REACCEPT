public boolean isValidService(String stackName, String version,
                                String serviceName) throws AmbariException {

    boolean exist = false;
    try {
      getServiceInfo(stackName, version, serviceName);
      exist = true;
    } catch (ObjectNotFoundException e) {
    }
    return exist;
  }