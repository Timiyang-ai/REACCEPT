@NonNull
    public static Changes newInstance(
            @NonNull String affectedTable,
            @Nullable Collection<String> affectedTags
    ) {
        checkNotNull(affectedTable, "Please specify affected table");
        return new Changes(singleton(affectedTable), nonNullSet(affectedTags));
    }