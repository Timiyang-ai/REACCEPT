@Test
    public void extractBrokerConfigurationsTests() throws ConfigurationException, MqttException {
        MqttService service = new MqttService();

        Map<String, Object> properties = new Hashtable<>();
        properties.put("bam.name", "brokername");
        properties.put("bam.url", "tcp://123.123.123.123");
        Map<String, MqttService.Config> map = service.extractBrokerConfigurations(properties);
        assertEquals(map.size(), 1);
        MqttService.Config data = map.get("bam");
        assertNotNull(data);
        assertEquals("brokername", data.name);
        assertEquals("tcp://123.123.123.123", data.url);
    }