    @Test
    public void createFromFileTestnetWithTestnetTrueAndFalse() throws IOException {
        // lets assume in our configFile is TESTNET=true
        File configFile = createTestnetConfigFile("true");

        // but the parameter is set to testnet=false
        IotaConfig iotaConfig = ConfigFactory.createFromFile(configFile, false);
        assertTrue("Expected iotaConfig as instance of TestnetConfig.", iotaConfig instanceof TestnetConfig);
        assertTrue("Expected iotaConfig as Testnet.", iotaConfig.isTestnet());
    }