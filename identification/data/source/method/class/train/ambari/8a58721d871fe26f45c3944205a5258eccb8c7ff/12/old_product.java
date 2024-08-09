public boolean isSupportedStack(String stackName, String version) throws AmbariException {
    boolean exist = false;
    try {
      StackInfo stackInfo = getStackInfo(stackName, version);
      if (stackInfo != null) {
        exist = true;
      }
    } catch (ObjectNotFoundException e) {
    }
    return exist;
  }