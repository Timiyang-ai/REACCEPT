@JsonIgnore
    public void setUpdated(final Date updated) {
        LOG.info("Tried to set updated time to " + updated + " for entity " + this.id + ". Ignoring.");
    }