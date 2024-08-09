    @Test
    public void findVariantIDsForDeveloper() {
        assertThat(variantDao.findVariantIDsForDeveloper("admin")).isNotNull();
        assertThat(variantDao.findVariantIDsForDeveloper("admin")).containsOnly("1");
    }