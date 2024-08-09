@RequestMapping(value="profileSchema/{profileSchemaId}", method=RequestMethod.GET)
    @ResponseBody
    public ProfileSchemaDto getProfileSchema(@PathVariable String profileSchemaId) throws KaaAdminServiceException {
        return kaaAdminService.getProfileSchema(profileSchemaId);
    }