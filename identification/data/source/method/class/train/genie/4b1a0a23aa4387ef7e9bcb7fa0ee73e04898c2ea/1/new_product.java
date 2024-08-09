@Override
    public void patchCommand(@NotBlank final String id, @NotNull final JsonPatch patch) throws GenieException {
        final CommandEntity commandEntity = this.findCommand(id);
        try {
            final Command commandToPatch = JpaServiceUtils.toCommandDto(commandEntity);
            log.debug("Will patch command {}. Original state: {}", id, commandToPatch);
            final JsonNode commandNode = GenieObjectMapper.getMapper().readTree(commandToPatch.toString());
            final JsonNode postPatchNode = patch.apply(commandNode);
            final Command patchedCommand = GenieObjectMapper.getMapper().treeToValue(postPatchNode, Command.class);
            log.debug("Finished patching command {}. New state: {}", id, patchedCommand);
            this.updateEntityWithDtoContents(commandEntity, patchedCommand);
        } catch (final JsonPatchException | IOException e) {
            log.error("Unable to patch cluster {} with patch {} due to exception.", id, patch, e);
            throw new GenieServerException(e.getLocalizedMessage(), e);
        }
    }