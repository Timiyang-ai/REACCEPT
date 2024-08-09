@DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{component}")
    public Response unsetConfigs(@PathParam("component") String component,
                                 InputStream request) throws IOException {
        ComponentConfigService service = get(ComponentConfigService.class);
        ObjectNode props = (ObjectNode) mapper().readTree(request);
        props.fieldNames().forEachRemaining(k -> service.unsetProperty(component, k));
        return Response.noContent().build();
    }