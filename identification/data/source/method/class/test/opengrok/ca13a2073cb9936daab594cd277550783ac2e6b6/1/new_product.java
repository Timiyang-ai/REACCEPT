public static void addExtension(String extension, AnalyzerFactory factory) {
        if (extension.contains(".")) {
            throw new IllegalArgumentException("extension contains a '.'");
        }

        // LangMap fileSpec requires a leading period to indicate an extension.
        String langMapExtension = "." + extension;

        AnalyzerFactory oldFactory;
        if (factory == null) {
            oldFactory = ext.remove(extension);
            langMap.exclude(langMapExtension);
        } else {
            oldFactory = ext.put(extension, factory);
            langMap.add(langMapExtension, factory.getAnalyzer().getCtagsLang());
        }

        if (factoriesDifferent(factory, oldFactory)) {
            addCustomizationKey("e:" + extension);
        }
    }