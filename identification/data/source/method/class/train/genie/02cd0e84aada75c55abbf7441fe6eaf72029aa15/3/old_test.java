@Test
    public void testUpdateEntity() {
        PersistenceManager<Job> pm = new PersistenceManager<Job>();
        Job initial = new Job();
        UUID uuid = UUID.randomUUID();
        initial.setJobID(uuid.toString());
        pm.createEntity(initial);
        initial.setJobStatus(JobStatus.FAILED);
        Job updated = pm.updateEntity(initial);
        Assert.assertEquals("FAILED", updated.getStatus());
    }