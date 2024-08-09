@POST
   @Path("/catalogItems")
   @Consumes(VCloudDirectorMediaType.CATALOG_ITEM)
   @Produces(VCloudDirectorMediaType.CATALOG_ITEM)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<CatalogItem> addCatalogItem(@EndpointParam(parser = ReferenceToEndpoint.class) ReferenceType<?> catalogRef,
         @BinderParam(BindToXMLPayload.class) CatalogItem catalogItem);