@Override
    public Void call() {
        try {
            Settings.setInstance(settings);

            if (shouldAnalyze()) {
                LOGGER.debug("Begin Analysis of '{}' ({})", dependency.getActualFilePath(), analyzer.getName());
                try {
                    analyzer.analyze(dependency, engine);
                } catch (AnalysisException ex) {
                    LOGGER.warn("An error occurred while analyzing '{}' ({}).", dependency.getActualFilePath(), analyzer.getName());
                    LOGGER.debug("", ex);
                    exceptions.add(ex);
                } catch (Throwable ex) {
                    LOGGER.warn("An unexpected error occurred during analysis of '{}' ({}): {}",
                            dependency.getActualFilePath(), analyzer.getName(), ex.getMessage());
                    LOGGER.debug("", ex);
                    exceptions.add(ex);
                }
            }
        } finally {
            Settings.cleanup(false);
        }
        return null;
    }