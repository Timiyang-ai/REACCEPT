    @Test
    public void isDuplicateOfLastAuditEventTest() {
        AuditEventLogger auditEventLogger = new AuditEventLogger(testAuditConfig, testWriter, formController);
        auditEventLogger.logEvent(LOCATION_PROVIDERS_ENABLED, false, 0);
        assertTrue(auditEventLogger.isDuplicateOfLastLocationEvent(LOCATION_PROVIDERS_ENABLED));
        auditEventLogger.logEvent(LOCATION_PROVIDERS_DISABLED, false, 0);
        assertTrue(auditEventLogger.isDuplicateOfLastLocationEvent(LOCATION_PROVIDERS_DISABLED));
        assertFalse(auditEventLogger.isDuplicateOfLastLocationEvent(LOCATION_PROVIDERS_ENABLED));

        auditEventLogger.exitView(); // Triggers event writing
        assertEquals(2, testWriter.auditEvents.size());
    }