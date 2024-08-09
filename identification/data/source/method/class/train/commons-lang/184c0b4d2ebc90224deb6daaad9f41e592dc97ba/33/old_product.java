public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        List<Class<?>> list = new ArrayList<Class<?>>();
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (list.contains(interfaces[i]) == false) {
                    list.add(interfaces[i]);
                }
                List<Class<?>> superInterfaces = getAllInterfaces(interfaces[i]);
                for (Iterator<Class<?>> it = superInterfaces.iterator(); it.hasNext();) {
                    Class<?> intface = it.next();
                    if (list.contains(intface) == false) {
                        list.add(intface);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return list;
    }