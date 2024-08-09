@RequestMapping(value="profileSchema/{profileSchemaId}", method=RequestMethod.GET)
    @ResponseBody
    public EndpointProfileSchemaDto getProfileSchema(@PathVariable String profileSchemaId) throws KaaAdminServiceException {
        return kaaAdminService.getProfileSchema(profileSchemaId);
    }