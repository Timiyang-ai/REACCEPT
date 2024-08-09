@Test
    public void testAddTelemetryService() {
        addDefaultServices();

        TelemetryAdminService kafkaService = new KafkaTelemetryManager();

        assertEquals(2, manager.telemetryServices().size());

        manager.addTelemetryService(kafkaService);

        assertEquals(3, manager.telemetryServices().size());
    }