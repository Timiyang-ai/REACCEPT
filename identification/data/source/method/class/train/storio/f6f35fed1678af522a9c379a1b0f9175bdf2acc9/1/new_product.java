@NonNull
    public static Changes newInstance(
            @NonNull Set<String> affectedTables,
            @Nullable Collection<String> affectedTags
    ) {
        return new Changes(affectedTables, nonNullSet(affectedTags));
    }