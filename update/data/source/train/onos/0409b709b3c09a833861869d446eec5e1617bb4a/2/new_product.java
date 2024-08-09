@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{deviceId}/relinquish")
    public Response relinquishMastership(@PathParam("deviceId") String deviceId) {
        DeviceId id = DeviceId.deviceId(deviceId);
        mastershipService.relinquishMastershipSync(id);
        return Response.created(id.uri()).build();
    }