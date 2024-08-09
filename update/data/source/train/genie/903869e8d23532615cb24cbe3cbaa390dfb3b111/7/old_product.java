@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchApplication(
        @PathVariable("id") final String id,
        @RequestBody final JsonPatch patch
    ) throws GenieException {
        log.debug("Called to patch application {} with patch {}", id, patch);
        this.applicationService.patchApplication(id, patch);
    }