    @Test
    public void getCompleteLogs() throws Exception {
        AuditLog al = mock(AuditLogImpl.class);
        Authorization auth = mock(Authorization.class);
        List<AuditLogEntry> list = new ArrayList<>();
        AuditLogResource lr = new AuditLogResource(al, auth,
                new HttpHeader("MyApp-???", "600"),
                new PaginationHelper<>(new AuditLogEntryFilter(), new AuditLogEntryComparator()));

        Mockito.when(al.getAuditLogs()).thenReturn(list);
        Response r = lr.getLogsForAllApplications(
                "", 1, 10, "", "", null);
        assertThat("{logEntries=[], totalEntries=0}", is(r.getEntity().toString()));

        for (int i = 0; i < 5; i++) {
            list.add(AuditLogEntryFactory.createFromEvent(new SimpleEvent("Event")));
        }
        Mockito.when(al.getAuditLogs()).thenReturn(list);
        r = lr.getLogsForAllApplications(
                "", 1, 10, "", "", null);
        Assert.assertTrue(r.getEntity().toString().contains("totalEntries=5"));

        for (int i = 0; i < 6; i++) {
            list.add(AuditLogEntryFactory.createFromEvent(new SimpleEvent("Event")));
        }
        Mockito.when(al.getAuditLogs()).thenReturn(list);
        r = lr.getLogsForAllApplications(
                "", 1, 10, "", "", null);
        Assert.assertTrue(r.getEntity().toString().contains("totalEntries=11"));
    }