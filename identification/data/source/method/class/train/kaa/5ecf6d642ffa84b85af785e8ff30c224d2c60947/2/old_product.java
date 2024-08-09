@RequestMapping(value = "CTL/deleteSchema", params = { "fqn", "version" }, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCTLSchemaByFqnAndVersion(@RequestParam String fqn, @RequestParam int version) throws KaaAdminServiceException {
        kaaAdminService.deleteCTLSchemaByFqnAndVersion(fqn, version);
    }