protected String resultToPlainText(final String title, final List<HealthCheckExecutionResult> results) {
        final StringBuilder sb = new StringBuilder();

        sb.append(title);
        sb.append(System.lineSeparator());

        if (results.size() == 0) {
            sb.append("No " + StringUtils.lowerCase(title) + " could be found!");
            sb.append(System.lineSeparator());
        } else {
            sb.append(StringUtils.repeat("-", NUM_DASHES));
            sb.append(System.lineSeparator());

            for (final HealthCheckExecutionResult result : results) {
                sb.append(StringUtils.rightPad("[ " + result.getHealthCheckResult().getStatus().name() + " ]", HEALTH_CHECK_STATUS_PADDING));
                sb.append("  ");
                sb.append(result.getHealthCheckMetadata().getTitle());
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }