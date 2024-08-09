@RequestMapping(value = "applicationEventMaps/{applicationToken}", method = RequestMethod.GET)
    @ResponseBody
    public List<ApplicationEventFamilyMapDto> getApplicationEventFamilyMapsByApplicationToken(@PathVariable String applicationToken)
            throws KaaAdminServiceException {
        return kaaAdminService.getApplicationEventFamilyMapsByApplicationToken(applicationToken);
    }