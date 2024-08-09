    @Test
    public void serviceNames() {
        assertThat(ThriftServiceUtils.serviceNames(THttpService.of(asyncService)))
                .isEqualTo(ImmutableSet.of(SERVICE_NAME));
        assertThat(ThriftServiceUtils.serviceNames(THttpService.of(ImmutableMap.of("async", asyncService,
                                                                                   "sync", syncService))))
                .isEqualTo(ImmutableSet.of(SERVICE_NAME));
    }