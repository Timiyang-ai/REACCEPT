@Test
    public void testSetUpdateTime() throws GenieException {
        final long initialUpdated = this.service.getJob(JOB_1_ID).getUpdated().getTime();
        final long newUpdated = this.service.setUpdateTime(JOB_1_ID);
        Assert.assertNotEquals(initialUpdated, newUpdated);
    }