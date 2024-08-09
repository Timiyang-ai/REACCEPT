    @Test
    public void test_getShardingItemParameters_withInvalidNonnumericKey() {
        JobConfiguration jobConfiguration = mock(JobConfiguration.class);
        when(jobConfiguration.getShardingItemParameters()).thenReturn("0=1,1=2,1={aa},2=a,a=");
        JobScheduler jobScheduler = mock(JobScheduler.class);
        when(jobScheduler.getCurrentConf()).thenReturn(jobConfiguration);
        ConfigurationService configurationService = new ConfigurationService(jobScheduler);
        Map parameters = configurationService.getShardingItemParameters();
        assertNull(parameters.get("a"));
    }