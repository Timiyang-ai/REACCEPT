@GET
   @Path("/users/{username}")
   @ExceptionParser(ReturnNullOnNotFoundOr404.class)
   @Consumes(MediaType.APPLICATION_JSON)
   ListenableFuture<User> getUser(@PathParam("username") String username);