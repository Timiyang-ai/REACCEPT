private void validate(
            final ApplicationStatus status) throws GenieException {
        final StringBuilder builder = new StringBuilder();
        if (status == null) {
            builder.append("No application status entered and is required.\n");
        }

        if (builder.length() > 0) {
            builder.insert(0, "Application configuration errors:\n");
            final String msg = builder.toString();
            LOG.error(msg);
            throw new GenieException(HttpURLConnection.HTTP_BAD_REQUEST, msg);
        }
    }