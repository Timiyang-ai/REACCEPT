    public static boolean filter(String path, Collection<String> includes, Collection<String> excludes) {
        return FieldFilter.filter(path, FieldFilter.toNumberedFilter(includes), excludes, true).matched;
    }