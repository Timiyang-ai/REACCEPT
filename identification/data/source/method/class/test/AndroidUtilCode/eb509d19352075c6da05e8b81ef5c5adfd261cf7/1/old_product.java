public static File getFileByPath(String filePath) {
        if (StringUtils.isSpace(filePath)) return null;
        return new File(filePath);
    }