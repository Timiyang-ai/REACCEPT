@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateApplication() throws GeniePreconditionException {
        this.validate(this.status, null);
        if (this.tags == null) {
            this.tags = new HashSet<>();
        }
        this.addAndValidateSystemTags(this.tags);
    }