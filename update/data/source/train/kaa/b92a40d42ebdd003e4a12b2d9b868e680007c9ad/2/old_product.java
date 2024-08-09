public List<EcfInfoDto> getVacantEventClassFamiliesByApplicationToken(String applicationToken) throws Exception {
        ParameterizedTypeReference<List<EcfInfoDto>> typeRef = new ParameterizedTypeReference<List<EcfInfoDto>>() {
        };
        ResponseEntity<List<EcfInfoDto>> entity = restTemplate.exchange(restTemplate.getUrl() + "vacantEventClassFamiliesByAppToken/" + applicationToken,
                HttpMethod.GET, null, typeRef);
        return entity.getBody();
    }