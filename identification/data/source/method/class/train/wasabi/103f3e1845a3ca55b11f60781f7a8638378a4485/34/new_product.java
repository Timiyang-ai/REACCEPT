@PUT
    @Path("/{applicationName}/priorities")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Create global priority list for an application",
            notes = "Experiments can only be placed in a priority list in DRAFT, RUNNING, and PAUSED states.")
    @Timed
    public Response createPriorities(
            @PathParam("applicationName")
            @ApiParam(value = "Application Name")
            final Application.Name applicationName,

            @ApiParam(required = true, defaultValue = DEFAULT_MODEXP)
            final ExperimentIDList experimentIDList,

            @HeaderParam(AUTHORIZATION)
            @ApiParam(value = EXAMPLE_AUTHORIZATION_HEADER, required = true)
            final String authorizationHeader) {
        try {
            authorization.checkUserPermissions(authorization.getUser(authorizationHeader), applicationName, UPDATE);
            priorities.createPriorities(applicationName, experimentIDList, true);

            return httpHeader.headers(NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("Create priorities failed for application={} and experimentIdList={} with error:",
                    applicationName,
                    experimentIDList,
                    e);
            throw e;
        }
    }