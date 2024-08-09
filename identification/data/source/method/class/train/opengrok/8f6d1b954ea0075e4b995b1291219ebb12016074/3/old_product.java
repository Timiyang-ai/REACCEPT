public static void addPrefix(String prefix,
            FileAnalyzerFactory factory) {
        if (factory == null) {
            pre.remove(prefix);
        } else {
            pre.put(prefix, factory);
        }
    }