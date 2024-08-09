@SuppressWarnings("unchecked")
    public static <T> T[] add(T[] array, int index, T element) {
        Class<?> clss = null;
        if (array != null) {
            clss = array.getClass().getComponentType();
        } else if (element != null) {
            clss = element.getClass();
        } else {
            return (T[]) new Object[] { null };
        }
        return (T[]) add(array, index, element, clss);
    }