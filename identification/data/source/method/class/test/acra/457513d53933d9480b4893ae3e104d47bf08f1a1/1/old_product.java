public  <T> List<T> create(Collection<Class<? extends T>> classes) {
        final List<T> result = new ArrayList<T>();
        for (Class<? extends T> clazz : classes) {
            T instance = create(clazz, null);
            if (instance != null) {
                result.add(instance);
            }
        }
        return result;
    }