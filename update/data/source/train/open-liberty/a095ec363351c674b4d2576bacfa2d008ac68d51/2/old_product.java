@Override
    public <U> ManagedCompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        WSContextService contextSvc = ((WSManagedExecutorService) defaultExecutor).getContextService();

        @SuppressWarnings("unchecked")
        ThreadContextDescriptor contextDescriptor = contextSvc.captureThreadContext(XPROPS_SUSPEND_TRAN);
        action = new ContextualBiConsumer<>(contextDescriptor, action);

        if (JAVA8) {
            if (other instanceof ManagedCompletableFuture)
                other = ((ManagedCompletableFuture<? extends U>) other).completableFuture;
            CompletableFuture<Void> dependentStage = completableFuture.thenAcceptBoth(other, action);
            return new ManagedCompletableFuture<Void>(dependentStage, defaultExecutor, null);
        } else {
            return (ManagedCompletableFuture<Void>) super.thenAcceptBoth(other, action);
        }
    }