@Override
    public void unregisterService(ServiceInstance<T> service) throws Exception
    {
        internalUnregisterService(services.remove(service.getId()));
    }