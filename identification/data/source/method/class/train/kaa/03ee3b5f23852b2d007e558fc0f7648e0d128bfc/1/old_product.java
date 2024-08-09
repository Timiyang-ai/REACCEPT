public List<TenantUserDto> getTenants() throws Exception {
        ParameterizedTypeReference<List<TenantUserDto>> typeRef = new ParameterizedTypeReference<List<TenantUserDto>>() {
        };
        ResponseEntity<List<TenantUserDto>> entity = restTemplate.exchange(restTemplate.getUrl() + "tenants", HttpMethod.GET, null, typeRef);
        return entity.getBody();
    }