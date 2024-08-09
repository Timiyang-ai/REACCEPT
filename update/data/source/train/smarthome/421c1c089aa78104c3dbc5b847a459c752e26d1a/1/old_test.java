@Test
    public void extractBrokerConfigurationsTests() throws ConfigurationException, MqttException {
        MqttService service = new MqttService();

        Map<String, Object> properties = new Hashtable<>();
        properties.put("bam.name", "brokername");
        properties.put("bam.url", "tcp://123.123.123.123");
        Map<String, Map<String, String>> map = service.extractBrokerConfigurations(properties);
        assertEquals(map.size(), 1);
        Map<String, String> data = map.get("bam");
        assertNotNull(data);
        assertEquals("brokername", data.get("name"));
        assertEquals("tcp://123.123.123.123", data.get("url"));
    }