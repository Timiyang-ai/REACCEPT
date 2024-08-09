@Override
    public <U, R> ManagedCompletableFuture<R> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends R> action) {
        if (other instanceof ManagedCompletableFuture)
            other = ((ManagedCompletableFuture<? extends U>) other).completableFuture;

        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        WSContextService contextSvc = defaultExecutor.getContextService();

        @SuppressWarnings("unchecked")
        ThreadContextDescriptor contextDescriptor = contextSvc.captureThreadContext(XPROPS_SUSPEND_TRAN);
        action = new ContextualBiFunction<>(contextDescriptor, action);

        CompletableFuture<R> dependentStage = completableFuture.thenCombine(other, action);
        return new ManagedCompletableFuture<R>(dependentStage, defaultExecutor, null);
    }