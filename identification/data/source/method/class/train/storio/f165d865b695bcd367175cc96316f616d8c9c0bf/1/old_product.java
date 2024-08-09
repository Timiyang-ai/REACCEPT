public boolean wasDeleted(@NonNull T object) {
        return results.containsKey(object);
    }