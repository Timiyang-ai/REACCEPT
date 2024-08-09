public static String getImageType(InputStream is) {
        if (is == null) return null;
        try {
            byte[] bytes = new byte[8];
            is.read(bytes);
            return getImageType(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }