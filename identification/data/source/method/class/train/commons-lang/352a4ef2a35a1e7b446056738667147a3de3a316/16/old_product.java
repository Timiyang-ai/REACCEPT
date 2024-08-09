@SuppressWarnings("unchecked")
    public static <T> T[] add(T[] array, T element) {
        Class<?> type = array != null ? array.getClass() : (element != null ? element.getClass() : Object.class);
        T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[newArray.length - 1] = element;
        return newArray;
    }