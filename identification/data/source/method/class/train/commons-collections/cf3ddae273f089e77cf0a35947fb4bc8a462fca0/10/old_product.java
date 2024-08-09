public static boolean addIgnoreNull(Collection collection, Object object) {
        return (object == null ? false : collection.add(object));
    }