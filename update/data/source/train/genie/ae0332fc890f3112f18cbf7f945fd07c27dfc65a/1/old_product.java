@PrePersist
    @PreUpdate
    protected void onCreateOrUpdate() throws GenieException {
        validate(this.getName(), this.getUser(), this.status);
     // Add the id to the tags
        if (this.tags == null) {
            this.tags = new HashSet<String>();
        }
        this.tags.add(this.getId());
    }