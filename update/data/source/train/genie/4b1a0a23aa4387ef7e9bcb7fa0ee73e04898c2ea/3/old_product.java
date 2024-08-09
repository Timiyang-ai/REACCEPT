@Override
    public void patchApplication(@NotBlank final String id, @NotNull final JsonPatch patch) throws GenieException {
        final ApplicationEntity applicationEntity = this.findApplication(id);
        try {
            final Application appToPatch = JpaServiceUtils.toApplicationDto(applicationEntity);
            log.debug("Will patch application {}. Original state: {}", id, appToPatch);
            final JsonNode applicationNode = MAPPER.readTree(appToPatch.toString());
            final JsonNode postPatchNode = patch.apply(applicationNode);
            final Application patchedApp = MAPPER.treeToValue(postPatchNode, Application.class);
            log.debug("Finished patching application {}. New state: {}", id, patchedApp);
            this.updateEntityWithDtoContents(applicationEntity, patchedApp);
        } catch (final JsonPatchException | IOException e) {
            log.error("Unable to patch application {} with patch {} due to exception.", id, patch, e);
            throw new GenieServerException(e.getLocalizedMessage(), e);
        }
    }