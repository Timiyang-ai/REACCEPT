@RequestMapping(value="delProfileFilterRecord", method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteProfileFilterRecord(
            @RequestParam(value="endpointProfileSchemaId") String endpointProfileSchemaId,
            @RequestParam(value="serverProfileSchemaId") String serverProfileSchemaId,
            @RequestParam(value="endpointGroupId") String endpointGroupId) throws KaaAdminServiceException {
        kaaAdminService.deleteProfileFilterRecord(endpointProfileSchemaId, serverProfileSchemaId, endpointGroupId);
    }