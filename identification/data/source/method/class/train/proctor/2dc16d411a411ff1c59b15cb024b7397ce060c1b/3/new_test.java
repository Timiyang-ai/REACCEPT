    @Test
    public void getEnvironmentVersion() throws StoreException, InterruptedException, ExecutionException, TimeoutException {
        final Future<SingleEnvironmentVersion> mockedFuture = Mockito.mock(Future.class);
        Mockito.when(mockedFuture.get(Mockito.anyLong(), Mockito.eq(TimeUnit.SECONDS)))
                .thenReturn(new SingleEnvironmentVersion(TRUNK_HISTORY.get(0), TRUNK_REVISION))
                .thenReturn(new SingleEnvironmentVersion(QA_HISTORY.get(0), QA_REVISION))
                .thenReturn(new SingleEnvironmentVersion(PRODUCTION_HISTORY.get(0), PROD_REVISION));
        Mockito.when(executorService.submit(Mockito.any(Callable.class)))
                .thenReturn(mockedFuture);

        final EnvironmentVersion environmentVersion = proctorPromoter.getEnvironmentVersion(TEST_NAME);
        Assertions.assertThat(environmentVersion.getTestName()).isEqualTo(TEST_NAME);
        Assertions.assertThat(environmentVersion.getTrunk()).isEqualTo(TRUNK_HISTORY.get(0));
        Assertions.assertThat(environmentVersion.getTrunkVersion()).isEqualTo(TRUNK_REVISION);
        Assertions.assertThat(environmentVersion.getQa()).isEqualTo(QA_HISTORY.get(0));
        Assertions.assertThat(environmentVersion.getQaVersion()).isEqualTo(QA_REVISION);
        Assertions.assertThat(environmentVersion.getProduction()).isEqualTo(PRODUCTION_HISTORY.get(0));
        Assertions.assertThat(environmentVersion.getProductionVersion()).isEqualTo(PROD_REVISION);
    }