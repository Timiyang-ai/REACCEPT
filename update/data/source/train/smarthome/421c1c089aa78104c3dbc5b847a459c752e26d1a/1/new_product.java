public Map<String, Config> extractBrokerConfigurations(Map<String, Object> properties) {
        Map<String, Map<String, String>> configPerBroker = new HashMap<String, Map<String, String>>();
        for (Entry<String, Object> entry : properties.entrySet()) {
            String key = entry.getKey();
            // ignore the non-broker properties
            if (key.equals("service.pid") || key.equals("objectClass") || key.equals("component.name")
                    || key.equals("component.id")) {
                continue;
            }

            if (!(entry.getValue() instanceof String)) {
                logger.warn("Unexpected value in broker configuration {}:{}", entry.getKey(), entry.getValue());
                continue;
            }

            String value = (String) entry.getValue();

            String[] subkeys = key.split("\\.");
            if (subkeys.length != 2 || StringUtils.isBlank(value)) {
                logger.debug("MQTT Broker property '{}={}' should have the format 'broker.propertykey=value'", key,
                        value);
                continue;
            }
            String brokername = subkeys[0].toLowerCase();

            Map<String, String> brokerConfig = configPerBroker.get(brokername);
            if (brokerConfig == null) {
                brokerConfig = new HashMap<>();
                configPerBroker.put(brokername, brokerConfig);
                brokerConfig.put(NAME_PROPERTY, brokername);
            }

            brokerConfig.put(subkeys[1], value);
        }

        return configPerBroker.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> new Config(entry.getValue())));
    }