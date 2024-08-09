@GET
    @Path("/{applicationName}/experiments")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns metadata for all experiments within an application",
            response = ExperimentList.class)
    @Timed
    public Response getExperiments(@PathParam("applicationName")
                                   @ApiParam(value = "Application Name")
                                   final Application.Name applicationName,

                                   @HeaderParam(AUTHORIZATION)
                                   @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
                                   final String authorizationHeader) {
        List<Experiment> experimentList = authorizedExperimentGetter.getAuthorizedExperimentsByName(authorizationHeader,
                applicationName);

        return httpHeader.headers().entity(experimentList).build();
    }