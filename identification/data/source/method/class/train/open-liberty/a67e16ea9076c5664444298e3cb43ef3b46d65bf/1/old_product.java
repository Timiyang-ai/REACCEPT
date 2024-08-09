public static <U> CompletionStage<U> completedStage(U value) {
        if (JAVA8)
            throw new UnsupportedOperationException();

        ManagedExecutorService defaultExecutor = AccessController.doPrivileged(getDefaultManagedExecutorAction);
        ManagedCompletionStage<U> stage = new ManagedCompletionStage<U>(defaultExecutor);
        stage.super_complete(value);
        return stage;
    }