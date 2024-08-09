@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{deviceId}/request")
    public Response requestRoleFor(@PathParam("deviceId") String deviceId) {
        MastershipRole role = nullIsNotFound(mastershipService.requestRoleForSync(
                                DeviceId.deviceId(deviceId)), MASTERSHIP_ROLE_NOT_FOUND);
        ObjectNode root = codec(MastershipRole.class).encode(role, this);
        return ok(root).build();
    }