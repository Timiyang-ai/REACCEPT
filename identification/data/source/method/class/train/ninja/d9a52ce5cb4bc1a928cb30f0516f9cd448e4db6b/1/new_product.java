static public Class<? extends Standalone> resolveStandaloneClass() {
        return resolveStandaloneClass(
            System.getProperty(Standalone.KEY_NINJA_STANDALONE_CLASS),
            ForwardingServiceLoader.loadWithSystemServiceLoader(Standalone.class),
            Standalone.DEFAULT_STANDALONE_CLASS
        );
    }