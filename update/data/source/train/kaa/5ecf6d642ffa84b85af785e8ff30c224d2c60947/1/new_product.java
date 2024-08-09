@RequestMapping(value = "CTL/deleteSchema", params = { "fqn", "version" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCTLSchemaByFqnVersionTenantIdAndApplicationId(@RequestParam String fqn, @RequestParam int version, 
            @RequestParam(required = false) String tenantId,
            @RequestParam(required = false) String applicationId) throws KaaAdminServiceException {
        kaaAdminService.deleteCTLSchemaByFqnVersionTenantIdAndApplicationId(fqn, version, tenantId, applicationId);
    }