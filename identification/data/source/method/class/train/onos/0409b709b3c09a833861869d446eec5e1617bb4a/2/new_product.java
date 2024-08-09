@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setRole(InputStream stream) {

        try {
            ObjectNode jsonTree = (ObjectNode) mapper().readTree(stream);
            JsonNode deviceIdJson = jsonTree.get(DEVICE_ID);
            JsonNode nodeIdJson = jsonTree.get(NODE_ID);
            MastershipRole role = codec(MastershipRole.class).decode(jsonTree, this);

            if (deviceIdJson == null) {
                throw new IllegalArgumentException(DEVICE_ID_INVALID);
            }

            if (nodeIdJson == null) {
                throw new IllegalArgumentException(NODE_ID_INVALID);
            }

            mastershipAdminService.setRoleSync(NodeId.nodeId(nodeIdJson.asText()),
                    DeviceId.deviceId(deviceIdJson.asText()), role);

            return Response.ok().build();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }