    @Test
    public void decorateSupplier() {
        Supplier supplier = mock(Supplier.class);
        Supplier decorated = RateLimiter.decorateSupplier(limit, supplier);
        given(limit.acquirePermission(1)).willReturn(false);
        Try decoratedSupplierResult = Try.success(decorated).map(Supplier::get);
        assertThat(decoratedSupplierResult.isFailure()).isTrue();
        assertThat(decoratedSupplierResult.getCause()).isInstanceOf(RequestNotPermitted.class);
        then(supplier).should(never()).get();
        given(limit.acquirePermission(1)).willReturn(true);

        Try secondSupplierResult = Try.success(decorated).map(Supplier::get);

        assertThat(secondSupplierResult.isSuccess()).isTrue();
        then(supplier).should().get();
    }