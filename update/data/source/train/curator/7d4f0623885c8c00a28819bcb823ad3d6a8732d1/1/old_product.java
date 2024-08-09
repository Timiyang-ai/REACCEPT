@Override
    public void unregisterService(ServiceInstance<T> service) throws Exception
    {
        clean();

        internalUnregisterService(getOrMakeHolder(service, null));
    }