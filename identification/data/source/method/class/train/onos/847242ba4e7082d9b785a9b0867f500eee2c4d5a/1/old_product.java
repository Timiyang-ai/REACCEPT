@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("ids/name")
    public Response getAppIdByName(@QueryParam("name") String name) {
        CoreService service = get(CoreService.class);
        ApplicationId appId = service.getAppId(name);
        return response(appId);
    }