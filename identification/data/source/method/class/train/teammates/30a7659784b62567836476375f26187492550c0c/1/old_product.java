public static <T> String toString(List<T> list, String delimiter) {
        if (list.isEmpty()) {
            return "";
        }

        StringBuilder returnValue = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            returnValue.append(list.get(i)).append(delimiter);
        }
        //append the last item
        returnValue.append(list.get(list.size() - 1));

        return returnValue.toString();
    }