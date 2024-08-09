public static StreamManager create(URI controller) {
        return create(ClientConfig.builder().controllerURI(controller).build());
    }