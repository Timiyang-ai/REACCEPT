@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("ids/entry")
    public Response getAppIdByName(@QueryParam("id") Short id,
                                   @QueryParam("name") String name) {
        CoreService service = get(CoreService.class);
        ApplicationId appId = null;
        if (id != null) {
            appId = service.getAppId(id);
        } else if (name != null) {
            appId = service.getAppId(name);
        }
        return response(appId);
    }