@NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOContentResolver storIOContentResolver, @NonNull DeleteQuery deleteQuery) {
        final int numberOfRowsDeleted = storIOContentResolver.internal().delete(deleteQuery);
        return DeleteResult.newInstance(numberOfRowsDeleted, deleteQuery.uri);
    }