protected List<ClusterCriteria> stringToClusterCriterias(final String criteriaString) throws GenieException {
        //Rebuild the cluster criteria objects
        final String[] criteriaSets = StringUtils.split(criteriaString, CRITERIA_SET_DELIMITER);
        if (criteriaSets == null || criteriaSets.length == 0) {
            throw new GenieException(
                    HttpURLConnection.HTTP_PRECON_FAILED,
                    "No cluster criteria found. Unable to continue.");
        }
        final List<ClusterCriteria> cc = new ArrayList<>();
        for (final String criteriaSet : criteriaSets) {
            final String[] criterias = StringUtils.split(criteriaSet, CRITERIA_DELIMITER);
            if (criterias == null || criterias.length == 0) {
                continue;
            }
            final Set<String> c = new HashSet<>();
            c.addAll(Arrays.asList(criterias));
            cc.add(new ClusterCriteria(c));
        }
        if (cc.isEmpty()) {
            throw new GenieException(
                    HttpURLConnection.HTTP_PRECON_FAILED,
                    "No Cluster Criteria found. Unable to continue");
        }
        return cc;
    }