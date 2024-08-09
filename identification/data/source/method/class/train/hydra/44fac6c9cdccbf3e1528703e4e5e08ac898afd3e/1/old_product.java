@POST
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED, MediaType.WILDCARD})
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveJob(@QueryParam("pairs") KVPairs kv,
                            @QueryParam("user") String user,
                            @QueryParam("token") String token,
                            @QueryParam("sudo") String sudo,
                            @DefaultValue("true") @QueryParam("defaults") boolean defaults) {
        String id = KVUtils.getValue(kv, "", "id", "job");
        try {
            Job job = requestHandler.createOrUpdateJob(kv, user, token, sudo, defaults);
            log.info("[job/save][user={}][id={}] Job {}", user, job.getId(), jobUpdateAction(id));
            return Response.ok("{\"id\":\"" + job.getId() + "\",\"updated\":\"true\"}").build();
        } catch (IllegalArgumentException e) {
            log.warn("[job/save][user={}][id={}] Bad parameter: {}", user, id, e.getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (InsufficientPrivilegesException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        } catch (Exception e) {
            log.error("[job/save][user={}][id={}] Internal error: {}", user, id, e.getMessage(), e);
            return buildServerError(e);
        }
    }