@Override
	public String buildAuthRequestUrl(ServerConfiguration serverConfig, RegisteredClient clientConfig, String redirectUri, String nonce, String state, Map<String, String> options, String loginHint) {

		// create our signed JWT for the request object
		JWTClaimsSet.Builder claims = new JWTClaimsSet.Builder();

		//set parameters to JwtClaims
		claims.claim("response_type", "code");
		claims.claim("client_id", clientConfig.getClientId());
		claims.claim("scope", Joiner.on(" ").join(clientConfig.getScope()));

		// build our redirect URI
		claims.claim("redirect_uri", redirectUri);

		// this comes back in the id token
		claims.claim("nonce", nonce);

		// this comes back in the auth request return
		claims.claim("state", state);

		// Optional parameters
		for (Entry<String, String> option : options.entrySet()) {
			claims.claim(option.getKey(), option.getValue());
		}

		// if there's a login hint, send it
		if (!Strings.isNullOrEmpty(loginHint)) {
			claims.claim("login_hint", loginHint);
		}

		EncryptedJWT jwt = new EncryptedJWT(new JWEHeader(alg, enc), claims.build());

		JWTEncryptionAndDecryptionService encryptor = encrypterService.getEncrypter(serverConfig.getJwksUri());

		encryptor.encryptJwt(jwt);

		try {
			URIBuilder uriBuilder = new URIBuilder(serverConfig.getAuthorizationEndpointUri());
			uriBuilder.addParameter("request", jwt.serialize());

			// build out the URI
			return uriBuilder.build().toString();
		} catch (URISyntaxException e) {
			throw new AuthenticationServiceException("Malformed Authorization Endpoint Uri", e);
		}
	}