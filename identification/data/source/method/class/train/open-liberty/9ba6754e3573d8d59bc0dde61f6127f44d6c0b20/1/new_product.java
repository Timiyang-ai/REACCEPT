@Override
    public ManagedCompletableFuture<Void> thenRun(Runnable action) {
        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        WSContextService contextSvc = defaultExecutor.getContextService();

        @SuppressWarnings("unchecked")
        ThreadContextDescriptor contextDescriptor = contextSvc.captureThreadContext(XPROPS_SUSPEND_TRAN);
        action = contextSvc.createContextualProxy(contextDescriptor, action, Runnable.class);

        CompletableFuture<Void> dependentStage = completableFuture.thenRun(action);
        return new ManagedCompletableFuture<Void>(dependentStage, defaultExecutor);
    }