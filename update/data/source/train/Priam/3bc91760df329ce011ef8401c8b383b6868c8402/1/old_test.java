@Test
    public void restore_maximal() throws Exception
    {
        final String dateRange = "201101010000,20111231259";
        final String newRegion = null;
        final String newToken = "5678";
        final String keyspaces = null;

        final String oldRegion = "us-east-1";
        final String oldToken = "1234";
        final String appName = "myApp";

        new Expectations() {
            @NonStrict InstanceIdentity identity;
            PriamInstance instance;
            @NonStrict PriamInstance instance1, instance2, instance3;
            AbstractBackupPath backupPath;

            {
                pathProvider.get(); result = backupPath;
                backupPath.parseDate(dateRange.split(",")[0]); result = new DateTime(2011, 01, 01, 00, 00).toDate(); times = 1;
                backupPath.parseDate(dateRange.split(",")[1]); result = new DateTime(2011, 12, 31, 23, 59).toDate(); times = 1;

                config.getDC(); result = oldRegion; times = 2;
                priamServer.getId(); result = identity; times = 5;
                identity.getInstance(); result = instance; times = 5;
                instance.getToken(); result = oldToken;
                instance.setToken(newToken);

                config.isRestoreClosestToken(); result = true;
                instance.getToken(); result = oldToken;
                config.getAppName(); result = appName;
                factory.getAllIds(appName); result = ImmutableList.of(instance, instance1, instance2, instance3);
                instance.getDC();  result = oldRegion;
                instance.getToken(); result = oldToken;
                instance1.getDC(); result = oldRegion;
                instance2.getDC(); result = oldRegion;
                instance3.getDC(); result = oldRegion;
                instance1.getToken(); result = "1234";
                instance2.getToken(); result = "5678";
                instance3.getToken(); result = "9000";
                instance.setToken((String) any); // TODO: test mocked closest token

                restoreObj.restore(
                    new DateTime(2011, 01, 01, 00, 00).toDate(),
                    new DateTime(2011, 12, 31, 23, 59).toDate());
  
                config.setDC(oldRegion);
                instance.setToken(oldToken);
                tuneCassandra.updateYaml(false);
            }
        };

        expectCassandraStartup();

        Response response = resource.restore(dateRange, newRegion, newToken, keyspaces);
        assertEquals(200, response.getStatus());
        assertEquals("[\"ok\"]", response.getEntity());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMetadata().get("Content-Type").get(0));
    }