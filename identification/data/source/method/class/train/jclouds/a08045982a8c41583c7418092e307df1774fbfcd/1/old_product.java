@POST
   @Path("/server/clone/format/json")
   @SelectJson("server")
   @Consumes(MediaType.APPLICATION_JSON)
   ListenableFuture<ServerCreated> cloneServer(@FormParam("serverid") String serverid,
                                               @FormParam("hostname") String hostname,
                                               ServerCloneOptions... options);