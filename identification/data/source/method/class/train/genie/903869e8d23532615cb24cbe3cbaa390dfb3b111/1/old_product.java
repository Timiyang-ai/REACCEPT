@GetMapping(value = "/{id}/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getTagsForApplication(@PathVariable("id") final String id) throws GenieException {
        log.debug("Called with id {}", id);
        return this.applicationService.getTagsForApplication(id);
    }