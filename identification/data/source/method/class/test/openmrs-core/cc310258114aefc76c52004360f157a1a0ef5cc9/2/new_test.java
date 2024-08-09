    @Test
    public void setMemberIds_shouldSupportLargeCohorts() {
	    int cohortSize = 100000;
        Cohort c = new Cohort();
        Set<Integer> ids = new HashSet<>();
        for (int i=0; i<cohortSize; i++) {
            ids.add(i);
        }
        long startTime = System.currentTimeMillis();
        c.setMemberIds(ids);
        long endTime = System.currentTimeMillis();
        double secondsToSet = (endTime - startTime)/1000;
        Assert.assertTrue("Setting cohort of size " + cohortSize + " took " + secondsToSet + " seconds", secondsToSet < 5);
    }