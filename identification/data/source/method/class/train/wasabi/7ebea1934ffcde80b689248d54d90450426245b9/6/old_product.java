@DELETE
    @Path("{experimentID}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Delete an experiment",
            notes = "Can only delete an experiment that is in DRAFT or TERMINATED state.  The default call is " +
                    "safe to use, but other than that please only delete experiments which you have creation_time.")
    @Timed
    public Response deleteExperiment(@PathParam("experimentID")
                                     @ApiParam(value = "Experiment ID")
                                     final Experiment.ID experimentID,

                                     @HeaderParam(AUTHORIZATION)
                                     @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                     final String authorizationHeader) {
        Username userName = authorization.getUser(authorizationHeader);
        Experiment experiment = experiments.getExperiment(experimentID);

        if (experiment == null) {
            throw new ExperimentNotFoundException(experimentID);
        }

        authorization.checkUserPermissions(userName, experiment.getApplicationName(), DELETE);

        // Note: deleting an experiment follows the same rules as
        // updating its state to "deleted" -- so reuse the code.
        Experiment updatedExperiment = from(experiment).withState(DELETED).build();

        experiment = experiments.updateExperiment(experimentID, updatedExperiment, authorization.getUserInfo(userName));

        assert experiment != null : "Error deleting experiment";

        return httpHeader.headers(NO_CONTENT).build();
    }