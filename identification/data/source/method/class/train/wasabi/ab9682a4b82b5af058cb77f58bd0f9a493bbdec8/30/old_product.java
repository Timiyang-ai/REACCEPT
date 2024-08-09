@DELETE
    @Path("{experimentID}/pages/{pageName}")
    @ApiOperation(value = "Remove a page from an experiment",
            notes = "Pages can only be added to an experiment with DRAFT, RUNNING, or PAUSED states")
    @Timed
    public Response deletePage(@PathParam("experimentID")
                               @ApiParam(value = "Experiment ID")
                               final Experiment.ID experimentID,

                               @PathParam("pageName")
                               @ApiParam(value = "Page name where the experiment will appear")
                               final Page.Name pageName,

                               @HeaderParam(AUTHORIZATION)
                               @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                               final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID);

        // Throw an exception if the current experiment is not valid
        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }

        authorization.checkUserPermissions(userName, experiment.getApplicationName(), DELETE);
        pages.deletePage(experimentID, pageName, authorization.getUserInfo(userName));

        return httpHeader.headers(NO_CONTENT).build();
    }