@DELETE
    @Path("{experimentID}/buckets/{bucketLabel}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Delete a bucket in an experiment",
            notes = "Can only delete a bucket for experiment that is in DRAFT state.  The default call is " +
                    "safe to use, but other than that please only delete buckets which you have created.")
    @Timed
    public Response deleteBucket(@PathParam("experimentID")
                                 @ApiParam(value = "Experiment ID")
                                 final Experiment.ID experimentID,

                                 @PathParam("bucketLabel")
                                 @ApiParam(value = "Bucket Label")
                                 final Bucket.Label bucketLabel,

                                 @HeaderParam(AUTHORIZATION)
                                 @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                 final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID);

        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }

        authorization.checkUserPermissions(userName, experiment.getApplicationName(), Permission.DELETE);

        UserInfo user = authorization.getUserInfo(userName);

        buckets.deleteBucket(experimentID, bucketLabel, user);

        return httpHeader.headers(NO_CONTENT).build();
    }