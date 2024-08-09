@RequestMapping(value = "CTL/saveSchema", params = { "body" }, method = RequestMethod.POST)
    @ResponseBody
    public CTLSchemaInfoDto saveCTLSchema(@RequestParam String body, 
            @RequestParam(required = false) String scope,  
            @RequestParam(required = false) String applicationId) throws KaaAdminServiceException {
        return kaaAdminService.saveCTLSchema(body, isEmpty(scope) ? null : CTLSchemaScopeDto.valueOf(scope.toUpperCase()), applicationId);
    }