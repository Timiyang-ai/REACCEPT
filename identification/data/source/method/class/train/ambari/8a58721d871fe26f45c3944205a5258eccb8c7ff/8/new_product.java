public boolean isValidService(String stackName, String version, String serviceName){

    try {
      getService(stackName, version, serviceName);
      return true;
    } catch (AmbariException e) {
      return false;
    }
  }