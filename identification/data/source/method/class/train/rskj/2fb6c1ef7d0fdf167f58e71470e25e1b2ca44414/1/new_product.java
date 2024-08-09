public static boolean hasMask(String text) {
        if (text == null)
            return false;

        String[] parts = text.split("/");

        return parts.length == 2 && parts[0].length() != 0 && parts[1].length() != 0;
    }