@GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResource getApplication(@PathVariable("id") final String id) throws GenieException {
        log.debug("Called to get Application for id {}", id);
        return this.applicationResourceAssembler.toResource(
            DtoAdapters.toV3Application(this.applicationService.getApplication(id))
        );
    }