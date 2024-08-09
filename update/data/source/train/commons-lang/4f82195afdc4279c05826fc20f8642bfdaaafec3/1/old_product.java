public static List<Class<?>> convertClassNamesToClasses(final List<String> classNames) {
        if (classNames == null) {
            return null;
        }
        final List<Class<?>> classes = new ArrayList<Class<?>>(classNames.size());
        for (final String className : classNames) {
            try {
                classes.add(Class.forName(className));
            } catch (final Exception ex) {
                classes.add(null);
            }
        }
        return classes;
    }