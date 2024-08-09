@RequestMapping(value="profileSchemas/{applicationId}", method=RequestMethod.GET)
    @ResponseBody
    public List<EndpointProfileSchemaDto> getProfileSchemasByApplicationId(@PathVariable String applicationId) throws KaaAdminServiceException {
        return kaaAdminService.getProfileSchemasByApplicationId(applicationId);
    }