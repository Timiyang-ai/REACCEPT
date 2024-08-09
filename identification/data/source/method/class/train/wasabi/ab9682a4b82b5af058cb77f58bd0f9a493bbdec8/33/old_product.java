@PUT
    @Path("{experimentID}/buckets")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Modify buckets for an experiment",
            notes = "Can only modify buckets for an experiment that is in DRAFT state.",
            response = Bucket.class)
    @Timed
    public Response putBucket(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            @ApiParam(required = true, defaultValue = DEFAULT_MODBUCK)
            final BucketList bucketList,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            Username userName = authorization.getUser(authorizationHeader);
            Experiment experiment = experiments.getExperiment(experimentID);

            if (experiment == null) {
                throw new ExperimentNotFoundException(experimentID);
            }

            authorization.checkUserPermissions(userName, experiment.getApplicationName(), UPDATE);

            LOGGER.warn("Bucket edited: user " + userName.toString()
                    + " is batch editing buckets for experiment " + experimentID.toString());

            UserInfo user = authorization.getUserInfo(userName);
            BucketList bucketList1 = buckets.updateBucketBatch(experimentID, bucketList, user);

            return httpHeader.headers().entity(bucketList1).build();
        } catch (Exception exception) {
            LOGGER.error("putBucket failed for experimentID={}, bucketList={} with error:",
                    experimentID, bucketList, exception);
            throw exception;
        }
    }