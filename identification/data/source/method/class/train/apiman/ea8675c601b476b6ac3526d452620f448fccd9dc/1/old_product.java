@Override
    public final IEngine createEngine() {
        IRegistry registry = createRegistry();
        IPluginRegistry pluginRegistry = createPluginRegistry();
        IComponentRegistry componentRegistry = createComponentRegistry();
        IConnectorFactory cfactory = createConnectorFactory();
        IPolicyFactory pfactory = createPolicyFactory();
        IMetrics metrics = createMetrics();

        IEngine engine = new EngineImpl(registry, pluginRegistry, componentRegistry, cfactory, pfactory, metrics);
        return engine;
    }