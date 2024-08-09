public String dockerHostIpAddress() {
        initialize();
        return strategy.getDockerHostIpAddress();
    }