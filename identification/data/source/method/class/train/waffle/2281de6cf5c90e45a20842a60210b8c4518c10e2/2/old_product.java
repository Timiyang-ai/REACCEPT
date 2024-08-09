@Override
    public boolean commit() throws LoginException {
        if (_principals == null) {
            return false;
        }

        if (_subject.isReadOnly()) {
            throw new LoginException("Subject cannot be read-only.");
        }

        Set<Principal> principals = _subject.getPrincipals();
        principals.addAll(_principals);

        _log.debug("committing {} principals", Integer.valueOf(_subject.getPrincipals().size()));
        if (_debug) {
            for (Principal principal : principals) {
                _log.debug(" principal: {}", principal.getName());
            }
        }

        return true;
    }