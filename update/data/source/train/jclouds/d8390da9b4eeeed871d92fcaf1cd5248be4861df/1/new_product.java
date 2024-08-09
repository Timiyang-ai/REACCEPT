@GET
   @Path("/metadata/{key}")
   @Consumes
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<MetadataValue> getCatalogMetadataValue(@EndpointParam(parser = ReferenceToEndpoint.class) ReferenceType<?> catalogRef,
         @PathParam("key") String key);