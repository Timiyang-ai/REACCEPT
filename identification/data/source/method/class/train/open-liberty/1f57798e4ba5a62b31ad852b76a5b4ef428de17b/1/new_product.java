@Override
    public SecurityUtilityReturnCodes handleTask(ConsoleWrapper stdin, PrintStream stdout, PrintStream stderr, String[] args) throws Exception {
        this.stdin = stdin;
        this.stdout = stdout;
        this.stderr = stderr;

        validateArgumentList(args, Arrays.asList(new String[] { ARG_PASSWORD }));
        String serverName = getArgumentValue(ARG_SERVER, args, null);
        String clientName = getArgumentValue(ARG_CLIENT, args, null);
        String ou_name = null;
        String dir = null;

        // if a server and client both were specified we would not get this far

        // Verify the server or client exists, if it does not then exit and do not create the certificate
        // Do this first so we don't prompt for a password we'll not use
        if (serverName != null) {
            String usrServers = fileUtility.getServersDirectory();
            String serverDir = usrServers + serverName + SLASH;

            if (!fileUtility.exists(serverDir)) {
                usrServers = fileUtility.resolvePath(usrServers);
                stdout.println(getMessage("sslCert.abort"));
                stdout.println(getMessage("serverNotFound", serverName, usrServers));
                return SecurityUtilityReturnCodes.ERR_SERVER_NOT_FOUND;
            }
            dir = serverDir;
            ou_name = serverName;
        }

        if (clientName != null) {
            String usrClients = fileUtility.getClientsDirectory();
            String clientDir = usrClients + clientName + SLASH;

            if (!fileUtility.exists(clientDir)) {
                usrClients = fileUtility.resolvePath(usrClients);
                stdout.println(getMessage("sslCert.abort"));
                stdout.println(getMessage("sslCert.clientNotFound", clientName, usrClients));
                return SecurityUtilityReturnCodes.ERR_CLIENT_NOT_FOUND;
            }
            dir = clientDir;
            ou_name = clientName;
        }

        // Create the directories we need before we prompt for a password
        String location = null;

        String keyType = getArgumentValue(ARG_KEY_TYPE, args, null);
        if (keyType != null) {
            if (keyType.equalsIgnoreCase(JKS))
                location = dir + "resources" + SLASH + "security" + SLASH + JKS_KEYFILE;
            else if (keyType.equalsIgnoreCase(PKCS12))
                location = dir + "resources" + SLASH + "security" + SLASH + PKCS12_KEYFILE;
        } else {
            location = dir + "resources" + SLASH + "security" + SLASH + PKCS12_KEYFILE;
        }

        File fLocation = new File(location);
        location = fileUtility.resolvePath(fLocation);
        if (!fileUtility.createParentDirectory(stdout, fLocation)) {
            stdout.println(getMessage("sslCert.abort"));
            stdout.println(getMessage("file.requiredDirNotCreated", location));
            return SecurityUtilityReturnCodes.ERR_PATH_CANNOT_BE_CREATED;
        }

        if (fLocation.exists()) {
            stdout.println(getMessage("sslCert.abort"));
            stdout.println(getMessage("file.exists", location));
            return SecurityUtilityReturnCodes.ERR_FILE_EXISTS;
        }

        String password = getArgumentValue(ARG_PASSWORD, args, null);
        int validity = Integer.valueOf(getArgumentValue(ARG_VALIDITY, args, String.valueOf(DefaultSSLCertificateCreator.DEFAULT_VALIDITY)));
        String subjectDN = getArgumentValue(ARG_SUBJECT, args, new DefaultSubjectDN(null, ou_name).getSubjectDN());
        int keySize = Integer.valueOf(getArgumentValue(ARG_KEYSIZE, args, String.valueOf(DefaultSSLCertificateCreator.DEFAULT_SIZE)));
        String sigAlg = getArgumentValue(ARG_SIGALG, args, DefaultSSLCertificateCreator.SIGALG);
        List<String> extInfo = getExtInfoArgumentValues(ARG_EXT, args);

        try {
            String encoding = getArgumentValue(ARG_ENCODING, args, PasswordUtil.getDefaultEncoding());
            String key = getArgumentValue(ARG_KEY, args, null);
            stdout.println(getMessage("sslCert.createKeyStore", location));
            String encodedPassword = PasswordUtil.encode(password, encoding, key);
            creator.createDefaultSSLCertificate(location, password, validity, subjectDN, keySize, sigAlg, extInfo);
            String xmlSnippet = null;
            if (serverName != null) {
                stdout.println(getMessage("sslCert.serverXML", serverName, subjectDN));
                xmlSnippet = "    <featureManager>" + NL +
                             "        <feature>ssl-1.0</feature>" + NL +
                             "    </featureManager>" + NL +
                             "    <keyStore id=\"defaultKeyStore\" password=\"" + encodedPassword + "\" />" + NL;

            } else {
                stdout.println(getMessage("sslCert.clientXML", clientName, subjectDN));
                xmlSnippet = "    <featureManager>" + NL +
                             "        <feature>appSecurityClient-1.0</feature>" + NL +
                             "    </featureManager>" + NL +
                             "    <keyStore id=\"defaultKeyStore\" password=\"" + encodedPassword + "\" />" + NL;

            }
            stdout.println(NL + createConfigFileIfNeeded(dir, args, xmlSnippet) + NL);
        } catch (CertificateException e) {
            stdout.println(getMessage("sslCert.createFailed", e.getMessage()));
            throw (e);
        } catch (InvalidPasswordEncodingException e) {
            stdout.println(getMessage("sslCert.errorEncodePassword", e.getMessage()));
            throw (e);
        } catch (UnsupportedCryptoAlgorithmException e) {
            stdout.println(getMessage("sslCert.errorEncodePassword", e.getMessage()));
            throw (e);
        }

        return SecurityUtilityReturnCodes.OK;
    }