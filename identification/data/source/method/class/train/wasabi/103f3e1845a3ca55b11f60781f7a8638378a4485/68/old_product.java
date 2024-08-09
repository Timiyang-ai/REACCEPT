@GET
    @Path("queueLength")
    @Produces(APPLICATION_JSON)
    @Timed
    public Response getEventsQueueLength() {
        return httpHeader.headers().entity(events.queuesLength()).build();
    }