public boolean isSupportedStack(String stackName, String version) throws AmbariException {
    boolean exist = false;
    try {
      getStackInfo(stackName, version);
      exist = true;
    } catch (ObjectNotFoundException e) {
    }
    return exist;
  }