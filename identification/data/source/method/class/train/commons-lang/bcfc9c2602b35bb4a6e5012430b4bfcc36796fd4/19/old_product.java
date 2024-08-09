public static List getAllSuperclasses(Class cls) {
        if (cls == null) {
            return null;
        }
        List classes = new ArrayList();
        Class superclass = cls.getSuperclass();
        while (superclass != null) {
            classes.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return classes;
    }