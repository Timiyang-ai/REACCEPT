public static JsonObject serialize(RegisteredClient c) {
		JsonObject o = new JsonObject();

		o.addProperty("client_id", c.getClientId());
		if (c.getClientSecret() != null) {
			o.addProperty("client_secret", c.getClientSecret());
			
			if (c.getClientSecretExpiresAt() == null) {
				o.addProperty("client_secret_expires_at", 0); // TODO: do we want to let secrets expire?
			} else {
				o.addProperty("client_secret_expires_at", c.getClientSecretExpiresAt().getTime() / 1000L);
			}
		}

		if (c.getClientIdIssuedAt() != null) {
			o.addProperty("client_id_issued_at", c.getClientIdIssuedAt().getTime() / 1000L);
		} else if (c.getCreatedAt() != null) {
			o.addProperty("client_id_issued_at", c.getCreatedAt().getTime() / 1000L);
		}
		if (c.getRegistrationAccessToken() != null) {
			o.addProperty("registration_access_token", c.getRegistrationAccessToken());
		}

		if (c.getRegistrationClientUri() != null) {
			o.addProperty("registration_client_uri", c.getRegistrationClientUri());
		}


		// add in all other client properties

		// OAuth DynReg
		o.add("redirect_uris", getAsArray(c.getRedirectUris()));
		o.addProperty("client_name", c.getClientName());
		o.addProperty("client_uri", c.getClientUri());
		o.addProperty("logo_uri", c.getLogoUri());
		o.add("contacts", getAsArray(c.getContacts()));
		o.addProperty("tos_uri", c.getTosUri());
		o.addProperty("token_endpoint_auth_method", c.getTokenEndpointAuthMethod() != null ? c.getTokenEndpointAuthMethod().getValue() : null);
		o.addProperty("scope", c.getScope() != null ? Joiner.on(" ").join(c.getScope()) : null);
		o.add("grant_types", getAsArray(c.getGrantTypes()));
		o.addProperty("policy_uri", c.getPolicyUri());
		o.addProperty("jwks_uri", c.getJwksUri());

		// OIDC Registration
		o.addProperty("application_type", c.getApplicationType() != null ? c.getApplicationType().getValue() : null);
		o.addProperty("sector_identifier_uri", c.getSectorIdentifierUri());
		o.addProperty("subject_type", c.getSubjectType() != null ? c.getSubjectType().getValue() : null);
		o.addProperty("request_object_signing_alg", c.getRequestObjectSigningAlg() != null ? c.getRequestObjectSigningAlg().getAlgorithmName() : null);
		o.addProperty("userinfo_signed_response_alg", c.getUserInfoSignedResponseAlg() != null ? c.getUserInfoSignedResponseAlg().getAlgorithmName() : null);
		o.addProperty("userinfo_encrypted_response_alg", c.getUserInfoEncryptedResponseAlg() != null ? c.getUserInfoEncryptedResponseAlg().getAlgorithmName() : null);
		o.addProperty("userinfo_encrypted_response_enc", c.getUserInfoEncryptedResponseEnc() != null ? c.getUserInfoEncryptedResponseEnc().getAlgorithmName() : null);
		o.addProperty("id_token_signed_response_alg", c.getIdTokenSignedResponseAlg() != null ? c.getIdTokenSignedResponseAlg().getAlgorithmName() : null);
		o.addProperty("id_token_encrypted_response_alg", c.getIdTokenEncryptedResponseAlg() != null ? c.getIdTokenEncryptedResponseAlg().getAlgorithmName() : null);
		o.addProperty("id_token_encrypted_response_enc", c.getIdTokenEncryptedResponseEnc() != null ? c.getIdTokenEncryptedResponseEnc().getAlgorithmName() : null);
		o.addProperty("default_max_age", c.getDefaultMaxAge());
		o.addProperty("require_auth_time", c.getRequireAuthTime());
		o.add("default_acr_values", getAsArray(c.getDefaultACRvalues()));
		o.addProperty("initiate_login_uri", c.getInitiateLoginUri());
		o.addProperty("post_logout_redirect_uri", c.getPostLogoutRedirectUri());
		o.add("request_uris", getAsArray(c.getRequestUris()));
		return o;
	}