@PUT
    @Path("{experimentID}/buckets/{bucketLabel}/state/{desiredState}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update a bucket state",
            notes = "Can only close a bucket which is not in DRAFT state",
            response = Bucket.class)
    @Timed
    public Response putBucketState(@PathParam("experimentID")
                                   @ApiParam(value = "Experiment ID")
                                   final Experiment.ID experimentID,

                                   @PathParam("bucketLabel")
                                   @ApiParam(value = "Bucket Label")
                                   final Bucket.Label bucketLabel,

                                   @PathParam("desiredState")
                                   @ApiParam(value = "Desired Bucket State")
                                   final Bucket.State desiredState,

                                   @HeaderParam(AUTHORIZATION)
                                   @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                   final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID);

        // Throw an exception if the current experiment is not valid
        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }

        authorization.checkUserPermissions(userName, experiment.getApplicationName(), UPDATE);

        UserInfo user = authorization.getUserInfo(userName);
        Bucket bucket = buckets.updateBucketState(experimentID, bucketLabel, desiredState, user);

        assert bucket != null : "Error updating bucket state";

        return httpHeader.headers().entity(bucket).build();
    }