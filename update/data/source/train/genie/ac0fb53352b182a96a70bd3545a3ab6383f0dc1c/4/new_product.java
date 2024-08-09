@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateApplication() throws GenieException {
        this.setApplicationTags(this.getFinalTags());
    }