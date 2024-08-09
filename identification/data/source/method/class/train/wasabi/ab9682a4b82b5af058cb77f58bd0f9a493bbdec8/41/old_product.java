@GET
    @Path("{experimentID}/buckets")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Return all buckets for an experiment",
            response = BucketList.class)
    @Timed
    public Response getBuckets(@PathParam("experimentID")
                               @ApiParam(value = "Experiment ID")
                               final Experiment.ID experimentID,

                               @HeaderParam(AUTHORIZATION)
                               @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                               final String authorizationHeader) {
        if (authorizationHeader != null) {
            Username userName = authorization.getUser(authorizationHeader);

            authorization.checkUserPermissions(userName, experiments.getExperiment(experimentID).getApplicationName(),
                    READ);
        }

        return httpHeader.headers().entity(buckets.getBuckets(experimentID)).build();
    }