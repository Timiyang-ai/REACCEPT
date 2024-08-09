public WeldContainer initialize() {
        ResourceLoader resourceLoader = new WeldSEResourceLoader();
        // check for beans.xml
        if (resourceLoader.getResource(WeldSEUrlDeployment.BEANS_XML) == null)
            throw new IllegalStateException("Missing beans.xml file in META-INF!");

        final Bootstrap delegate;
        try {
            delegate = (Bootstrap) resourceLoader.classForName(BOOTSTRAP_IMPL_CLASS_NAME).newInstance();
        } catch (InstantiationException ex) {
            throw new IllegalStateException("Error loading Weld bootstrap, check that Weld is on the classpath", ex);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException("Error loading Weld bootstrap, check that Weld is on the classpath", ex);
        }

        Bootstrap bootstrap = new ForwardingBootstrap() {
            protected Bootstrap delegate() {
                return delegate;
            }

            public BeansXml parse(URL url) {
                return delegate.parse(url);
            }

            public BeansXml parse(Iterable<URL> urls) {
                return delegate.parse(urls);
            }

            public BeansXml parse(Iterable<URL> urls, boolean removeDuplicates) {
                return delegate.parse(urls, removeDuplicates);
            }

            public Iterable<Metadata<Extension>> loadExtensions(ClassLoader classLoader) {
                Iterable<Metadata<Extension>> iter = delegate.loadExtensions(classLoader);
                if (extensions != null) {
                    Set<Metadata<Extension>> set = new HashSet<Metadata<Extension>>(extensions);
                    for (Metadata<Extension> ext : iter)
                        set.add(ext);
                    return set;
                } else {
                    return iter;
                }
            }
        };

        Deployment deployment = createDeployment(resourceLoader, bootstrap);
        // Set up the container
        bootstrap.startContainer(Environments.SE, deployment);

        // Start the container
        bootstrap.startInitialization();
        bootstrap.deployBeans();
        bootstrap.validateBeans();
        bootstrap.endInitialization();

        // Set up the ShutdownManager for later
        this.shutdownManager = new ShutdownManager(bootstrap);

        WeldContainer container = getInstanceByType(bootstrap.getManager(deployment.loadBeanDeploymentArchive(WeldContainer.class)), WeldContainer.class);

        // notify container initialized
        container.event().select(ContainerInitialized.class).fire(new ContainerInitialized());

        return container;
    }