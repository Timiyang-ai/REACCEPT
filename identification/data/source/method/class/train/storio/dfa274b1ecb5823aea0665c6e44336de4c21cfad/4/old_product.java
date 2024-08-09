@Override
    @NonNull
    public DeleteResult performDelete(@NonNull StorIOSQLite storIOSQLite, @NonNull DeleteQuery deleteQuery) {
        return DeleteResult.newInstance(storIOSQLite.internal().delete(deleteQuery), deleteQuery.table);
    }