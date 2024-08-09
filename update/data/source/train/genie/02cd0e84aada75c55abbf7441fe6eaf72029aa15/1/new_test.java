@Test
    public void testQuery() throws Exception {
        PersistenceManager<Job> pm = new PersistenceManager<Job>();
        Job initial = new Job();
        UUID uuid = UUID.randomUUID();
        initial.setJobID(uuid.toString());
        initial.setJobName("My test job");
        initial.setJobStatus(JobStatus.FAILED);
        initial.setUpdateTime(System.currentTimeMillis());
        initial.setUserName("myUserName");
        pm.createEntity(initial);
        ClauseBuilder cb = new ClauseBuilder(ClauseBuilder.AND);
        cb.append("jobID='" + initial.getJobID() + "'");
        cb.append("userName='myUserName'");
        QueryBuilder qb = new QueryBuilder().table("Job").clause(
                cb.toString());
        Object[] results = pm.query(qb);
        Assert.assertEquals(1, results.length);
        Assert.assertEquals(results[0] instanceof Job, true);
    }