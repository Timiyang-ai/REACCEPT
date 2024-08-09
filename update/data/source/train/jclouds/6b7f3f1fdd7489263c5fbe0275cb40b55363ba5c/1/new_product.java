@POST
   @Path("/catalogItems")
   @Consumes(VCloudDirectorMediaType.CATALOG_ITEM)
   @Produces(VCloudDirectorMediaType.CATALOG_ITEM)
   @JAXBResponseParser
   @ExceptionParser(ThrowVCloudErrorOn4xx.class)
   ListenableFuture<CatalogItem> addCatalogItem(@EndpointParam(parser = URISupplierToEndpoint.class) URISupplier catalogRef,
         @BinderParam(BindToXMLPayload.class) CatalogItem catalogItem);