public static IotaConfig createFromFile(File configFile, boolean testnet) throws IOException,
            IllegalArgumentException {
        IotaConfig iotaConfig;

        try (FileInputStream confStream = new FileInputStream(configFile)) {
            Properties props = new Properties();
            props.load(confStream);
            boolean isTestnet = testnet || Boolean.parseBoolean(props.getProperty("TESTNET", "false"));
            Class<? extends IotaConfig> iotaConfigClass = isTestnet ? TestnetConfig.class : MainnetConfig.class;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            SimpleModule booleanParser = new SimpleModule("BooleanParser");
            booleanParser.addDeserializer(Boolean.TYPE, new CustomBoolDeserializer());
            objectMapper.registerModule(booleanParser);

            SimpleModule stringParser = new SimpleModule("StringParser");
            stringParser.addDeserializer(String.class, new CustomStringDeserializer());
            objectMapper.registerModule(stringParser);

            iotaConfig = objectMapper.convertValue(props, iotaConfigClass);
        }
        return iotaConfig;
    }