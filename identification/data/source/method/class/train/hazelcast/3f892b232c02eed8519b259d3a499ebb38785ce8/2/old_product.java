public ICompletableFuture<E> add(ICompletableFuture<E> future) throws InterruptedException {
        checkNotNull(future, "future can't be null");
        this.thread = Thread.currentThread();

        down();
        futures.add(future);
        future.andThen(new ExecutionCallback<E>() {
            @Override
            public void onResponse(E response) {
                up();
            }

            @Override
            public void onFailure(Throwable t) {
                up();
            }
        }, CALLER_RUNS);
        return future;
    }