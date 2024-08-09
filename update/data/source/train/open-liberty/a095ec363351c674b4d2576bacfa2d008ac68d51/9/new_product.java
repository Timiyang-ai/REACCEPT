@Override
    public ManagedCompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
        if (other instanceof ManagedCompletableFuture)
            other = ((ManagedCompletableFuture<? extends T>) other).completableFuture;

        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        WSContextService contextSvc = ((WSManagedExecutorService) defaultExecutor).getContextService();

        @SuppressWarnings("unchecked")
        ThreadContextDescriptor contextDescriptor = contextSvc.captureThreadContext(XPROPS_SUSPEND_TRAN);
        action = new ContextualConsumer<>(contextDescriptor, action);

        CompletableFuture<Void> dependentStage = completableFuture.acceptEither(other, action);
        return new ManagedCompletableFuture<Void>(dependentStage, defaultExecutor, null);
    }