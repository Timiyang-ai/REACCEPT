public static <U> CompletionStage<U> completedStage(U value) {
        ManagedExecutorService defaultExecutor = AccessController.doPrivileged(getDefaultManagedExecutorAction);
        return completedStage(value, defaultExecutor);
    }