@RequestMapping(value = "eventClassFamilies/{applicationToken}", method = RequestMethod.GET)
    @ResponseBody
    public List<AefMapInfoDto> getEventClassFamiliesByApplicationToken(@PathVariable String applicationToken) throws KaaAdminServiceException {
        return kaaAdminService.getEventClassFamiliesByApplicationToken(applicationToken);
    }