public List<ComponentInfo> getComponentsByService(String stackName, String version, String stackServiceName)
      throws AmbariException {

    ServiceInfo service;
    try {
      service = getService(stackName, version, stackServiceName);
    } catch (StackAccessException e) {
      throw new ParentObjectNotFoundException("Parent Service resource doesn't exist. stackName=" +
          stackName + ", stackVersion=" + version + ", stackServiceName=" + stackServiceName);
    }
    return service.getComponents();
  }