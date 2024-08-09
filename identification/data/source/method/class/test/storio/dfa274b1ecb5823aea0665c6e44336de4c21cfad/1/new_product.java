@NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOContentResolver storIOContentResolver, @NonNull T object) {
        final DeleteQuery deleteQuery = mapToDeleteQuery(object);
        final int numberOfRowsDeleted = storIOContentResolver.lowLevel().delete(deleteQuery);
        return DeleteResult.newInstance(numberOfRowsDeleted, deleteQuery.uri());
    }