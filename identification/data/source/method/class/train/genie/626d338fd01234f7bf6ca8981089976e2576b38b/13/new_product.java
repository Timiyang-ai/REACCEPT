@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCommand(
        @PathVariable("id") final String id,
        @RequestBody final JsonPatch patch
    ) throws GenieException {
        log.debug("Called to patch command {} with patch {}", id, patch);

        final Command currentCommand = DtoAdapters.toV3Command(this.commandService.getCommand(id));

        try {
            log.debug("Will patch cluster {}. Original state: {}", id, currentCommand);
            final JsonNode commandNode = GenieObjectMapper.getMapper().valueToTree(currentCommand);
            final JsonNode postPatchNode = patch.apply(commandNode);
            final Command patchedCommand = GenieObjectMapper.getMapper().treeToValue(postPatchNode, Command.class);
            log.debug("Finished patching command {}. New state: {}", id, patchedCommand);
            this.commandService.updateCommand(id, DtoAdapters.toV4Command(patchedCommand));
        } catch (final JsonPatchException | IOException e) {
            log.error("Unable to patch command {} with patch {} due to exception.", id, patch, e);
            throw new GenieServerException(e.getLocalizedMessage(), e);
        }
    }