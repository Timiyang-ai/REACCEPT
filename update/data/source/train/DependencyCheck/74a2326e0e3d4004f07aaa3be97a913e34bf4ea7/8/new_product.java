@Override
    public final void initialize(Engine engine) throws InitializationException {
        final String key = getAnalyzerEnabledSettingKey();
        try {
            this.setEnabled(settings.getBoolean(key, true));
        } catch (InvalidSettingException ex) {
            String msg = String.format("Invalid setting for property '{}'", key);
            LOGGER.warn(msg);
            LOGGER.debug(msg, ex);
        }

        if (isEnabled()) {
            initializeAnalyzer(engine);
        } else {
            LOGGER.debug("{} has been disabled", getName());
        }
    }