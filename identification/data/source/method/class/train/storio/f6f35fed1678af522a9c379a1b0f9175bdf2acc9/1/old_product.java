@NonNull
    public static Changes newInstance(@NonNull Set<String> affectedTables) {
        return new Changes(affectedTables);
    }