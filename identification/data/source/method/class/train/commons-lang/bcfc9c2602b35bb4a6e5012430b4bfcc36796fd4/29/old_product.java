public static List convertClassNamesToClasses(List classNames) {
        if (classNames == null) {
            throw new IllegalArgumentException("The class names must not be null");
        }
        List classes = new ArrayList(classNames.size());
        for (Iterator it = classNames.iterator(); it.hasNext();) {
            String className = (String) it.next();
            try {
                classes.add(Class.forName(className));
            } catch (Exception ex) {
                classes.add(null);
            }
        }
        return classes;
    }