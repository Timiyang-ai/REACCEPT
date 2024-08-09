@Override
    public boolean commit() throws LoginException {
        if (this.principals == null) {
            return false;
        }

        if (this.subject.isReadOnly()) {
            throw new LoginException("Subject cannot be read-only.");
        }

        final Set<Principal> principalsSet = this.subject.getPrincipals();
        principalsSet.addAll(this.principals);

        LOGGER.debug("committing {} principals", Integer.valueOf(this.subject.getPrincipals().size()));
        if (this.debug) {
            for (Principal principal : principalsSet) {
                LOGGER.debug(" principal: {}", principal.getName());
            }
        }

        return true;
    }