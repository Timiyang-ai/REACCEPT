    private ConfigserverConfig createTestConfig() {
        ConfigserverConfig.Builder builder = new ConfigserverConfig.Builder();
        builder.zookeeperserver(createZKBuilder(localhost, port1));
        builder.zookeeperserver(createZKBuilder(localhost, port2));
        return new ConfigserverConfig(builder);
    }