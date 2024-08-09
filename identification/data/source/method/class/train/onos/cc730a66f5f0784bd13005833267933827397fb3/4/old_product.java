@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{component}")
    public Response setConfigs(@PathParam("component") String component,
                               @DefaultValue("false") @QueryParam("preset") boolean preset,
                               InputStream request) throws IOException {
        ComponentConfigService service = get(ComponentConfigService.class);
        ObjectNode props = (ObjectNode) mapper().readTree(request);
        List<String> errorMsgs = new ArrayList<String>();
        if (preset) {
                props.fieldNames().forEachRemaining(k -> {
                    try {
                        service.preSetProperty(component, k, props.path(k).asText());
                    } catch (IllegalArgumentException e) {
                        errorMsgs.add(e.getMessage());
                    }
                });
        } else {
                props.fieldNames().forEachRemaining(k -> {
                    try {
                        service.setProperty(component, k, props.path(k).asText());
                    } catch (IllegalArgumentException e) {
                        errorMsgs.add(e.getMessage());
                    }
                });
        }
        if (!errorMsgs.isEmpty()) {
            return Response.status(MULTI_STATUS_RESPONE).entity(produceErrorJson(errorMsgs)).build();
        }
        return Response.ok().build();
    }