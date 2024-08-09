@RequestMapping(value = "CTL/getSchema", params = { "fqn", "version" }, method = RequestMethod.GET)
    @ResponseBody
    public CTLSchemaInfoDto getCTLSchemaByFqnAndVersion(@RequestParam String fqn, @RequestParam int version)
            throws KaaAdminServiceException {
        return kaaAdminService.getCTLSchemaByFqnAndVersion(fqn, version);
    }