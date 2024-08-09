private ServerRuntime initialize(InjectionManager injectionManager,
            List<BootstrapConfigurator> bootstrapConfigurators, ServerBootstrapBag bootstrapBag) {
        this.injectionManager = injectionManager;
        LOGGER.config(LocalizationMessages.INIT_MSG(Version.getBuildId()));

        // Lock original ResourceConfig.
        if (application instanceof ResourceConfig) {
            ((ResourceConfig) application).lock();
        }

        final boolean ignoreValidationErrors = ServerProperties.getValue(runtimeConfig.getProperties(),
                ServerProperties.RESOURCE_VALIDATION_IGNORE_ERRORS,
                Boolean.FALSE,
                Boolean.class);
        final boolean disableValidation = ServerProperties.getValue(runtimeConfig.getProperties(),
                ServerProperties.RESOURCE_VALIDATION_DISABLE,
                Boolean.FALSE,
                Boolean.class);

        // Temporary way to eliminate injection manager in deeper bootstrap processing.
        final java.util.function.Function<Class<?>, ?> createServiceFunction =
                serviceType -> Injections.getOrCreate(injectionManager, serviceType);

        final Collection<ValueSupplierProvider> valueSupplierProviders;
        final ResourceBag resourceBag;
        final ProcessingProviders processingProviders;
        final JerseyResourceContext jerseyResourceContext = bootstrapBag.getResourceContext();
        final Collection<ComponentProvider> componentProviders = bootstrapBag.getComponentProviders().get();
        final ComponentBag componentBag;

        ResourceModel resourceModel;
        CompositeApplicationEventListener compositeListener = null;

        Errors.mark(); // mark begin of validation phase
        try {
            // AutoDiscoverable.
            if (!CommonProperties.getValue(runtimeConfig.getProperties(), RuntimeType.SERVER,
                    CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, Boolean.FALSE, Boolean.class)) {
                runtimeConfig.configureAutoDiscoverableProviders(injectionManager);
            } else {
                runtimeConfig.configureForcedAutoDiscoverableProviders(injectionManager);
            }

            // Configure binders and features.
            runtimeConfig.configureMetaProviders(injectionManager);

            ResourceBagConfigurator resourceBagConfigurator = new ResourceBagConfigurator();
            resourceBagConfigurator.init(injectionManager, bootstrapBag);
            resourceBag = bootstrapBag.getResourceBag();

            runtimeConfig.lock();

            componentBag = runtimeConfig.getComponentBag();

            ExternalRequestScopeConfigurator externalRequestScopeConfigurator = new ExternalRequestScopeConfigurator();
            externalRequestScopeConfigurator.init(injectionManager, bootstrapBag);

            // Adds all providers from resource config to InjectionManager -> BootstrapConfigurators are able to work with these
            // services and get them.
            bindProvidersAndResources(injectionManager, bootstrapBag, componentBag, resourceBag.classes,
                    resourceBag.instances);

            resourceModel = new ResourceModel.Builder(resourceBag.getRootResources(), false).build();
            resourceModel = processResourceModel(resourceModel);
            bindEnhancingResourceClasses(injectionManager, bootstrapBag, resourceModel, resourceBag);

            // All service are registered in InjectionManager
            injectionManager.completeRegistration();

            bootstrapConfigurators.forEach(configurator -> configurator.postInit(injectionManager, bootstrapBag));

            componentProviders.forEach(ComponentProvider::done);

            msgBodyWorkers = bootstrapBag.getMessageBodyWorkers();
            processingProviders = bootstrapBag.getProcessingProviders();
            valueSupplierProviders = bootstrapBag.getValueSupplierProviders();

            Iterable<ApplicationEventListener> appEventListeners =
                    Providers.getAllProviders(injectionManager, ApplicationEventListener.class, new RankedComparator<>());

            if (appEventListeners.iterator().hasNext()) {
                compositeListener = new CompositeApplicationEventListener(appEventListeners);
                compositeListener.onEvent(new ApplicationEventImpl(ApplicationEvent.Type.INITIALIZATION_START,
                        this.runtimeConfig, componentBag.getRegistrations(), resourceBag.classes, resourceBag.instances,
                        null));
            }

            if (!disableValidation) {
                final ComponentModelValidator validator = new ComponentModelValidator(valueSupplierProviders, msgBodyWorkers);
                validator.validate(resourceModel);
            }

            if (Errors.fatalIssuesFound() && !ignoreValidationErrors) {
                throw new ModelValidationException(LocalizationMessages.RESOURCE_MODEL_VALIDATION_FAILED_AT_INIT(),
                        ModelErrors.getErrorsAsResourceModelIssues(true));
            }
        } finally {
            if (ignoreValidationErrors) {
                Errors.logErrors(true);
                Errors.reset(); // reset errors to the state before validation phase
            } else {
                Errors.unmark();
            }
        }

        // TODO: replace by ExecutorProviderConfigurator
        ExecutorProviders.createInjectionBindings(injectionManager);

        // initiate resource model into JerseyResourceContext
        jerseyResourceContext.setResourceModel(resourceModel);

        // assembly request processing chain
        GenericType<Ref<RequestProcessingContext>> requestProcessingType = new GenericType<Ref<RequestProcessingContext>>() {};
        ReferencesInitializer referencesInitializer = new ReferencesInitializer(injectionManager,
                () -> injectionManager.getInstance(requestProcessingType.getType()));

        Iterable<RankedProvider<ModelProcessor>> allRankedProviders =
                 Providers.getAllRankedProviders(injectionManager, ModelProcessor.class);
        Iterable<ModelProcessor> modelProcessors =
                Providers.sortRankedProviders(new RankedComparator<>(), allRankedProviders);

        final ContainerFilteringStage preMatchRequestFilteringStage = new ContainerFilteringStage(
                processingProviders.getPreMatchFilters(),
                processingProviders.getGlobalResponseFilters());
        final ChainableStage<RequestProcessingContext> routingStage = Routing.forModel(resourceModel.getRuntimeResourceModel())
                .resourceContext(jerseyResourceContext)
                .configuration(runtimeConfig)
                .entityProviders(msgBodyWorkers)
                .valueSupplierProviders(valueSupplierProviders)
                .modelProcessors(modelProcessors)
                .createService(createServiceFunction)
                .processingProviders(processingProviders)
                .resourceMethodInvokerBuilder(bootstrapBag.getResourceMethodInvokerBuilder())
                .buildStage();
        final ContainerFilteringStage resourceFilteringStage =
                new ContainerFilteringStage(processingProviders.getGlobalRequestFilters(), null);
        /*
         *  Root linear request acceptor. This is the main entry point for the whole request processing.
         */
        final Stage<RequestProcessingContext> rootStage = Stages
                .chain(referencesInitializer)
                .to(preMatchRequestFilteringStage)
                .to(routingStage)
                .to(resourceFilteringStage)
                .build(Routing.matchedEndpointExtractor());

        ServerRuntime serverRuntime = ServerRuntime.createServerRuntime(
                injectionManager, bootstrapBag, rootStage, compositeListener, processingProviders);

        // Inject instances.
        for (final Object instance : componentBag.getInstances(ComponentBag.excludeMetaProviders(injectionManager))) {
            injectionManager.inject(instance);
        }
        for (final Object instance : resourceBag.instances) {
            injectionManager.inject(instance);
        }

        logApplicationInitConfiguration(injectionManager, resourceBag, processingProviders);

        if (compositeListener != null) {
            final ApplicationEvent initFinishedEvent = new ApplicationEventImpl(
                    ApplicationEvent.Type.INITIALIZATION_APP_FINISHED, runtimeConfig,
                    componentBag.getRegistrations(), resourceBag.classes, resourceBag.instances, resourceModel);
            compositeListener.onEvent(initFinishedEvent);

            final MonitoringContainerListener containerListener
                    = injectionManager.getInstance(MonitoringContainerListener.class);
            containerListener.init(compositeListener, initFinishedEvent);
        }

        return serverRuntime;
    }