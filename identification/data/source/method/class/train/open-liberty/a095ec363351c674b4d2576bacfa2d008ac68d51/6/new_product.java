@Override
    public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> action) {
        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        WSContextService contextSvc = ((WSManagedExecutorService) defaultExecutor).getContextService();

        @SuppressWarnings("unchecked")
        ThreadContextDescriptor contextDescriptor = contextSvc.captureThreadContext(XPROPS_SUSPEND_TRAN);
        action = new ContextualFunction<>(contextDescriptor, action);

        if (JAVA8) {
            CompletableFuture<T> dependentStage = completableFuture.exceptionally(action);
            return new ManagedCompletableFuture<T>(dependentStage, defaultExecutor, null);
        } else {
            return super.exceptionally(action);
        }
    }