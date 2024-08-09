@GetMapping(value = "/{id}/applications", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationResource> getApplicationsForCommand(
        @PathVariable("id") final String id
    ) throws GenieException {
        log.debug("Called with id {}", id);
        return this.commandService.getApplicationsForCommand(id)
            .stream()
            .map(DtoAdapters::toV3Application)
            .map(this.applicationResourceAssembler::toResource)
            .collect(Collectors.toList());
    }