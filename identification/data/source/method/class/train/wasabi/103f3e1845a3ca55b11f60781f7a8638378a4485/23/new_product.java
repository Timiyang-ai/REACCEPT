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
                    .getExperimentsByName(false, authorizationHeader, applicationName)).build();
        } catch (Exception e) {
            LOGGER.error("Get experiments failed for application={} with error:", applicationName, e);
            throw e;
        }
    }