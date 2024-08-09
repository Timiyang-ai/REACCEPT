@RequestMapping(value="profileFilterRecord", method=RequestMethod.GET)
    @ResponseBody
    public ProfileFilterRecordDto getProfileFilterRecord(
            @RequestParam(value="endpointProfileSchemaId", required = false) String endpointProfileSchemaId,
            @RequestParam(value="serverProfileSchemaId", required = false) String serverProfileSchemaId,
            @RequestParam(value="endpointGroupId") String endpointGroupId) throws KaaAdminServiceException {
        return kaaAdminService.getProfileFilterRecord(endpointProfileSchemaId, serverProfileSchemaId, endpointGroupId);
    }