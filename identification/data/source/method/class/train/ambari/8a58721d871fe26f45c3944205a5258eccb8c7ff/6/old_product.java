public List<ComponentInfo> getComponentsByService(String stackName, String version, String serviceName)
      throws AmbariException {

    ServiceInfo service;
    try {
      service = getService(stackName, version, serviceName);
    } catch (StackAccessException e) {
      throw new ParentObjectNotFoundException("Parent Service resource doesn't exist. stackName=" +
          stackName + ", stackVersion=" + version + ", serviceName=" + serviceName);
    }
    return service.getComponents();
  }