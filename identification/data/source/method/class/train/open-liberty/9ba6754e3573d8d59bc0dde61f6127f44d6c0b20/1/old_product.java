@Override
    public ManagedCompletableFuture<Void> thenRun(Runnable action) {
        ContextualRunnable contextualAction = new ContextualRunnable(defaultExecutor.getContextService(), action);
        CompletableFuture<Void> dependentStage = completableFuture.thenRun(contextualAction);
        return new ManagedCompletableFuture<Void>(dependentStage, defaultExecutor);
    }