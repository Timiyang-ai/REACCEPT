public Cluster createCluster(final Cluster cluster)
            throws GenieException {
        if (cluster == null) {
            throw new GeniePreconditionException(
                    "No cluster entered. Unable to validate.");
        }
        cluster.validate();
        final HttpRequest request = BaseGenieClient.buildRequest(
                Verb.POST,
                BASE_CONFIG_CLUSTER_REST_URL,
                null,
                cluster);
        return (Cluster) this.executeRequest(request, null, Cluster.class);
    }