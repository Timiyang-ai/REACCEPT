    @Test
    public void validateAndUpdatApiAndSecretKeyIfNeededTestNoKeys() {
        accountManagerImpl.validateAndUpdateApiAndSecretKeyIfNeeded(UpdateUserCmdMock, userVoMock);

        Mockito.verify(_accountDao, Mockito.times(0)).findUserAccountByApiKey(Mockito.anyString());
    }