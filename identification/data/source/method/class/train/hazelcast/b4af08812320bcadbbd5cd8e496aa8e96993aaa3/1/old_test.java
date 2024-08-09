    @Test
    public void map() {
        // given
        List<AliasedDiscoveryConfig<?>> aliasedDiscoveryConfigs = new ArrayList<>();
        aliasedDiscoveryConfigs
                .add(new GcpConfig().setEnabled(true).setProperty("projects", "hazelcast-33").setProperty("zones", "us-east1-b"));
        aliasedDiscoveryConfigs.add(new AwsConfig() { }.setEnabled(true).setProperty("access-key", "someAccessKey")
                .setProperty("secret-key", "someSecretKey").setProperty("region", "eu-central-1"));

        // when
        List<DiscoveryStrategyConfig> discoveryConfigs = AliasedDiscoveryConfigUtils.map(aliasedDiscoveryConfigs);

        // then
        DiscoveryStrategyConfig gcpConfig = discoveryConfigs.get(0);
        assertEquals("com.hazelcast.gcp.GcpDiscoveryStrategy", gcpConfig.getClassName());
        assertEquals("hazelcast-33", gcpConfig.getProperties().get("projects"));
        assertEquals("us-east1-b", gcpConfig.getProperties().get("zones"));

        DiscoveryStrategyConfig awsConfig = discoveryConfigs.get(1);
        assertEquals("com.hazelcast.aws.AwsDiscoveryStrategy", awsConfig.getClassName());
        assertEquals("someAccessKey", awsConfig.getProperties().get("access-key"));
        assertEquals("someSecretKey", awsConfig.getProperties().get("secret-key"));
        assertEquals("eu-central-1", awsConfig.getProperties().get("region"));
    }