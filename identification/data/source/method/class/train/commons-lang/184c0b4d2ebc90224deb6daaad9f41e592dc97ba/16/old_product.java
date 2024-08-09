public static List getAllInterfaces(Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        List list = new ArrayList();
        Class[] interfaces = cls.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (list.contains(interfaces[i]) == false) {
                list.add(interfaces[i]);
            }
            List superInterfaces = getAllInterfaces(interfaces[i]);
            for (Iterator it = superInterfaces.iterator(); it.hasNext();) {
                Class intface = (Class) it.next();
                if (list.contains(intface) == false) {
                    list.add(intface);
                }
            }
        }
        return list;
    }