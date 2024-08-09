@GET
    @Path("{experimentID}/pages")
    @ApiOperation(value = "Get the associated pages information for a given experiment ID")
    @Timed
    public Response getExperimentPages(@PathParam("experimentID")
                                       @ApiParam(value = "Experiment ID")
                                       final Experiment.ID experimentID,

                                       @HeaderParam(AUTHORIZATION)
                                       @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                       final String authorizationHeader) {
        if (authorizationHeader != null) {
            Username userName = authorization.getUser(authorizationHeader);
            Experiment experiment = experiments.getExperiment(experimentID);

            if (experiment == null) {
                throw new ExperimentNotFoundException(experimentID);
            }

            authorization.checkUserPermissions(userName, experiment.getApplicationName(), READ);
        }

        ExperimentPageList experimentPages = pages.getExperimentPages(experimentID);

        return httpHeader.headers().entity(experimentPages).build();
    }