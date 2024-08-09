public static List<DiscoveryStrategyConfig> map(List<AliasedDiscoveryConfig<?>> aliasedDiscoveryConfigs) {
        List<DiscoveryStrategyConfig> result = new ArrayList<DiscoveryStrategyConfig>();
        for (AliasedDiscoveryConfig config : aliasedDiscoveryConfigs) {
            if (config.isEnabled()) {
                result.add(createDiscoveryStrategyConfig(config));
            }
        }
        return result;
    }