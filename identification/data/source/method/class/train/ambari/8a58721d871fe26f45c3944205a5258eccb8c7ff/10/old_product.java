public boolean isValidService(String stackName, String version,
                                  String serviceName) {
        ServiceInfo service = getServiceInfo(stackName, version, serviceName);
        return (service != null);
    }