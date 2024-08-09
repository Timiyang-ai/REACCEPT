public static ClientDetailsEntity parse(String jsonString) {
		JsonElement jsonEl = parser.parse(jsonString);
		if (jsonEl.isJsonObject()) {

			JsonObject o = jsonEl.getAsJsonObject();
			ClientDetailsEntity c = new ClientDetailsEntity();

			// TODO: make these field names into constants

			// these two fields should only be sent in the update request, and MUST match existing values
			c.setClientId(getAsString(o, "client_id"));
			c.setClientSecret(getAsString(o, "client_secret"));

			// OAuth DynReg
			c.setRedirectUris(getAsStringSet(o, "redirect_uris"));
			c.setClientName(getAsString(o, "client_name"));
			c.setClientUri(getAsString(o, "client_uri"));
			c.setLogoUri(getAsString(o, "logo_uri"));
			c.setContacts(getAsStringSet(o, "contacts"));
			c.setTosUri(getAsString(o, "tos_uri"));

			String authMethod = getAsString(o, "token_endpoint_auth_method");
			if (authMethod != null) {
				c.setTokenEndpointAuthMethod(AuthMethod.getByValue(authMethod));
			}

			// scope is a space-separated string
			String scope = getAsString(o, "scope");
			if (scope != null) {
				c.setScope(Sets.newHashSet(Splitter.on(" ").split(scope)));
			}

			c.setGrantTypes(getAsStringSet(o, "grant_types"));
			c.setPolicyUri(getAsString(o, "policy_uri"));
			c.setJwksUri(getAsString(o, "jwks_uri"));


			// OIDC Additions
			String appType = getAsString(o, "application_type");
			if (appType != null) {
				c.setApplicationType(AppType.getByValue(appType));
			}

			c.setSectorIdentifierUri(getAsString(o, "sector_identifier_uri"));

			String subjectType = getAsString(o, "subject_type");
			if (subjectType != null) {
				c.setSubjectType(SubjectType.getByValue(subjectType));
			}

			c.setRequestObjectSigningAlg(getAsJwsAlgorithm(o, "request_object_signing_alg"));

			c.setUserInfoSignedResponseAlg(getAsJwsAlgorithm(o, "userinfo_signed_response_alg"));
			c.setUserInfoEncryptedResponseAlg(getAsJweAlgorithm(o, "userinfo_encrypted_response_alg"));
			c.setUserInfoEncryptedResponseEnc(getAsJweEncryptionMethod(o, "userinfo_encrypted_response_enc"));

			c.setIdTokenSignedResponseAlg(getAsJwsAlgorithm(o, "id_token_signed_response_alg"));
			c.setIdTokenEncryptedResponseAlg(getAsJweAlgorithm(o, "id_token_encrypted_response_alg"));
			c.setIdTokenEncryptedResponseEnc(getAsJweEncryptionMethod(o, "id_token_encrypted_response_enc"));

			if (o.has("default_max_age")) {
				if (o.get("default_max_age").isJsonPrimitive()) {
					c.setDefaultMaxAge(o.get("default_max_age").getAsInt());
				}
			}

			if (o.has("require_auth_time")) {
				if (o.get("require_auth_time").isJsonPrimitive()) {
					c.setRequireAuthTime(o.get("require_auth_time").getAsBoolean());
				}
			}

			c.setDefaultACRvalues(getAsStringSet(o, "default_acr_values"));
			c.setInitiateLoginUri(getAsString(o, "initiate_login_uri"));
			c.setPostLogoutRedirectUri(getAsString(o, "post_logout_redirect_uri"));
			c.setRequestUris(getAsStringSet(o, "request_uris"));

			return c;
		} else {
			return null;
		}
	}