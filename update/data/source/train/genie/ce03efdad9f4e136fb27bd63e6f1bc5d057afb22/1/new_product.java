public void setCreated(final Date created) {
        LOG.info("Tried to set created to " + created + " for entity " + this.id + ". Will not be persisted.");
        if (created.before(this.created)) {
            this.created = new Date(created.getTime());
        }
    }