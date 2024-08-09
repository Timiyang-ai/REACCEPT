public static List<String> convertClassesToClassNames(final List<Class<?>> classes) {
        if (classes == null) {
            return null;
        }
        final List<String> classNames = new ArrayList<>(classes.size());
        for (final Class<?> cls : classes) {
            if (cls == null) {
                classNames.add(null);
            } else {
                classNames.add(cls.getName());
            }
        }
        return classNames;
    }