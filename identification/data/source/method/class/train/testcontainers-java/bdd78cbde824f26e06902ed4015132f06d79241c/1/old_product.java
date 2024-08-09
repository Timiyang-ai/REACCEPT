private String dockerHostIpAddress(DockerClientConfig config) {
        return config.getUri().getHost();
    }