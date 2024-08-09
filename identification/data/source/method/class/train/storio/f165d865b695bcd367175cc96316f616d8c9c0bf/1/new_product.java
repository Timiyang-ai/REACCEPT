public boolean wasDeleted(@NonNull T object) {
        final DeleteResult result = results.get(object);
        return result != null && result.numberOfRowsDeleted() > 0;
    }