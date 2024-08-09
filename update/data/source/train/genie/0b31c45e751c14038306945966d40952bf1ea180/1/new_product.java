private void validate(
            final ApplicationStatus status, final String error) throws GeniePreconditionException {
        final StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(error)) {
            builder.append(error);
        }
        if (status == null) {
            builder.append("No application status entered and is required.\n");
        }

        if (builder.length() > 0) {
            builder.insert(0, "Application configuration errors:\n");
            final String msg = builder.toString();
            LOG.error(msg);
            throw new GeniePreconditionException(msg);
        }
    }