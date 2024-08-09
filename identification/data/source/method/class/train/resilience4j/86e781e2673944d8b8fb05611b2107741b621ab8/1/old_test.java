    @Test
    public void decorateCompletionStage() {
        Supplier supplier = mock(Supplier.class);
        given(supplier.get()).willReturn("Resource");
        Supplier<CompletionStage<String>> completionStage = () -> supplyAsync(supplier);
        Supplier<CompletionStage<String>> decorated = RateLimiter
            .decorateCompletionStage(limit, completionStage);
        given(limit.acquirePermission(1)).willReturn(false);
        AtomicReference<Throwable> error = new AtomicReference<>(null);
        CompletableFuture<String> notPermittedFuture = decorated.get()
            .whenComplete((v, e) -> error.set(e))
            .toCompletableFuture();

        Try<String> errorResult = Try.of(notPermittedFuture::get);

        assertTrue(errorResult.isFailure());
        assertThat(errorResult.getCause()).isInstanceOf(ExecutionException.class);
        assertThat(notPermittedFuture.isCompletedExceptionally()).isTrue();
        assertThat(error.get()).isExactlyInstanceOf(RequestNotPermitted.class);
        then(supplier).should(never()).get();

        given(limit.acquirePermission(1)).willReturn(true);
        AtomicReference<Throwable> shouldBeEmpty = new AtomicReference<>(null);
        CompletableFuture<String> success = decorated.get()
            .whenComplete((v, e) -> error.set(e))
            .toCompletableFuture();

        Try<String> successResult = Try.of(success::get);

        assertThat(successResult.isSuccess()).isTrue();
        assertThat(success.isCompletedExceptionally()).isFalse();
        assertThat(shouldBeEmpty.get()).isNull();
        then(supplier).should().get();
    }