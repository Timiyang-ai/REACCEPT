public static Curator create(String connectionSpec) {
        return new Curator(connectionSpec, connectionSpec);
    }