public <T extends ClientRpc> T getRpcProxy(final Class<T> rpcInterface) {
        // create, initialize and return a dynamic proxy for RPC
        try {
            if (!rpcProxyMap.containsKey(rpcInterface)) {
                Class<T> proxyClass = (Class) Proxy.getProxyClass(
                        rpcInterface.getClassLoader(), rpcInterface);
                Constructor<T> constructor = proxyClass
                        .getConstructor(InvocationHandler.class);
                T rpcProxy = constructor.newInstance(new RpcInvoicationHandler(
                        rpcInterface));
                // cache the proxy
                rpcProxyMap.put(rpcInterface, rpcProxy);
            }
            return (T) rpcProxyMap.get(rpcInterface);
        } catch (Exception e) {
            // TODO exception handling?
            throw new RuntimeException(e);
        }
    }