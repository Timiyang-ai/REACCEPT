@GET
    @Path("{experimentID}/buckets/{bucketLabel}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return a single bucket for an experiment",
            response = Bucket.class)
    @Timed
    public Response getBucket(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @PathParam("bucketLabel")
            @ApiParam(value = "Bucket Label")
            final Bucket.Label bucketLabel,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            //TODO: Duplicated code: move to a separate method
            if (authorizationHeader != null) {
                Username userName = authorization.getUser(authorizationHeader);
                Experiment experiment = experiments.getExperiment(experimentID);

                if (experiment == null) {
                    throw new ExperimentNotFoundException(experimentID);
                }

                authorization.checkUserPermissions(userName, experiment.getApplicationName(), READ);
            }

            Bucket bucket = buckets.getBucket(experimentID, bucketLabel);

            if (bucket == null) {
                throw new BucketNotFoundException(bucketLabel);
            }

            return httpHeader.headers().entity(bucket).build();
        } catch (Exception exception) {
            LOGGER.error("getBucket failed for experimentID={}, bucketLabel={} with error:",
                    experimentID, bucketLabel, exception);
            throw exception;
        }
    }