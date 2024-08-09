public static Curator create(String connectionSpec) {
        return Curator.create(connectionSpec, Optional.empty());
    }