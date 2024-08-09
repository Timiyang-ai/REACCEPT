@GetMapping(value = "/{id}/commands", produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CommandResource> getCommandsForCluster(
        @PathVariable("id") final String id,
        @RequestParam(value = "status", required = false) final Set<String> statuses
    ) throws GenieException {
        log.debug("Called with id {} status {}", id, statuses);

        Set<CommandStatus> enumStatuses = null;
        if (statuses != null) {
            enumStatuses = EnumSet.noneOf(CommandStatus.class);
            for (final String status : statuses) {
                enumStatuses.add(CommandStatus.parse(status));
            }
        }

        return this.clusterService.getCommandsForCluster(id, enumStatuses)
            .stream()
            .map(this.commandResourceAssembler::toResource)
            .collect(Collectors.toList());
    }