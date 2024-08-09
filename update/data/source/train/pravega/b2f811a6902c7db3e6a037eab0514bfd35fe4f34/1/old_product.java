static StreamManager create(URI controller, PravegaCredentials credentials) {
        boolean enableTls = Boolean.parseBoolean(System.getProperty("io.pravega.auth.enabled"));
        String tlsCertFile = System.getProperty("io.pravega.auth.certfile");
        return new StreamManagerImpl(controller, credentials, enableTls, tlsCertFile);
    }