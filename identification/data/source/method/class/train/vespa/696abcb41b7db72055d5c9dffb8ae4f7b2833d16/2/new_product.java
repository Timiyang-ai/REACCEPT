public static Curator create(String connectionSpec, Optional<File> clientConfigFile) {
        return new Curator(connectionSpec, connectionSpec, clientConfigFile);
    }