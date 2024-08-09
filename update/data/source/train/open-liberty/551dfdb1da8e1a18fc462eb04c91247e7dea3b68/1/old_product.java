@Override
    public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
        // Reject ManagedTask so that we have the flexibility to decide later how to handle ManagedTaskListener and execution properties
        if (action instanceof ManagedTask)
            throw new IllegalArgumentException(ManagedTask.class.getName());

        ThreadContextDescriptor contextDescriptor = captureThreadContext(defaultExecutor);
        if (contextDescriptor != null)
            action = new ContextualConsumer<>(contextDescriptor, action);

        if (JAVA8) {
            if (other instanceof ManagedCompletableFuture)
                other = ((ManagedCompletableFuture<? extends T>) other).completableFuture;
            CompletableFuture<Void> dependentStage = completableFuture.acceptEither(other, action);
            return new ManagedCompletableFuture<Void>(dependentStage, defaultExecutor, null);
        } else {
            return super.acceptEither(other, action);
        }
    }