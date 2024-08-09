@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchApplication(
        @PathVariable("id") final String id,
        @RequestBody final JsonPatch patch
    ) throws GenieException {
        log.debug("Called to patch application {} with patch {}", id, patch);
        final Application currentApp = DtoAdapters.toV3Application(this.applicationService.getApplication(id));

        try {
            log.debug("Will patch application {}. Original state: {}", id, currentApp);
            final JsonNode applicationNode = GenieObjectMapper.getMapper().valueToTree(currentApp);
            final JsonNode postPatchNode = patch.apply(applicationNode);
            final Application patchedApp = GenieObjectMapper.getMapper().treeToValue(postPatchNode, Application.class);
            log.debug("Finished patching application {}. New state: {}", id, patchedApp);
            this.applicationService.updateApplication(id, DtoAdapters.toV4Application(patchedApp));
        } catch (final JsonPatchException | IOException e) {
            log.error("Unable to patch application {} with patch {} due to exception.", id, patch, e);
            throw new GenieServerException(e.getLocalizedMessage(), e);
        }
    }