public Map<String, ServiceInfo> getServices(String stackName, String version) throws AmbariException {

    Map<String, ServiceInfo> servicesInfoResult = new HashMap<String, ServiceInfo>();

    List<ServiceInfo> services;
    StackInfo stack;
    try {
      stack = getStackInfo(stackName, version);
    } catch (StackAccessException e) {
      throw new ParentObjectNotFoundException("Parent Stack Version resource doesn't exist", e);
    }

    services = stack.getServices();
    if (services != null)
      for (ServiceInfo service : services) {
        servicesInfoResult.put(service.getName(), service);
      }
    return servicesInfoResult;
  }