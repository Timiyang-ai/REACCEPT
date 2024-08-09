@POST
    @Path("applications/{applicationName}/experiments/{experimentLabel}/users")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Timed
    public Response recordUsersEvents(
            @PathParam("applicationName")
            final Application.Name applicationName,

            @PathParam("experimentLabel")
            final Experiment.Label experimentLabel,

            final Map<User.ID, List<Event>> eventList) {
        LOGGER.warn("recordUsersEvents is unsupported");
        throw new UnsupportedOperationException("Not implemented");
    }