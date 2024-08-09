@POST
        @Path("/scopes/{scope}/streams")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public void createStream(@PathParam("scope") final String scope, final CreateStreamRequest createStreamRequest,
                                 @Suspended final AsyncResponse asyncResponse);