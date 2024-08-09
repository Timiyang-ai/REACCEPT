    @Test
    public void decorateCheckedSupplier() throws Throwable {
        CheckedFunction0 supplier = mock(CheckedFunction0.class);
        CheckedFunction0 decorated = RateLimiter.decorateCheckedSupplier(limit, supplier);
        given(limit.acquirePermission(1)).willReturn(false);
        Try decoratedSupplierResult = Try.of(decorated);
        assertThat(decoratedSupplierResult.isFailure()).isTrue();
        assertThat(decoratedSupplierResult.getCause()).isInstanceOf(RequestNotPermitted.class);
        then(supplier).should(never()).apply();
        given(limit.acquirePermission(1)).willReturn(true);

        Try secondSupplierResult = Try.of(decorated);

        assertThat(secondSupplierResult.isSuccess()).isTrue();
        then(supplier).should().apply();
    }