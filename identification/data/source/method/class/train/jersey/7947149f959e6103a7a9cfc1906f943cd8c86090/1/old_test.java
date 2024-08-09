    private ApplicationHandler createApplication(Class<?>... classes) {
        final ResourceConfig resourceConfig = new ResourceConfig(classes);

        return new ApplicationHandler(resourceConfig);
    }