    @Test
    public void saveJob() throws Exception {
        // stub spawn calls
        Job job = new Job("new_job_id", "megatron");
        when(requestHandler.createOrUpdateJob(kv, "megatron", "megatron", null, false)).thenReturn(job);
        Response response = resource.saveJob(kv, "megatron", "megatron", null, false);
        assertEquals(200, response.getStatus());
        verifyZeroInteractions(spawn);
    }