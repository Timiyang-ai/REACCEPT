@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCommand() throws GeniePreconditionException {
        // Add the id to the tags
        if (this.tags == null) {
           this.tags = new HashSet<>();
        }
        this.addAndValidateSystemTags(this.tags);
    }