    @Test
    public void init() {
        Application application = ApplicationProvider.getApplicationContext();
        CoreConfigurationBuilder builder = new CoreConfigurationBuilder(application).setPluginLoader(new SimplePluginLoader(StacktraceCollector.class, TestAdministrator.class));
        ACRA.init(application, builder);
        ACRA.getErrorReporter().handleException(new RuntimeException());
    }