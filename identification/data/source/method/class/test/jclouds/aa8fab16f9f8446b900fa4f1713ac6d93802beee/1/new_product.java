@Named("deleteSecurityGroup")
   @GET
   @QueryParams(keys = "command", values = "deleteSecurityGroup")
   @Consumes(MediaType.APPLICATION_JSON)
   @Fallback(VoidOnNotFoundOr404.class)
   void deleteSecurityGroup(@QueryParam("id") String id);