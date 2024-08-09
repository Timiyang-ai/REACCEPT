public List<AefMapInfoDto> getEventClassFamiliesByApplicationToken(String applicationToken) throws Exception {
        ParameterizedTypeReference<List<AefMapInfoDto>> typeRef = new ParameterizedTypeReference<List<AefMapInfoDto>>() {
        };
        ResponseEntity<List<AefMapInfoDto>> entity = restTemplate.exchange(restTemplate.getUrl() + "eventClassFamiliesByAppToken/" + applicationToken,
                HttpMethod.GET, null, typeRef);
        return entity.getBody();
    }