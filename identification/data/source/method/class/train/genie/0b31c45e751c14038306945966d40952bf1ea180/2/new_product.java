public void setId(final String id) throws GeniePreconditionException {
        if (StringUtils.isBlank(this.id)) {
            this.id = id;
        } else {
            throw new GeniePreconditionException("Id already set for this entity.");
        }
    }