@NonNull
    @Override
    public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull T object) {
        final DeleteQuery deleteQuery = mapToDeleteQuery(object);
        final int numberOfRowsDeleted = storIOSQLite.lowLevel().delete(deleteQuery);
        return DeleteResult.newInstance(numberOfRowsDeleted, deleteQuery.table());
    }