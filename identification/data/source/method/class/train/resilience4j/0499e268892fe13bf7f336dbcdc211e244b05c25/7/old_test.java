    @Test
    public void decorateConsumer() {
        Consumer<Integer> consumer = mock(Consumer.class);
        Consumer<Integer> decorated = RateLimiter.decorateConsumer(limit, consumer);
        given(limit.acquirePermission(1)).willReturn(false);
        Try<Integer> decoratedConsumerResult = Try.success(1).andThen(decorated);
        assertThat(decoratedConsumerResult.isFailure()).isTrue();
        assertThat(decoratedConsumerResult.getCause()).isInstanceOf(RequestNotPermitted.class);
        then(consumer).should(never()).accept(any());
        given(limit.acquirePermission(1)).willReturn(true);

        Try secondConsumerResult = Try.success(1).andThen(decorated);

        assertThat(secondConsumerResult.isSuccess()).isTrue();
        then(consumer).should().accept(1);
    }