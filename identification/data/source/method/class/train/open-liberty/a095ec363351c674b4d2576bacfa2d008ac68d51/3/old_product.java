@Override
    public ManagedCompletableFuture<T> exceptionally(Function<Throwable, ? extends T> action) {
        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        WSContextService contextSvc = defaultExecutor.getContextService();

        @SuppressWarnings("unchecked")
        ThreadContextDescriptor contextDescriptor = contextSvc.captureThreadContext(XPROPS_SUSPEND_TRAN);
        action = new ContextualFunction<>(contextDescriptor, action);

        CompletableFuture<T> dependentStage = completableFuture.exceptionally(action);
        return new ManagedCompletableFuture<T>(dependentStage, defaultExecutor);
    }