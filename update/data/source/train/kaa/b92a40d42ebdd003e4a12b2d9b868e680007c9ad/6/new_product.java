@RequestMapping(value = "vacantEventClassFamilies/{applicationToken}", method = RequestMethod.GET)
    @ResponseBody
    public List<EcfInfoDto> getVacantEventClassFamiliesByApplicationToken(@PathVariable String applicationToken) throws KaaAdminServiceException {
        return kaaAdminService.getVacantEventClassFamiliesByApplicationToken(applicationToken);
    }