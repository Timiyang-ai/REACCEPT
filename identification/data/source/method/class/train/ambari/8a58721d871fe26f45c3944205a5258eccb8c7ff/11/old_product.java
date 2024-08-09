public List<ComponentInfo> getComponentsByService(String stackName,
                                                      String version, String serviceName) {
        List<ComponentInfo> componentsResult = null;
        ServiceInfo service = getServiceInfo(stackName, version, serviceName);
        if (service != null)
            componentsResult = service.getComponents();

        return componentsResult;
    }