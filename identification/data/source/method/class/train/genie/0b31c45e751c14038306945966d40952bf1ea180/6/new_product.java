protected Set<String> stringToCommandCriteria(final String criteriaString) throws GeniePreconditionException {
        final String[] criterias = StringUtils.split(criteriaString, CRITERIA_DELIMITER);
        if (criterias == null || criterias.length == 0) {
            throw new GeniePreconditionException("No command criteria found. Unable to continue.");
        }
        final Set<String> c = new HashSet<>();
        c.addAll(Arrays.asList(criterias));
        return c;
    }