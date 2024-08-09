@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{component}")
    public Response setConfigs(@PathParam("component") String component,
                               InputStream request) throws IOException {
        ComponentConfigService service = get(ComponentConfigService.class);
        ObjectNode props = (ObjectNode) mapper().readTree(request);
        props.fieldNames().forEachRemaining(k -> service.setProperty(component, k,
                                                                     props.path(k).asText()));
        return Response.noContent().build();
    }