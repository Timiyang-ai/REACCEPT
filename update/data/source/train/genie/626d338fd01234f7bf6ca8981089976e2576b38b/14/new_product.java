@GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ClusterResource getCluster(@PathVariable("id") final String id) throws GenieException {
        log.debug("Called with id: {}", id);
        return this.clusterResourceAssembler.toResource(DtoAdapters.toV3Cluster(this.clusterService.getCluster(id)));
    }