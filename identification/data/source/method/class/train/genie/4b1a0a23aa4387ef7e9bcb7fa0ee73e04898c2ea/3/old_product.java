@Override
    public void patchCluster(@NotBlank final String id, @NotNull final JsonPatch patch) throws GenieException {
        final ClusterEntity clusterEntity = this.findCluster(id);
        try {
            final Cluster clusterToPatch = JpaServiceUtils.toClusterDto(clusterEntity);
            log.debug("Will patch cluster {}. Original state: {}", id, clusterToPatch);
            final JsonNode clusterNode = MAPPER.readTree(clusterToPatch.toString());
            final JsonNode postPatchNode = patch.apply(clusterNode);
            final Cluster patchedCluster = MAPPER.treeToValue(postPatchNode, Cluster.class);
            log.debug("Finished patching cluster {}. New state: {}", id, patchedCluster);
            this.updateEntityWithDtoContents(clusterEntity, patchedCluster);
        } catch (final JsonPatchException | IOException e) {
            log.error("Unable to patch cluster {} with patch {} due to exception.", id, patch, e);
            throw new GenieServerException(e.getLocalizedMessage(), e);
        }
    }