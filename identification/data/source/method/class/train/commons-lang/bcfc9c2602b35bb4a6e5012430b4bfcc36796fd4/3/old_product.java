public static List getAllSuperclasses(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        List classes = new ArrayList();
        Class superclass = cls.getSuperclass();
        while (superclass != null) {
            classes.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return classes;
    }