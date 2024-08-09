@Override
    public boolean logout() throws LoginException {
        if (this.subject.isReadOnly()) {
            throw new LoginException("Subject cannot be read-only.");
        }

        this.subject.getPrincipals().clear();

        if (this.username != null) {
            LOGGER.debug("logging out {}", this.username);
        }

        return true;
    }