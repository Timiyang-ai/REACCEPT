public void deleteTenant(String userId) throws Exception {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userId", userId);
        restTemplate.postForLocation(restTemplate.getUrl() + "delTenant", params);
    }