    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void isValid(IdeaProjectTestFixture fixture) {
        assert new IntellijJavaField(fixture.getModule(), new PsiFieldMock().setValid(true)).isValid();

        assert !new IntellijJavaField(fixture.getModule(), new PsiFieldMock().setValid(false)).isValid();
    }