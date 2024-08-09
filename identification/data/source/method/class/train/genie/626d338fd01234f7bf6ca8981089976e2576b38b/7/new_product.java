@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchCluster(
        @PathVariable("id") final String id,
        @RequestBody final JsonPatch patch
    ) throws GenieException {
        log.debug("Called to patch cluster {} with patch {}", id, patch);

        final Cluster currentCluster = DtoAdapters.toV3Cluster(this.clusterService.getCluster(id));

        try {
            log.debug("Will patch cluster {}. Original state: {}", id, currentCluster);
            final JsonNode clusterNode = GenieObjectMapper.getMapper().valueToTree(currentCluster);
            final JsonNode postPatchNode = patch.apply(clusterNode);
            final Cluster patchedCluster = GenieObjectMapper.getMapper().treeToValue(postPatchNode, Cluster.class);
            log.debug("Finished patching cluster {}. New state: {}", id, patchedCluster);
            this.clusterService.updateCluster(id, DtoAdapters.toV4Cluster(patchedCluster));
        } catch (final JsonPatchException | IOException e) {
            log.error("Unable to patch cluster {} with patch {} due to exception.", id, patch, e);
            throw new GenieServerException(e.getLocalizedMessage(), e);
        }
    }