@GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CommandResource getCommand(@PathVariable("id") final String id) throws GenieException {
        log.debug("Called to get command with id {}", id);
        return this.commandResourceAssembler.toResource(DtoAdapters.toV3Command(this.commandService.getCommand(id)));
    }