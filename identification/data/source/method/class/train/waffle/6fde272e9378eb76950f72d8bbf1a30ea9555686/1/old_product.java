@Override
    public boolean logout() throws LoginException {
        if (_subject.isReadOnly()) {
            throw new LoginException("Subject cannot be read-only.");
        }

        _subject.getPrincipals().clear();

        if (_username != null) {
            _log.debug("logging out {}", _username);
        }

        return true;
    }