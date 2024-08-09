@GET
    @Path("/{applicationName}/experiments")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Returns metadata for all experiments within an application",
            response = ExperimentList.class)
    @Timed
    public Response getExperiments(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = false)
            final String authorizationHeader) {
        try {
            return httpHeader.headers().entity(authorizedExperimentGetter
                    .getExperimentsByName(true, authorizationHeader, applicationName)).build();
        } catch (Exception exception) {
            LOGGER.error("getExperiments failed for applicationName={} with error:", applicationName, exception);
            throw exception;
        }
    }