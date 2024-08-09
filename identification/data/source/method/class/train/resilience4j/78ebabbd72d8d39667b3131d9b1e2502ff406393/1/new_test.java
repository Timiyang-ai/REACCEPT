    @Test
    public void decorateTrySupplier() {
        Supplier<Try<String>> supplier = mock(Supplier.class);
        given(supplier.get()).willReturn(Try.success("Resource"));
        given(limit.acquirePermission(1)).willReturn(true);

        Try<String> result = RateLimiter.decorateTrySupplier(limit, supplier).get();

        assertThat(result.isSuccess()).isTrue();
        then(supplier).should().get();
    }