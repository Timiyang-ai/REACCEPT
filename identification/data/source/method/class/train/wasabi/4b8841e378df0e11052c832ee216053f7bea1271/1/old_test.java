    @Test
    public void getAssignmentsQueueDetails() throws Exception {
        Map<String, Object> queueDetailsMap = new HashMap<String, Object>();
        try {
            queueDetailsMap.put(HOST_IP, InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {
            // ignore
        }
        Map<String, Object> testIngestionExecutorMap = new HashMap<String, Object>();
        testIngestionExecutorMap.put(QUEUE_SIZE, new Integer(0));
        Map<String, Object> ruleCacheMap = new HashMap<String, Object>();
        ruleCacheMap.put(QUEUE_SIZE, new Integer(0));
        queueDetailsMap.put(RULE_CACHE, ruleCacheMap);
        queueDetailsMap.put("test", testIngestionExecutorMap);
        assertThat(resource.getAssignmentsQueueDetails().getStatus(), is(HttpStatus.SC_OK));
        when(assignments.queuesDetails()).thenReturn(queueDetailsMap);
        Response response = resource.getAssignmentsQueueDetails();
        assertEquals(queueDetailsMap, response.getEntity());
    }