@RequestMapping(value = "CTL/saveSchema", params = { "body", "scope", "applicationId" }, method = RequestMethod.POST)
    @ResponseBody
    public CTLSchemaInfoDto saveCTLSchema(@RequestParam String body, 
            @RequestParam(required = false) CTLSchemaScopeDto scope,  
            @RequestParam(required = false) String applicationId) throws KaaAdminServiceException {
        return kaaAdminService.saveCTLSchema(body, scope, applicationId);
    }