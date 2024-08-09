private Pair<Boolean, ActionOnFailedAuthentication> authenticate(String username, String password, Long domainId, UserAccount user) {
        boolean result = false;

        if(user != null ) {
            try {
                LdapUser ldapUser = _ldapManager.getUser(username, domainId);
                if(!ldapUser.isDisabled()) {
                    result = _ldapManager.canAuthenticate(ldapUser.getPrincipal(), password, domainId);
                } else {
                    s_logger.debug("user with principal "+ ldapUser.getPrincipal() + " is disabled in ldap");
                }
            } catch (NoLdapUserMatchingQueryException e) {
                s_logger.debug(e.getMessage());
            }
        }
        return (!result && user != null) ?
                new Pair<Boolean, ActionOnFailedAuthentication>(false, ActionOnFailedAuthentication.INCREMENT_INCORRECT_LOGIN_ATTEMPT_COUNT):
                new Pair<Boolean, ActionOnFailedAuthentication>(false, null);
    }