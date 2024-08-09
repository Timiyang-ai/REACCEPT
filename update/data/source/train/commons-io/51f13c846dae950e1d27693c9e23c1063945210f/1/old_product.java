public static Set<File> filterSet(final IOFileFilter filter, final File... files) {
        final File[] acceptedFiles = filter(filter, files);
        return new HashSet<File>(Arrays.asList(acceptedFiles));
    }