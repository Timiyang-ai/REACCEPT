@NonNull
    public <T> List<T> create(@NonNull Collection<Class<? extends T>> classes) {
        final List<T> result = new ArrayList<>();
        for (Class<? extends T> clazz : classes) {
            final T instance = create(clazz);
            if (instance != null) {
                result.add(instance);
            }
        }
        return result;
    }