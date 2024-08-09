private IEngine createEngine() {
        DefaultEngineFactory factory = new DefaultEngineFactory() {

            @Override
            protected IConnectorFactory createConnectorFactory(IPluginRegistry pluginRegistry) {
                return new PolicyTesterConnectorFactory();
            }

            @Override
            protected IComponentRegistry createComponentRegistry(IPluginRegistry pluginRegistry) {
                return new DefaultComponentRegistry() {
                    @Override
                    protected void registerBufferFactoryComponent() {
                        addComponent(IBufferFactoryComponent.class, new PolicyTesterBufferFactoryComponent());
                    }
                };
            }

            @Override
            protected IPluginRegistry createPluginRegistry() {
                return new IPluginRegistry() {
                    @Override
                    public Future<IAsyncResult<Plugin>> loadPlugin(PluginCoordinates coordinates, IAsyncResultHandler<Plugin> handler) {
                        throw new RuntimeException("Plugins not supported.");
                    }
                };
            }
        };
        return factory.createEngine();
    }