public void setConfigs(final Set<String> configs) throws GenieException {
        if (configs == null || configs.isEmpty()) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "At least one config required.");
        }
        this.configs = configs;
    }