@Test
	public void testSerialize() {
		RegisteredClient c = new RegisteredClient();
		
		c.setClientId("s6BhdRkqt3");
		c.setClientSecret("ZJYCqe3GGRvdrudKyZS0XhGv_Z45DuKhCUk0gBR1vZk");
		c.setClientSecretExpiresAt(new Date(1577858400L * 1000L));
		c.setRegistrationAccessToken("this.is.an.access.token.value.ffx83");
		c.setRegistrationClientUri("https://server.example.com/connect/register?client_id=s6BhdRkqt3");
		c.setApplicationType(ClientDetailsEntity.AppType.WEB);
		c.setRedirectUris(ImmutableSet.of("https://client.example.org/callback", "https://client.example.org/callback2"));
		c.setClientName("My Example");
		c.setLogoUri("https://client.example.org/logo.png");
		c.setSubjectType(ClientDetailsEntity.SubjectType.PAIRWISE);
		c.setSectorIdentifierUri("https://other.example.net/file_of_redirect_uris.json");
		c.setTokenEndpointAuthMethod(ClientDetailsEntity.AuthMethod.SECRET_BASIC);
		c.setJwksUri("https://client.example.org/my_public_keys.jwks");
		c.setUserInfoEncryptedResponseAlg(new JWEAlgorithmEmbed(JWEAlgorithm.RSA1_5));
		c.setUserInfoEncryptedResponseEnc(new JWEEncryptionMethodEmbed(EncryptionMethod.A128CBC_HS256));
		c.setContacts(ImmutableSet.of("ve7jtb@example.org", "mary@example.org"));
		c.setRequestUris(ImmutableSet.of("https://client.example.org/rf.txt#qpXaRLh_n93TTR9F252ValdatUQvQiJi5BDub2BeznA"));
		
		JsonObject j = ClientDetailsEntityJsonProcessor.serialize(c);

		assertEquals("s6BhdRkqt3", j.get("client_id").getAsString());
		assertEquals("ZJYCqe3GGRvdrudKyZS0XhGv_Z45DuKhCUk0gBR1vZk", j.get("client_secret").getAsString());
		assertEquals(1577858400L, j.get("client_secret_expires_at").getAsNumber());
		assertEquals("this.is.an.access.token.value.ffx83", j.get("registration_access_token").getAsString());
		assertEquals("https://server.example.com/connect/register?client_id=s6BhdRkqt3", j.get("registration_client_uri").getAsString());
		assertEquals(ClientDetailsEntity.AppType.WEB.getValue(), j.get("application_type").getAsString());
		for (JsonElement e : j.get("redirect_uris").getAsJsonArray()) { 
			assertTrue(ImmutableSet.of("https://client.example.org/callback", "https://client.example.org/callback2").contains(e.getAsString()));
        }
		assertEquals("My Example", j.get("client_name").getAsString());
		assertEquals("https://client.example.org/logo.png", j.get("logo_uri").getAsString());
		assertEquals(ClientDetailsEntity.SubjectType.PAIRWISE.getValue(), j.get("subject_type").getAsString());
		assertEquals("https://other.example.net/file_of_redirect_uris.json", j.get("sector_identifier_uri").getAsString());
		assertEquals(ClientDetailsEntity.AuthMethod.SECRET_BASIC.getValue(), j.get("token_endpoint_auth_method").getAsString());
		assertEquals("https://client.example.org/my_public_keys.jwks", j.get("jwks_uri").getAsString());
		assertEquals(JWEAlgorithm.RSA1_5.getName(), j.get("userinfo_encrypted_response_alg").getAsString());
		assertEquals(EncryptionMethod.A128CBC_HS256.getName(), j.get("userinfo_encrypted_response_enc").getAsString());
		for (JsonElement e : j.get("contacts").getAsJsonArray()) {
			assertTrue(ImmutableSet.of("ve7jtb@example.org", "mary@example.org").contains(e.getAsString()));
		}
		for (JsonElement e : j.get("request_uris").getAsJsonArray()) {
			assertTrue(ImmutableSet.of("https://client.example.org/rf.txt#qpXaRLh_n93TTR9F252ValdatUQvQiJi5BDub2BeznA").contains(e.getAsString()));
		}
		
	}