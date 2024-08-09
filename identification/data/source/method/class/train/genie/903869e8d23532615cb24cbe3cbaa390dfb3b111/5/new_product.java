@GetMapping(value = "/{id}/commands", produces = MediaTypes.HAL_JSON_VALUE)
    public Set<CommandResource> getCommandsForApplication(
        @PathVariable("id") final String id,
        @RequestParam(value = "status", required = false) final Set<String> statuses
    ) throws GenieException {
        log.debug("Called with id {}", id);

        Set<CommandStatus> enumStatuses = null;
        if (statuses != null) {
            enumStatuses = EnumSet.noneOf(CommandStatus.class);
            for (final String status : statuses) {
                enumStatuses.add(CommandStatus.parse(status));
            }
        }

        return this.applicationService.getCommandsForApplication(id, enumStatuses)
            .stream()
            .map(DtoAdapters::toV3Command)
            .map(this.commandResourceAssembler::toResource)
            .collect(Collectors.toSet());
    }