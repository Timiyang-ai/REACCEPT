@PUT
    @Path("{experimentID}/buckets/{bucketLabel}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update a bucket in an experiment",
            notes = "Can only update buckets for an experiment that is in DRAFT state.",
            response = Bucket.class)
    @Timed
    public Response putBucket(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @PathParam("bucketLabel")
            @ApiParam(value = "Bucket Label")
            final Bucket.Label bucketLabel,

            @ApiParam(required = true, defaultValue = DEFAULT_PUTBUCK)
            final Bucket bucketEntity,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            Experiment experiment = experiments.getExperiment(experimentID);

            // Throw an exception if the current experiment is not valid
            if (experiment == null) {
                throw new ExperimentNotFoundException(experimentID);
            }

            authorization.checkUserPermissions(userName, experiment.getApplicationName(), UPDATE);

            UserInfo user = authorization.getUserInfo(userName);
            Bucket bucket = buckets.updateBucket(experimentID, bucketLabel, bucketEntity, user);

            assert bucket != null : "Error updating bucket";

            return httpHeader.headers().entity(bucket).build();
        } catch (Exception exception) {
            LOGGER.error("putBucket failed for experimentID={}, bucketLabel={}, bucketEntity={} with error:",
                    experimentID, bucketLabel, bucketEntity,
                    exception);
            throw exception;
        }
    }