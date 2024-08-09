@PrePersist
    protected void onCreateAuditable() {
        final Date date = new Date();
        this.updated = date;
        this.created = date;

        //Make sure we have an id if one wasn't entered beforehand
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }