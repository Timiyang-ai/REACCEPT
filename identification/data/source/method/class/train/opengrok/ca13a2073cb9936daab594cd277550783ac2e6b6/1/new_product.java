public static void addExtension(String extension,
            FileAnalyzerFactory factory) {
        FileAnalyzerFactory oldFactory;
        if (factory == null) {
            oldFactory = ext.remove(extension);
        } else {
            oldFactory = ext.put(extension, factory);
        }

        if (factoriesDifferent(factory, oldFactory)) {
            addCustomizationKey("e:" + extension);
        }
    }