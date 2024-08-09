public ICompletableFuture<E> add(ICompletableFuture<E> future) throws InterruptedException {
        checkNotNull(future, "future can't be null");

        semaphore.acquire();
        futures.add(future);
        future.andThen(new ExecutionCallback<E>() {
            @Override
            public void onResponse(E response) {
                semaphore.release();
            }

            @Override
            public void onFailure(Throwable t) {
                semaphore.release();
            }
        }, EXECUTOR);
        return future;
    }