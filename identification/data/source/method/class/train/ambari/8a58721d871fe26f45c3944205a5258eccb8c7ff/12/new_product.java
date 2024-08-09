public boolean isSupportedStack(String stackName, String version) {
    try {
      // thrown an exception if the stack doesn't exist
      getStack(stackName, version);
      return true;
    } catch (AmbariException e) {
      return false;
    }
  }