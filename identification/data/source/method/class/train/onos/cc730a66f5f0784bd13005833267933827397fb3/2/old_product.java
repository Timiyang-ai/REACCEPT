@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{component}")
    public Response setConfigs(@PathParam("component") String component,
                               @DefaultValue("false") @QueryParam("preset") boolean preset,
                               InputStream request) throws IOException {
        ComponentConfigService service = get(ComponentConfigService.class);
        ObjectNode props = (ObjectNode) mapper().readTree(request);
        if (preset) {
            props.fieldNames().forEachRemaining(k ->
                    service.preSetProperty(component, k, props.path(k).asText()));
        } else {
            props.fieldNames().forEachRemaining(k ->
                    service.setProperty(component, k, props.path(k).asText()));
        }
        return Response.ok().build();
    }