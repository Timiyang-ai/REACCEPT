@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("application/{appId}")
    public Response getFlowByAppId(@PathParam("appId") String appId) {
        final ApplicationService appService = get(ApplicationService.class);
        final ApplicationId idInstant = nullIsNotFound(appService.getId(appId), APP_ID_NOT_FOUND);
        final Iterable<FlowEntry> flowEntries = service.getFlowEntriesById(idInstant);

        flowEntries.forEach(flow -> flowsNode.add(codec(FlowEntry.class).encode(flow, this)));
        return ok(root).build();
    }