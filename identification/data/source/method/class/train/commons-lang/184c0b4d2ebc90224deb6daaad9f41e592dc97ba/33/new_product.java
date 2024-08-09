public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> intface : interfaces) {
                if (list.contains(intface) == false) {
                    list.add(intface);
                }
                List<Class<?>> superInterfaces = getAllInterfaces(intface);
                for (Class<?> superInterface : superInterfaces) {
                    if (list.contains(superInterface) == false) {
                        list.add(superInterface);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return list;
    }