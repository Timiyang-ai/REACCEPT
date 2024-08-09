@Override
	public String buildAuthRequestUrl(ServerConfiguration serverConfig, RegisteredClient clientConfig, String redirectUri, String nonce, String state, Map<String, String> options, String loginHint) {

		// create our signed JWT for the request object
		JWTClaimsSet claims = new JWTClaimsSet();

		//set parameters to JwtClaims
		claims.setClaim("response_type", "code");
		claims.setClaim("client_id", clientConfig.getClientId());
		claims.setClaim("scope", Joiner.on(" ").join(clientConfig.getScope()));

		// build our redirect URI
		claims.setClaim("redirect_uri", redirectUri);

		// this comes back in the id token
		claims.setClaim("nonce", nonce);

		// this comes back in the auth request return
		claims.setClaim("state", state);

		// Optional parameters
		for (Entry<String, String> option : options.entrySet()) {
			claims.setClaim(option.getKey(), option.getValue());
		}

		// if there's a login hint, send it
		if (!Strings.isNullOrEmpty(loginHint)) {
			claims.setClaim("login_hint", loginHint);
		}

		EncryptedJWT jwt = new EncryptedJWT(new JWEHeader(alg, enc), claims);

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