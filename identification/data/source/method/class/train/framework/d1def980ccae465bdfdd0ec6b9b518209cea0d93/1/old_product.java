public <T extends ClientRpc> T getRpcProxy(final Class<T> rpcInterface) {
        // create, initialize and return a dynamic proxy for RPC
        try {
            if (!rpcProxyMap.containsKey(rpcInterface)) {
                InvocationHandler handler = new InvocationHandler() {
                    public Object invoke(Object proxy, Method method,
                            Object[] args) throws Throwable {
                        addMethodInvocationToQueue(rpcInterface.getName()
                                .replaceAll("\\$", "."), method.getName(), args);
                        // TODO no need to do full repaint if only RPC calls
                        requestRepaint();
                        return null;
                    }
                };
                Class<?> proxyClass = Proxy.getProxyClass(
                        rpcInterface.getClassLoader(),
                        new Class[] { rpcInterface });
                T rpcProxy = (T) proxyClass.getConstructor(
                        new Class[] { InvocationHandler.class }).newInstance(
                        new Object[] { handler });
                // cache the proxy
                rpcProxyMap.put(rpcInterface, rpcProxy);
            }
            return (T) rpcProxyMap.get(rpcInterface);
        } catch (Exception e) {
            // TODO exception handling?
            throw new RuntimeException(e);
        }
    }