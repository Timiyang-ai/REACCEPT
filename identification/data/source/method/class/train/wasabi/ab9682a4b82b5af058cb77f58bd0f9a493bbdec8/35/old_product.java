@PUT
    @Path("{experimentID}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Update an experiment",
            notes = "Can update an experiment that is in DRAFT state to change the experiment state, or " +
                    "to change sampling percentage or to enable personalization and more.",
            response = Experiment.class)
    @Timed
    public Response putExperiment(@PathParam("experimentID")
                                  @ApiParam(value = "Experiment ID")
                                  final Experiment.ID experimentID,

                                  @ApiParam(value = "Please read the implementation notes above", required = true)
                                  final Experiment experimentEntity,

                                  @QueryParam("createNewApplication")
                                  @DefaultValue("false")
                                  final boolean createNewApplication,

                                  @HeaderParam(AUTHORIZATION)
                                  @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                  final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID);

        // Throw an exception if the current experiment is not valid
        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }

        assert isNotEmpty(experiment.getDescription()) : "No description/hypothesis found";

        if (!createNewApplication) {
            authorization.checkUserPermissions(userName, experiment.getApplicationName(), UPDATE);
        }

        experiment = experiments.updateExperiment(experimentID, experimentEntity, authorization.getUserInfo(userName));
        assert experiment != null : "Error updating experiment";

        if ((createNewApplication) && !experiment.getState().equals(DELETED)) {
            UserRole userRole = newInstance(experiment.getApplicationName(), ADMIN).withUserID(userName).build();

            authorization.setUserRole(userRole, null);
        }

        return experiment.getState().equals(DELETED) ?
                httpHeader.headers(NO_CONTENT).build() : httpHeader.headers().entity(experiment).build();
    }