@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCluster(
        @PathVariable("id") final String id,
        @RequestBody final JsonPatch patch
    ) throws GenieException {
        log.debug("Called to patch cluster {} with patch {}", id, patch);
        this.clusterService.patchCluster(id, patch);
    }