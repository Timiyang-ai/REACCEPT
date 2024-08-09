@GET
    @Path("queueLength")
    @Produces(APPLICATION_JSON)
    @Timed
    public Response getEventsQueueLength() {
        try {
            return httpHeader.headers().entity(events.queuesLength()).build();
        } catch (Exception exception) {
            LOGGER.error("getEventsQueueLength failed with error:", exception);
            throw exception;
        }
    }