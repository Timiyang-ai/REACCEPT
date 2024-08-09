@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createCluster(@RequestBody @Valid final Cluster cluster) throws GenieException {
        log.debug("Called to create new cluster {}", cluster);
        final String id = this.clusterService.createCluster(DtoAdapters.toV4ClusterRequest(cluster));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri()
        );
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }