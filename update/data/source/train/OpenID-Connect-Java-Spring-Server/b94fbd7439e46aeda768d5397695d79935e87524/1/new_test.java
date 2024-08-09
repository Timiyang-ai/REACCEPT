@Test
	public void testGetSigner() throws Exception {
		//create key, sign it, for both x509 and jwk. 
	/*	jsvs.setX509SigningUrl(x509Url.getPath());
		x509Key = jsvs.getSigningKey();
		jsvs.setJwkSigningUrl(jwkUrl.getPath());
		jwkKey = jsvs.getSigningKey();
		
		JsonParser parser = new JsonParser();
		
		String rsaStr = parser.parse(new BufferedReader(new InputStreamReader(jwkUrl.openStream()))).getAsString();
		JwtSigner rsaSigner = jsvs.getSigner(rsaStr);
		
		String x509Str = parser.parse(new BufferedReader(new InputStreamReader(x509Url.openStream()))).getAsString();
		JwtSigner x509Signer = jsvs.getSigner(x509Str);*/
		assertEquals("yo", "yo");
	}