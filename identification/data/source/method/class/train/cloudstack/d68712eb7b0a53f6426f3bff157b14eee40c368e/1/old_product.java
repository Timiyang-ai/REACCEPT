protected void validateAndUpdatApiAndSecretKeyIfNeeded(UpdateUserCmd updateUserCmd, UserVO user) {
        String apiKey = updateUserCmd.getApiKey();
        String secretKey = updateUserCmd.getSecretKey();

        boolean isApiKeyBlank = StringUtils.isBlank(apiKey);
        boolean isSecretKeyBlank = StringUtils.isBlank(secretKey);
        if (isApiKeyBlank ^ isSecretKeyBlank) {
            throw new InvalidParameterValueException("Please provide a userApiKey/userSecretKey pair");
        }
        if (isApiKeyBlank && isSecretKeyBlank) {
            return;
        }
        Pair<User, Account> apiKeyOwner = _accountDao.findUserAccountByApiKey(apiKey);
        if (apiKeyOwner != null) {
            User userThatHasTheProvidedApiKey = apiKeyOwner.first();
            if (userThatHasTheProvidedApiKey.getId() != user.getId()) {
                throw new InvalidParameterValueException(String.format("The API key [%s] already exists in the system. Please provide a unique key.", apiKey));
            }
        }
        user.setApiKey(apiKey);
        user.setSecretKey(secretKey);
    }