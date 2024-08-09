@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateApplication() throws GeniePreconditionException {
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.addAndValidateSystemTags(this.tags);
    }