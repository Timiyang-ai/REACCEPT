public static void addPrefix(String prefix, FileAnalyzerFactory factory) {
        FileAnalyzerFactory oldFactory;
        if (factory == null) {
            oldFactory = pre.remove(prefix);
        } else {
            oldFactory = pre.put(prefix, factory);
        }

        if (factoriesDifferent(factory, oldFactory)) {
            addCustomizationKey("p:" + prefix);
        }
    }