@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCommonEntityFields() throws GenieException {
        this.validate(this.name, this.user, this.version, null);
    }