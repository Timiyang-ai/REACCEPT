@Test
	public void testParse() {
		String json = "  {\n" + 
				"   \"application_type\": \"web\",\n" + 
				"   \"redirect_uris\":\n" + 
				"     [\"https://client.example.org/callback\",\n" + 
				"      \"https://client.example.org/callback2\"],\n" + 
				"   \"client_name\": \"My Example\",\n" + 
				"   \"client_name#ja-Jpan-JP\":\n" + 
				"     \"クライアント名\",\n" + 
				"   \"logo_uri\": \"https://client.example.org/logo.png\",\n" + 
				"   \"subject_type\": \"pairwise\",\n" + 
				"   \"sector_identifier_uri\":\n" + 
				"     \"https://other.example.net/file_of_redirect_uris.json\",\n" + 
				"   \"token_endpoint_auth_method\": \"client_secret_basic\",\n" + 
				"   \"jwks_uri\": \"https://client.example.org/my_public_keys.jwks\",\n" + 
				"   \"userinfo_encrypted_response_alg\": \"RSA1_5\",\n" + 
				"   \"userinfo_encrypted_response_enc\": \"A128CBC-HS256\",\n" + 
				"   \"contacts\": [\"ve7jtb@example.org\", \"mary@example.org\"],\n" + 
				"   \"request_uris\":\n" + 
				"     [\"https://client.example.org/rf.txt#qpXaRLh_n93TTR9F252ValdatUQvQiJi5BDub2BeznA\"]\n" + 
				"  }";
		ClientDetailsEntity c = ClientDetailsEntityJsonProcessor.parse(json);
		
		assertEquals(ClientDetailsEntity.AppType.WEB, c.getApplicationType());
		assertEquals(ImmutableSet.of("https://client.example.org/callback", "https://client.example.org/callback2"), c.getRedirectUris());
		assertEquals("My Example", c.getClientName());
		assertEquals("https://client.example.org/logo.png", c.getLogoUri());
		assertEquals(ClientDetailsEntity.SubjectType.PAIRWISE, c.getSubjectType());
		assertEquals("https://other.example.net/file_of_redirect_uris.json", c.getSectorIdentifierUri());
		assertEquals(ClientDetailsEntity.AuthMethod.SECRET_BASIC, c.getTokenEndpointAuthMethod());
		assertEquals("https://client.example.org/my_public_keys.jwks", c.getJwksUri());
		assertEquals(JWEAlgorithm.RSA1_5, c.getUserInfoEncryptedResponseAlg().getAlgorithm());
		assertEquals(EncryptionMethod.A128CBC_HS256, c.getUserInfoEncryptedResponseEnc().getAlgorithm());
		assertEquals(ImmutableSet.of("ve7jtb@example.org", "mary@example.org"), c.getContacts());
		assertEquals(ImmutableSet.of("https://client.example.org/rf.txt#qpXaRLh_n93TTR9F252ValdatUQvQiJi5BDub2BeznA"), c.getRequestUris());
		
	}