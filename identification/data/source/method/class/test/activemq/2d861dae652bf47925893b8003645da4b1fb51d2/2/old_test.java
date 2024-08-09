    @Test
    public void testgetAllJobs() throws Exception {
        final int COUNT = 10;
        final String ID = "id:";
        long time = 20000;

        for (int i = 0; i < COUNT; i++) {
            String str = new String("test" + i);
            scheduler.schedule(ID + i, new ByteSequence(str.getBytes()), "", time, 10 + i, -1);
        }

        List<Job> list = scheduler.getAllJobs();

        assertEquals(list.size(), COUNT);
        int count = 0;
        for (Job job : list) {
            assertEquals(job.getJobId(), ID + count);
            count++;
        }
    }