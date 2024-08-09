public List<Analyzer> getAnalyzers() {
        List<Analyzer> analyzers = new ArrayList<Analyzer>();
        final Iterator<Analyzer> iterator = service.iterator();
        boolean experimentalEnabled = false;
        try {
            experimentalEnabled = Settings.getBoolean(Settings.KEYS.ANALYZER_EXPERIMENTAL_ENABLED, false);
        } catch (InvalidSettingException ex) {
            LOGGER.error("invalide experimental setting", ex);
        }
        while (iterator.hasNext()) {
            final Analyzer a = iterator.next();
            if (!experimentalEnabled && a.getClass().isAnnotationPresent(Experimental.class)) {
                continue;
            }
            LOGGER.debug("Loaded Analyzer {}", a.getName());
            analyzers.add(a);
        }
        return analyzers;
    }