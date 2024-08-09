protected Set<String> stringToCommandCriteria(final String criteriaString) throws GenieException {
        final String[] criterias = StringUtils.split(criteriaString, CRITERIA_DELIMITER);
        if (criterias == null || criterias.length == 0) {
            throw new GenieException(
                    HttpURLConnection.HTTP_PRECON_FAILED,
                    "No command criteria found. Unable to continue.");
        }
        final Set<String> c = new HashSet<>();
        c.addAll(Arrays.asList(criterias));
        return c;
    }