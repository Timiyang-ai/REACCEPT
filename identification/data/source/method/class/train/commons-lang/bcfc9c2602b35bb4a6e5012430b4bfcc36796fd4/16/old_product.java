public static List convertClassesToClassNames(List classes) {
        if (classes == null) {
            return null;
        }
        List classNames = new ArrayList(classes.size());
        for (Iterator it = classes.iterator(); it.hasNext();) {
            Class cls = (Class) it.next();
            if (cls == null) {
                classNames.add(null);
            } else {
                classNames.add(cls.getName());
            }
        }
        return classNames;
    }