    @Test
    public void isAvailable_systemUser() {
        mShadowUserManager.setIsAdminUser(true);

        assertThat(mController.isAvailable()).isTrue();
    }