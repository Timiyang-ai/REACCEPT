@POST
    @Path("{experimentID}/pages")
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Post a list of pages to an experiment",
            notes = "Pages can only be added to an experiment with DRAFT, RUNNING, or PAUSED states")
    @Timed
    public Response postPages(
            @PathParam("experimentID")
            @ApiParam(value = "Experiment ID")
            final Experiment.ID experimentID,

            final ExperimentPageList experimentPageList,

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

            authorization.checkUserPermissions(userName, experiment.getApplicationName(), CREATE);
            pages.postPages(experimentID, experimentPageList, authorization.getUserInfo(userName));

            return httpHeader.headers(CREATED).build();
        } catch (Exception exception) {
            LOGGER.error("postPages failed for experimentID={}, experimentPageList={} with error:",
                    experimentID, experimentPageList, exception);
            throw exception;
        }
    }