    @Test
    public void scaleThroughput() throws Exception {
        LoadCompConf orig = new LoadCompConf.Builder()
            .withId("SOME_SPOUT")
            .withParallelism(1)
            .withStream(new OutputStream("default", new NormalDistStats(500.0, 100.0, 300.0, 600.0), false))
            .build();
        assertEquals(500.0, orig.getAllEmittedAggregate(), 0.001);
        LoadCompConf scaled = orig.scaleThroughput(2.0);
        //Parallelism is same
        assertEquals(1, scaled.parallelism);
        assertEquals("SOME_SPOUT", scaled.id);
        //But throughput is the same
        assertEquals(1000.0, scaled.getAllEmittedAggregate(), 0.001);
    }