private List<Analyzer> getAnalyzers(List<AnalysisPhase> phases) {
        final List<Analyzer> analyzers = new ArrayList<>();
        final Iterator<Analyzer> iterator = service.iterator();
        boolean experimentalEnabled = false;
        boolean retiredEnabled = false;
        try {
            experimentalEnabled = Settings.getBoolean(Settings.KEYS.ANALYZER_EXPERIMENTAL_ENABLED, false);
            retiredEnabled = Settings.getBoolean(Settings.KEYS.ANALYZER_RETIRED_ENABLED, false);
        } catch (InvalidSettingException ex) {
            LOGGER.error("invalid experimental or retired setting", ex);
        }
        while (iterator.hasNext()) {
            final Analyzer a = iterator.next();
            if (!phases.contains(a.getAnalysisPhase())) {
                continue;
            }
            if (!experimentalEnabled && a.getClass().isAnnotationPresent(Experimental.class)) {
                continue;
            }
            if (!retiredEnabled && a.getClass().isAnnotationPresent(Retired.class)) {
                continue;
            }
            LOGGER.debug("Loaded Analyzer {}", a.getName());
            analyzers.add(a);
        }
        return analyzers;
    }