@POST
    @Path("{experimentID}/priority/{priorityPosition}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Insert an experiment into an application's priority list",
            notes = "Experiments can only be placed in a priority list DRAFT, RUNNING, and PAUSED states within" +
                    "the same application")
    @Timed
    public Response setPriority(@PathParam("experimentID")
                                @ApiParam(value = "Experiment ID")
                                final Experiment.ID experimentID,

                                @PathParam("priorityPosition")
                                final int priorityNum,

                                @HeaderParam(AUTHORIZATION)
                                @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID);

        // Throw an exception if the current experiment is not valid
        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }

        authorization.checkUserPermissions(userName, experiment.getApplicationName(), CREATE);
        priorities.setPriority(experimentID, priorityNum);

        return httpHeader.headers(CREATED).build();
    }