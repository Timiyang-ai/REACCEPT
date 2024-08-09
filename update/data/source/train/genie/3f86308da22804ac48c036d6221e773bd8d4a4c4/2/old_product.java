public void setCreated(final Date created) {
        //This is to prevent the create time from being updated after
        //an entity has been persisted and someone is just trying to
        //update another field in the entity
        if (created.before(this.created)) {
            this.created.setTime(created.getTime());
        }
    }