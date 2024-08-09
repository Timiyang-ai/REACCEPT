@PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() throws GenieException {
        validate(this.name, this.user, this.status);
    }