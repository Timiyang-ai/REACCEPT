public CompletionStage<E> add(CompletionStage<E> future) throws InterruptedException {
        checkNotNull(future, "future can't be null");
        this.thread = Thread.currentThread();

        down();
        futures.add(future);
        future.whenCompleteAsync((response, t) -> up(), CALLER_RUNS);
        return future;
    }