public static ApplicationStatus parse(final String value) throws GeniePreconditionException {
        if (StringUtils.isNotBlank(value)) {
            for (final ApplicationStatus status : ApplicationStatus.values()) {
                if (value.equalsIgnoreCase(status.toString())) {
                    return status;
                }
            }
        }
        throw new GeniePreconditionException(
                "Unacceptable application status. Must be one of {ACTIVE, DEPRECATED, INACTIVE}");
    }