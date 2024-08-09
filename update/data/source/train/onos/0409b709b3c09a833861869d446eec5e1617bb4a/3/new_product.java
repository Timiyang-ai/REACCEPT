@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{deviceId}/master")
    public Response getMasterFor(@PathParam("deviceId") String deviceId) {
        NodeId id = nullIsNotFound(mastershipService.getMasterFor(
                    DeviceId.deviceId(deviceId)), NODE_ID_NOT_FOUND);

        ObjectNode root = mapper().createObjectNode();
        root.put(NODE_ID, id.id());
        return ok(root).build();
    }