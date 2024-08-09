@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateApplication() throws GeniePreconditionException {
        this.validate(this.status, null);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.tags.add(this.getId());
        this.tags.add(this.getName());
    }