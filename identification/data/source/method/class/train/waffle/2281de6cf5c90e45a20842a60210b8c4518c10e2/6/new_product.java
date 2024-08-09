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
        
        debug("committing " + _subject.getPrincipals().size() + " principals");
        if (_debug) {
        	for (Principal principal : principals) {
        		debug(" principal: " + principal.getName());
        	}
        }
        
		return true;
	}