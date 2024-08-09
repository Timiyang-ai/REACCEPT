@Override
    public final void initialize() throws InitializationException {
        final String key = getAnalyzerEnabledSettingKey();
        try {
            this.setEnabled(Settings.getBoolean(key, true));
        } catch (InvalidSettingException ex) {
            LOGGER.warn("Invalid setting for property '{}'", key);
            LOGGER.debug("", ex);
        }

        if (isEnabled()) {
            initializeAnalyzer();
        } else {
            LOGGER.debug("{} has been disabled", getName());
        }
    }