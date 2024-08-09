public boolean isValidService(String stackName, String version, String stackServiceName){

    try {
      getService(stackName, version, stackServiceName);
      return true;
    } catch (AmbariException e) {
      return false;
    }
  }