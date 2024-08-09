@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateApplication() throws GenieException {
        this.validate(this.status);
        // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<String>();
        }
        this.tags.add(this.getId());
    }