public Map<String, ServiceInfo> getServices(String stackName, String version) {

        Map<String, ServiceInfo> servicesInfoResult = new HashMap<String, ServiceInfo>();

        List<ServiceInfo> services = null;
        StackInfo stack = getStackInfo(stackName, version);
        if (stack == null)
            return null;
        services = stack.getServices();
        if (services != null)
            for (ServiceInfo service : services) {
                servicesInfoResult.put(service.getName(), service);
            }
        return servicesInfoResult;
    }