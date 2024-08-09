@Test
    public void testDeleteEntity() {
        PersistenceManager<Job> pm = new PersistenceManager<Job>();
        Job initial = new Job();
        UUID uuid = UUID.randomUUID();
        initial.setJobID(uuid.toString());
        pm.createEntity(initial);
        Job deleted = pm.deleteEntity(uuid.toString(),
                Job.class);
        Assert.assertNotNull(deleted);
    }