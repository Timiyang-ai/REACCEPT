@DELETE
    @Path("/exclusions/experiment1/{experimentID_1}/experiment2/{experimentID_2}")
    @ApiOperation(value = "Delete a mutual exclusion relation",
            notes = "Can only delete mutual exclusion relations that currently " +
                    "exists.  This operation is symmetric")
//            response = ??, //todo: update with proper object in @ApiOperation
    @Timed
    public Response removeExclusions(@PathParam("experimentID_1")
                                     @ApiParam(value = "Experiment ID 1")
                                     final Experiment.ID experimentID_1,

                                     @PathParam("experimentID_2")
                                     @ApiParam(value = "Experiment ID 2")
                                     final Experiment.ID experimentID_2,

                                     @HeaderParam(AUTHORIZATION)
                                     @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                     final String authorizationHeader) {
        //todo: do we want to check that exp1 and exp2 are in the same app?
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID_1);

        // Throw an exception if the current experiment is not valid
        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID_1);
        }

        authorization.checkUserPermissions(userName, experiment.getApplicationName(), Permission.DELETE);
        //this is the user that triggered the event and will be used for logging
        mutex.deleteExclusion(experimentID_1, experimentID_2, authorization.getUserInfo(userName));

        return httpHeader.headers(NO_CONTENT).build();
    }