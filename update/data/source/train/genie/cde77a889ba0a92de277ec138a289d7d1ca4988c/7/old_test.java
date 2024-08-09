@Test
    public void testSetProcessIdForJob() throws GenieException {
        Assert.assertEquals(-1, this.service.getJob(JOB_1_ID).getProcessHandle());
        final Random random = new Random();
        int pid = -1;
        while (pid < 0) {
            pid = random.nextInt();
        }
        this.service.setProcessIdForJob(JOB_1_ID, pid);
        Assert.assertEquals(pid, this.service.getJob(JOB_1_ID).getProcessHandle());
    }