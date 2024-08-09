    @Test
    public void makeRowKey() {
        String applicationName = "TESTAPP";
        short serviceType = 123;
        long time = System.currentTimeMillis();

        byte[] bytes = ApplicationMapStatisticsUtils.makeRowKey(applicationName, serviceType, time);

        Assert.assertEquals(applicationName, ApplicationMapStatisticsUtils.getApplicationNameFromRowKey(bytes));
        Assert.assertEquals(serviceType, ApplicationMapStatisticsUtils.getApplicationTypeFromRowKey(bytes));
    }