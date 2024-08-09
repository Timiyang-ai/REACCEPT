private Pair<Boolean, ActionOnFailedAuthentication> authenticate(String username, String password, Long domainId, UserAccount userAccount, List<LdapTrustMapVO> ldapTrustMapVOs) {
        Pair<Boolean, ActionOnFailedAuthentication> rc = new Pair<Boolean, ActionOnFailedAuthentication>(false, null);
        try {
            LdapUser ldapUser = _ldapManager.getUser(username, domainId);
            List<String> memberships = ldapUser.getMemberships();
            List<String> mappedGroups = getMappedGroups(ldapTrustMapVOs);
            mappedGroups.retainAll(memberships);
            // check membership, there must be only one match in this domain
            if(ldapUser.isDisabled()) {
                logAndDisable(userAccount, "attempt to log on using disabled ldap user " + userAccount.getUsername(), false);
            } else if(mappedGroups.size() > 1) {
                logAndDisable(userAccount, "user '" + username + "' is mapped to more then one account in domain and will be disabled.", false);
            } else if(mappedGroups.size() < 1) {
                logAndDisable(userAccount, "user '" + username + "' is not mapped to an account in domain and will be removed.", true);
            } else {
                // a valid ldap configured user exists
                LdapTrustMapVO mapping = _ldapManager.getLinkedLdapGroup(domainId,mappedGroups.get(0));
                // we could now assert that ldapTrustMapVOs.contains(mapping);
                // createUser in Account can only be done by account name not by account id
                String accountName = _accountManager.getAccount(mapping.getAccountId()).getAccountName();
                rc.first(_ldapManager.canAuthenticate(ldapUser.getPrincipal(), password, domainId));
                // for security reasons we keep processing on faulty login attempt to not give a way information on userid existence
                if (userAccount == null) {
                    // new user that is in ldap; authenticate and create
                    User user = _accountManager.createUser(username, "", ldapUser.getFirstname(), ldapUser.getLastname(), ldapUser.getEmail(), null, accountName,
                            domainId, UUID.randomUUID().toString(), User.Source.LDAP);
                    /* expected error conditions:
                     *
                     * caught in APIServlet: CloudRuntimeException("The domain " + domainId + " does not exist; unable to create user");
                     * caught in APIServlet: CloudRuntimeException("The user cannot be created as domain " + domain.getName() + " is being deleted");
                     * would have been thrown above: InvalidParameterValueException("Unable to find account " + accountName + " in domain id=" + domainId + " to create user");
                     * we are system user: PermissionDeniedException("Account id : " + account.getId() + " is a system account, can't add a user to it");
                     * serious and must be thrown: CloudRuntimeException("The user " + userName + " already exists in domain " + domainId);
                     * fatal system error and must be thrown: CloudRuntimeException("Failed to encode password");
                     */
                    userAccount = _accountManager.getUserAccountById(user.getId());
                } else {
                    // not a new user, check if mapped group has changed
                    if(userAccount.getAccountId() != mapping.getAccountId()) {
                        _accountManager.moveUser(userAccount.getId(),userAccount.getDomainId(),mapping.getAccountId());
                    }
                    // else { the user hasn't changed in ldap, the ldap group stayed the same, hurray, pass, fun thou self a lot of fun }
                }
            }
        } catch (NoLdapUserMatchingQueryException e) {
            s_logger.debug(e.getMessage());
            disableUserInCloudStack(userAccount);
        }

        return rc;
    }