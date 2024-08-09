public InitializeResult initialize() throws WIMApplicationException {
        final String METHODNAME = "initialize";
        iEnvironment = new Hashtable<String, Object>();
        iEnvironment.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_SUN_SPI);

        String urlPrefix = null;
        if (iSSLEnabled) {
            iEnvironment.put(LDAP_ENV_PROP_FACTORY_SOCKET, WAS_SSL_SOCKET_FACTORY);
            iEnvironment.put(Context.SECURITY_PROTOCOL, "ssl");
            urlPrefix = LDAP_URL_SSL_PREFIX;
        } else {
            urlPrefix = LDAP_URL_PREFIX;
        }

        List<String> urlList = new ArrayList<String>();

        /*
         * Add the primary server to the URL list.
         */
        if (iPrimaryServer == null || iPrimaryServer.hostname == null || iPrimaryServer.hostname.trim().isEmpty()) {
            return InitializeResult.MISSING_PRIMARY_SERVER;
        }
        String mainHost = iPrimaryServer.hostname;
        int mainPort = iPrimaryServer.port;
        urlList.add(urlPrefix + mainHost.trim() + ":" + mainPort);

        /*
         * Add the fail-over servers to the URL list.
         */
        for (HostPort failoverServer : iFailoverServers) {
            String ldapHost = failoverServer.hostname;
            if (ldapHost == null || ldapHost.trim().isEmpty()) {
                continue;
            }

            if (!(ldapHost.startsWith("[") && ldapHost.endsWith("]"))) {
                if (isIPv6Addr(ldapHost)) {
                    ldapHost = formatIPv6Addr(ldapHost);
                }
            }

            if (failoverServer.port != null) {
                urlList.add(urlPrefix + ldapHost.trim() + ":" + failoverServer.port);
            }
        }

        if (urlList != null && urlList.size() > 0) {
            String url = urlList.get(0);
            iEnvironment.put(ENVKEY_URL_LIST, urlList);
            iEnvironment.put(ENVKEY_ACTIVE_URL, url);
            iEnvironment.put(Context.PROVIDER_URL, url);
        }

        /*
         * If no administrative credentials, allow anonymous bind.
         */
        if (iBindDN != null && !iBindDN.isEmpty()) {
            iEnvironment.put(Context.SECURITY_PRINCIPAL, iBindDN);
            SerializableProtectedString sps = iBindPassword;
            String password = sps == null ? "" : new String(sps.getChars());
            String decodedPassword = PasswordUtil.passwordDecode(password.trim());

            /*
             * A password is required if we had a bind DN.
             */
            if (decodedPassword == null || decodedPassword.length() == 0) {
                return InitializeResult.MISSING_PASSWORD;
            }
            iEnvironment.put(Context.SECURITY_CREDENTIALS, new ProtectedString(decodedPassword.toCharArray()));
        }

        /*
         * Set the LDAP connection time out
         */
        if (iConnectTimeout != null) {
            iEnvironment.put(LDAP_ENV_PROP_CONNECT_TIMEOUT, iConnectTimeout.toString());
        } else {
            iEnvironment.put(LDAP_ENV_PROP_CONNECT_TIMEOUT, String.valueOf(DEFAULT_CONNECT_TIMEOUT));
        }

        /*
         * Set the LDAP read time out.
         */
        if (iReadTimeout != null) {
            iEnvironment.put(LDAP_ENV_PROP_READ_TIMEOUT, iReadTimeout.toString());
        } else {
            iEnvironment.put(LDAP_ENV_PROP_READ_TIMEOUT, String.valueOf(DEFAULT_READ_TIMEOUT));
        }

        /*
         * TODO Support different authentication mechanisms.
         *
         * String authen = (String) configProps.get(ConfigConstants.CONFIG_PROP_AUTHENTICATION);
         * iEnvironment.put(Context.SECURITY_AUTHENTICATION, authen);
         */

        /*
         * Determine referral handling behavior.
         */
        iEnvironment.put(Context.REFERRAL, iReferral);

        /*
         * TODO Support dereferencing aliases.
         *
         * String derefAliases = (String) configProps.get(ConfigConstants.CONFIG_PROP_DEREFALIASES);
         * if (!"always".equalsIgnoreCase(derefAliases)) {
         * iEnvironment.put(LdapConstants.LDAP_ENV_PROP_DEREF_ALIASES, derefAliases);
         * }
         */

        /*
         * Add binary attribute names
         */
        if (iBinaryAttributeNames != null && iBinaryAttributeNames.length() > 0) {
            iEnvironment.put(LDAP_ENV_PROP_ATTRIBUTES_BINARY, iBinaryAttributeNames);
        }

        /*
         * TODO Support other environment properties.
         *
         * // Initialize additional environment properties. These environ props will overwrite the above settings.
         * List envProps = server.getList(CONFIG_PROP_ENVIRONMENT_PROPERTIES);
         * for (int i = 0; i < envProps.size(); i++) {
         * DataObject envProp = (DataObject) envProps.get(i);
         * String name = envProp.getString(CONFIG_PROP_NAME);
         * String value = envProp.getString(CONFIG_PROP_VALUE);
         * iEnvironment.put(name, value);
         * }
         */

        /*
         * Create Context Pool
         */
        try {
            createContextPool(iInitPoolSize, null);
        } catch (NamingException e) {
            if (tc.isDebugEnabled()) {
                Tr.debug(tc, METHODNAME + " Can not create context pool: " + e.toString(true));
            }
        }

        if (tc.isDebugEnabled()) {
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("\nLDAP Server(s): ").append(urlList).append("\n");
            strBuf.append("\tBind DN: ").append(iBindDN).append("\n");
            // strBuf.append("\tAuthenticate: ").append(authen).append("\n");
            strBuf.append("\tReferral: ").append(iReferral).append("\n");
            strBuf.append("\tBinary Attributes: ").append(iBinaryAttributeNames).append("\n");
            // strBuf.append("\tAdditional Evn Props: ").append(envProps);

            if (iContextPoolEnabled) {
                strBuf.append("\nContext Pool is enabled: ").append("\n");
                strBuf.append("\tInitPoolSize: ").append(iInitPoolSize).append("\n");
                strBuf.append("\tMaxPoolSize: ").append(iMaxPoolSize).append("\n");
                strBuf.append("\tPrefPoolSize: ").append(iPrefPoolSize).append("\n");
                strBuf.append("\tPoolTimeOut: ").append(iPoolTimeOut).append("\n");
                strBuf.append("\tPoolWaitTime: ").append(iPoolWaitTime);
            } else {
                strBuf.append("\nContext Pool is disabled");
            }
            Tr.debug(tc, METHODNAME + strBuf.toString());
        }

        return InitializeResult.SUCCESS;
    }