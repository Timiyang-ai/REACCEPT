@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{deviceId}/request")
    public Response requestRoleFor(@PathParam("deviceId") String deviceId) {
        DeviceId id = DeviceId.deviceId(deviceId);
        nullIsNotFound(deviceService.getDevice(id), DEVICE_ID_NOT_FOUND);

        MastershipRole role = nullIsNotFound(mastershipService.requestRoleForSync(id),
                        MASTERSHIP_ROLE_NOT_FOUND);
        ObjectNode root = codec(MastershipRole.class).encode(role, this);
        return ok(root).build();
    }