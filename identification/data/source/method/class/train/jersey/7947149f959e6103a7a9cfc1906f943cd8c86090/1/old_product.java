private Application createApplication(final Class<? extends Application> applicationClass,
                                          final Value<Iterable<ComponentProvider>> componentProvidersValue) {
        // need to handle ResourceConfig and Application separately as invoking forContract() on these
        // will trigger the factories which we don't want at this point
        if (applicationClass == ResourceConfig.class) {
            return new ResourceConfig();
        } else if (applicationClass == Application.class) {
            return new Application();
        } else {
            Iterable<ComponentProvider> componentProviders = componentProvidersValue.get();
            boolean appClassBound = false;
            for (ComponentProvider cp : componentProviders) {
                if (cp.bind(applicationClass, Collections.emptySet())) {
                    appClassBound = true;
                    break;
                }
            }
            if (!appClassBound) {
                if (applicationClass.isAnnotationPresent(Singleton.class)) {
                    Binder binder = new AbstractBinder() {
                        @Override
                        protected void configure() {
                            bindAsContract(applicationClass).in(Singleton.class);
                        }
                    };
                    injectionManager.register(binder);
                    appClassBound = true;
                }
            }
            final Application app = appClassBound
                    ? injectionManager.getInstance(applicationClass) : injectionManager.createAndInitialize(applicationClass);
            if (app instanceof ResourceConfig) {
                final ResourceConfig _rc = (ResourceConfig) app;
                final Class<? extends Application> innerAppClass = _rc.getApplicationClass();
                if (innerAppClass != null) {
                    final Application innerApp = createApplication(innerAppClass, componentProvidersValue);
                    _rc.setApplication(innerApp);
                }
            }
            return app;
        }
    }