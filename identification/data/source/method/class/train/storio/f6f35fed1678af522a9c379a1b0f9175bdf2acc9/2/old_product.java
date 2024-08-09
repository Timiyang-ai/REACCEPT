@NonNull
    public static Changes newInstance(@NonNull String affectedTable) {
        checkNotNull(affectedTable, "Please specify affected table");
        return new Changes(Collections.singleton(affectedTable));
    }