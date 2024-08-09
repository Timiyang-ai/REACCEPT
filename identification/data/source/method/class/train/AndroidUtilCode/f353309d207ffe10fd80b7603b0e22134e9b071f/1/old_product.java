public static ReflectUtils reflect(final String name, final ClassLoader classLoader)
            throws ReflectException {
        return reflect(forName(name, classLoader));
    }