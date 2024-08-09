@PUT
    @Path("{path_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePath(@PathParam("path_id") String id,
            final InputStream stream) {
        log.debug("Update path by identifier {}.", id);
        try {
            ObjectNode jsonTree = (ObjectNode) mapper().readTree(stream);
            JsonNode pathNode = jsonTree.get("path");
            PcePath path = codec(PcePath.class).decode((ObjectNode) pathNode, this);
            if (path == null) {
                return Response.status(OK).entity(PCE_SETUP_PATH_FAILED).build();
            }
            List<Constraint> constrntList = new LinkedList<Constraint>();
            // Assign bandwidth
            if (path.bandwidthConstraint() != null) {
                constrntList.add(path.bandwidthConstraint());
            }

            // Assign cost
            if (path.costConstraint() != null) {
                constrntList.add(path.costConstraint());
            }

            Boolean result = nullIsNotFound(get(PceService.class).updatePath(TunnelId.valueOf(id), constrntList),
                                            PCE_PATH_NOT_FOUND);
            return Response.status(OK).entity(result.toString()).build();
        } catch (IOException e) {
            log.error("Update path failed because of exception {}.", e.toString());
            throw new IllegalArgumentException(e);
        }
    }