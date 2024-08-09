@JsonIgnore
    public void setCreated(final Date created) {
        LOG.info("Tried to set created time to " + created + " for entity " + this.id + ". Ignoring.");
    }