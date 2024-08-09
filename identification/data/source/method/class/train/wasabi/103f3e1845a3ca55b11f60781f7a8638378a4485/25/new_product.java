@POST
    @Path("applications/{applicationName}/experiments")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Timed
    public Response recordExperimentsEvents(
            @PathParam("applicationName") final Application.Name applicationName,
            final Map<Experiment.Label, Map<User.ID, List<Event>>> eventList) {
        LOGGER.warn("recordExperimentsEvents is unsupported");
        throw new UnsupportedOperationException("Not implemented");
    }