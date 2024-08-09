@PrePersist
    @PreUpdate
    protected void onCreateOrUpdateCommonEntityFields() throws GeniePreconditionException {
        this.validate(this.name, this.user, this.version, null);
    }