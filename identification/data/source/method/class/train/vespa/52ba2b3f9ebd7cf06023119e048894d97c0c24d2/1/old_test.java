@Test
    public void testGrabLog() throws Exception {
        long sessionId = 1;
        ApplicationId application = new ApplicationId.Builder().applicationName(ApplicationName.defaultName()).tenant(mytenantName).build();
        addMockApplication(tenants.getTenant(mytenantName), application, sessionId, Clock.systemUTC());
        assertEquals("log line", grabLog(application, Zone.defaultZone()));
    }