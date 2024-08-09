@PrePersist
    protected void onCreateApplication() throws GenieException {
        validate(this.name, this.user, this.status);
    }