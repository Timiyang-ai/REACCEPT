protected GrantedAuthoritiesMapper getAuthoritiesMapper() throws Exception {
		if(authoritiesMapper != null) {
			return authoritiesMapper;
		}

		SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
		simpleAuthorityMapper.setPrefix(this.rolePrefix.getRolePrefix());
		simpleAuthorityMapper.afterPropertiesSet();
		this.authoritiesMapper = simpleAuthorityMapper;
		return simpleAuthorityMapper;
	}