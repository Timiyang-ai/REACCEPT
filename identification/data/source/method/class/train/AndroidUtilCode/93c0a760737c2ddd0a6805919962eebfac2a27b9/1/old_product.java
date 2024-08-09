public static boolean isUtf8(final String filePath) {
        try {
            return isUtf8(getFileByPath(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }