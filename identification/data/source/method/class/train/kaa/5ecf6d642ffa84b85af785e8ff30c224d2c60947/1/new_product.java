@RequestMapping(value = "CTL/getSchema", params = { "fqn", "version" }, method = RequestMethod.GET)
    @ResponseBody
    public CTLSchemaDto getCTLSchemaByFqnVersionTenantIdAndApplicationId(@RequestParam String fqn, 
            @RequestParam int version,
            @RequestParam(required = false) String tenantId,
            @RequestParam(required = false) String applicationId)
            throws KaaAdminServiceException {
        return kaaAdminService.getCTLSchemaByFqnVersionTenantIdAndApplicationId(fqn, version, tenantId, applicationId);
    }