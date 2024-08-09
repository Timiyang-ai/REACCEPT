@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateApplication(
        @PathVariable("id") final String id,
        @RequestBody final Application updateApp
    ) throws GenieException {
        log.debug("called to update application {} with info {}", id, updateApp);
        this.applicationService.updateApplication(id, DtoAdapters.toV4Application(updateApp));
    }