@SuppressWarnings("unchecked")
	public static SSLContext createSSLContext(String trustedNamesGroupKey) throws IOException {
        SSLContext sslContext = null;
        KeyManager[] keyManagers = null;
        Map<String, Object> tlsMap = (Map<String, Object>)ClientConfig.get().getMappedConfig().get(TLS);
        if(tlsMap != null) {
            try {
                // load key store for client certificate if two way ssl is used.
                Boolean loadKeyStore = (Boolean) tlsMap.get(LOAD_KEY_STORE);
                if (loadKeyStore != null && loadKeyStore) {
                    String keyStoreName = (String)tlsMap.get(KEY_STORE);
                    String keyStorePass = (String)ClientConfig.get().getSecretConfig().get(SecretConstants.CLIENT_KEYSTORE_PASS);
                    String keyPass = (String)ClientConfig.get().getSecretConfig().get(SecretConstants.CLIENT_KEY_PASS);
                    KeyStore keyStore = loadKeyStore(keyStoreName, keyStorePass.toCharArray());
                    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    keyManagerFactory.init(keyStore, keyPass.toCharArray());
                    keyManagers = keyManagerFactory.getKeyManagers();
                }
            } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException e) {
                throw new IOException("Unable to initialise KeyManager[]", e);
            }

            TrustManager[] trustManagers = null;
            try {
                // load trust store, this is the server public key certificate
                // first check if javax.net.ssl.trustStore system properties is set. It is only necessary if the server
                // certificate doesn't have the entire chain.
                Boolean loadTrustStore = (Boolean) tlsMap.get(LOAD_TRUST_STORE);
                if (loadTrustStore != null && loadTrustStore) {
                    String trustStoreName = System.getProperty(TRUST_STORE_PROPERTY);
                    String trustStorePass = System.getProperty(TRUST_STORE_PASSWORD_PROPERTY);
                    if (trustStoreName != null && trustStorePass != null) {
                        if(logger.isInfoEnabled()) logger.info("Loading trust store from system property at " + Encode.forJava(trustStoreName));
                    } else {
                        trustStoreName = (String) tlsMap.get(TRUST_STORE);
                        trustStorePass = (String)ClientConfig.get().getSecretConfig().get(SecretConstants.CLIENT_TRUSTSTORE_PASS);
                        if(logger.isInfoEnabled()) logger.info("Loading trust store from config at " + Encode.forJava(trustStoreName));
                    }
                    if (trustStoreName != null && trustStorePass != null) {
                        KeyStore trustStore = loadKeyStore(trustStoreName, trustStorePass.toCharArray());
                        TLSConfig tlsConfig = TLSConfig.create(tlsMap, trustedNamesGroupKey);

                        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init(trustStore);
                        trustManagers = ClientX509ExtendedTrustManager.decorate(trustManagerFactory.getTrustManagers(), tlsConfig);
                    }
                }
            } catch (NoSuchAlgorithmException | KeyStoreException e) {
                throw new IOException("Unable to initialise TrustManager[]", e);
            }

            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(keyManagers, trustManagers, null);
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                throw new IOException("Unable to create and initialise the SSLContext", e);
            }
        } else {
            logger.error("TLS configuration section is missing in client.yml");
        }

        return sslContext;
    }