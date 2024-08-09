public static <T extends Entity> List<T> findByProperties(Class<T> clazz, Map<String, Object> propValues) {
        return getRepository().findByProperties(clazz, propValues);
    }