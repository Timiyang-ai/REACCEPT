@GET
    @Path("{applicationName}/priorities")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Get the priority list for an application",
            notes = "The returned priority list is rank ordered.")
    //            response = ??, //todo: update with proper object
    @Timed
    public Response getPriorities(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            authorization.checkUserPermissions(authorization.getUser(authorizationHeader), applicationName, READ);

            PrioritizedExperimentList prioritizedExperiments =
                    priorities.getPriorities(applicationName, true);

            return httpHeader.headers().entity(prioritizedExperiments).build();
        } catch (Exception e) {
            LOGGER.error("getPriorities failed for applicationName={} with error:", applicationName, e);
            throw e;
        }
    }