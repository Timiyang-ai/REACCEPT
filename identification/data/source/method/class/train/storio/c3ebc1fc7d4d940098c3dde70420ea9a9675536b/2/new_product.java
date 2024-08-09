@Override
    @NonNull
    public Observable<Changes> observeChangesInTables(@NonNull final Set<String> tables) {
        if (changesBus == null) {
            throw newRxJavaIsNotAvailableException("Observing changes in StorIOSQLite");
        }

        // indirect usage of RxJava filter() required to avoid problems with ClassLoader when RxJava is not in ClassPath
        return ChangesFilter.apply(changesBus, tables);
    }