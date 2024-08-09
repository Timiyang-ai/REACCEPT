@GetMapping(value = "/{id}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getTagsForCluster(@PathVariable("id") final String id) throws GenieException {
        log.debug("Called with id {}", id);
        return DtoAdapters.toV3Cluster(this.clusterService.getCluster(id)).getTags();
    }