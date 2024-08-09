@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCommand(
        @PathVariable("id") final String id,
        @RequestBody final Command updateCommand
    ) throws GenieException {
        log.debug("Called to update command {}", updateCommand);
        this.commandService.updateCommand(id, updateCommand);
    }