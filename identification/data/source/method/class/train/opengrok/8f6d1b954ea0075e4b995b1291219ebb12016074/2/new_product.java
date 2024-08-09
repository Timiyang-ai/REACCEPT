public static void addPrefix(String prefix, AnalyzerFactory factory) {
        AnalyzerFactory oldFactory;
        if (factory == null) {
            oldFactory = pre.remove(prefix);
            langMap.exclude(prefix);
        } else {
            oldFactory = pre.put(prefix, factory);
            langMap.add(prefix, factory.getAnalyzer().getCtagsLang());
        }

        if (factoriesDifferent(factory, oldFactory)) {
            addCustomizationKey("p:" + prefix);
        }
    }