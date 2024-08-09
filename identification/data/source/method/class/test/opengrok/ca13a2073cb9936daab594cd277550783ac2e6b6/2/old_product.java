public static void addExtension(String extension, Class<? extends FileAnalyzer> analyzer) {
        ext.remove(extension);
        if (analyzer != null) {
            ext.put(extension, analyzer);
        }
    }