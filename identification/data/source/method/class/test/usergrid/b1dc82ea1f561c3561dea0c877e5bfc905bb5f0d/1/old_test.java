    @Test
    public void getNextRunNumber() {

        LOG.info( "\n===RunDaoTest.getNextRunNumber===\n" );

        List<Runner> runners = ESSuiteTest.runnerDao.getRunners( ESSuiteTest.USER_1, ESSuiteTest.COMMIT_ID_2,
                ESSuiteTest.MODULE_ID_2 );

        int nextRunNumber = ESSuiteTest.runDao.getNextRunNumber( ESSuiteTest.COMMIT_ID_2 );
        assertEquals( 3, nextRunNumber );
    }