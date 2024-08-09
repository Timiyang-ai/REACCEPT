public static synchronized void register(FileSystem service) {
        SERVICES.add(service);
    }