@Override
    public final IEngine createEngine() {
        IPluginRegistry pluginRegistry = createPluginRegistry();
        IRegistry registry = createRegistry(pluginRegistry);
        IComponentRegistry componentRegistry = createComponentRegistry(pluginRegistry);
        IConnectorFactory cfactory = createConnectorFactory(pluginRegistry);
        IPolicyFactory pfactory = createPolicyFactory(pluginRegistry);
        IMetrics metrics = createMetrics(pluginRegistry);

        IEngine engine = new EngineImpl(registry, pluginRegistry, componentRegistry, cfactory, pfactory, metrics);
        return engine;
    }