public List<ComponentInfo> getComponentsByService(String stackName, String version, String serviceName)
      throws AmbariException {

    ServiceInfo service = getServiceInfo(stackName, version, serviceName);
    if (service == null) {
      throw new ParentObjectNotFoundException("Parent Service resource doesn't exist. stackName=" +
          stackName + ", stackVersion=" + version + ", serviceName=" + serviceName);
    }

    return service.getComponents();
  }