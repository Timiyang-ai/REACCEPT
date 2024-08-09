@POST
    @Path("applications/{applicationName}/experiments")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Timed
    public Response recordExperimentsEvents(
            @PathParam("applicationName") final Application.Name applicationName,
            @PathParam("userID") final User.ID userID,
            final Map<Experiment.Label, Map<User.ID, List<Event>>> eventList) {
        throw new UnsupportedOperationException("Not implemented");
    }