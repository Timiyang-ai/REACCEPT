public static ApplicationStatus parse(final String value) throws GenieException {
        if (StringUtils.isNotBlank(value)) {
            for (final ApplicationStatus status : ApplicationStatus.values()) {
                if (value.equalsIgnoreCase(status.toString())) {
                    return status;
                }
            }
        }
        throw new GenieException(HttpURLConnection.HTTP_NOT_ACCEPTABLE,
                "Unacceptable application status. Must be one of {ACTIVE, DEPRECATED, INACTIVE}");
    }