@RequestMapping(value = "CTL/saveSchema", params = { "body" }, method = RequestMethod.POST)
    @ResponseBody
    public CTLSchemaDto saveCTLSchema(@RequestParam String body, @RequestParam(required = false) String tenantId,
            @RequestParam(required = false) String applicationId) throws KaaAdminServiceException {
        return kaaAdminService.saveCTLSchema(body, tenantId, applicationId);
    }