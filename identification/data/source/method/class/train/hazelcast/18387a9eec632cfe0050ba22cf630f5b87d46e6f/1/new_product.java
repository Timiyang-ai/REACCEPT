@Override
    public List<ServiceInfo> getServiceInfos(Class serviceClass) {
        final LinkedList<ServiceInfo> result = new LinkedList<>();
        for (ServiceInfo serviceInfo : services.values()) {
            if (serviceInfo.isInstanceOf(serviceClass)) {
                if (serviceInfo.isCoreService()) {
                    result.addFirst(serviceInfo);
                } else {
                    result.addLast(serviceInfo);
                }
            }
        }
        return result;
    }