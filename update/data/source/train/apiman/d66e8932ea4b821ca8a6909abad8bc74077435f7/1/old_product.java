@Override
    public final IEngine createEngine() {
        IPluginRegistry pluginRegistry = createPluginRegistry();
        IDataEncrypter encrypter = createDataEncrypter(pluginRegistry);
        CurrentDataEncrypter.instance = encrypter;
        IRegistry registry = createRegistry(pluginRegistry, encrypter);
        IComponentRegistry componentRegistry = createComponentRegistry(pluginRegistry);
        IConnectorFactory cfactory = createConnectorFactory(pluginRegistry);
        IPolicyFactory pfactory = createPolicyFactory(pluginRegistry);
        IMetrics metrics = createMetrics(pluginRegistry);
        IDelegateFactory logFactory = createLoggerFactory(pluginRegistry);

        List<IGatewayInitializer> initializers = createInitializers(pluginRegistry);
        for (IGatewayInitializer initializer : initializers) {
            initializer.initialize();
        }

        return new EngineImpl(registry, pluginRegistry, componentRegistry, cfactory, pfactory, metrics, logFactory);
    }