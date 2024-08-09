@Test
	public void buildAuthRequestUrl() {

		String requestUri = urlBuilder.buildAuthRequestUrl(serverConfig, clientConfig, redirectUri, nonce, state, options, null);

		// parsing the result
		UriComponentsBuilder builder = null;

		try {
			builder = UriComponentsBuilder.fromUri(new URI(requestUri));
		} catch (URISyntaxException e1) {
			fail("URISyntaxException was thrown.");
		}

		UriComponents components = builder.build();
		String jwtString = components.getQueryParams().get("request").get(0);
		ReadOnlyJWTClaimsSet claims = null;

		try {
			SignedJWT jwt = SignedJWT.parse(jwtString);
			claims = jwt.getJWTClaimsSet();
		} catch (ParseException e) {
			fail("ParseException was thrown.");
		}

		assertEquals(responseType, claims.getClaim("response_type"));
		assertEquals(clientConfig.getClientId(), claims.getClaim("client_id"));

		List<String> scopeList = Arrays.asList(((String) claims.getClaim("scope")).split(" "));
		assertTrue(scopeList.containsAll(clientConfig.getScope()));

		assertEquals(redirectUri, claims.getClaim("redirect_uri"));
		assertEquals(nonce, claims.getClaim("nonce"));
		assertEquals(state, claims.getClaim("state"));
		for (String claim : options.keySet()) {
			assertEquals(options.get(claim), claims.getClaim(claim));
		}
	}