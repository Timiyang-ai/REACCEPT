@GET
    @ApiOperation(
            value= "Display information regarding to <b>Features</b>",
            notes= "other sub resources to be displayed",
            response=FeatureStoreApiBean.class)
    @ApiResponses(@ApiResponse(code = 200, message= "status of current ff4j bean"))
    @Produces(MediaType.APPLICATION_JSON)
    public FeatureStoreApiBean get() {
        return new FeatureStoreApiBean(ff4j.getStore());
    }