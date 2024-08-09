    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void isInterface_true(IdeaProjectTestFixture fixture) {
        PsiClass interface1 = getJavaFacade(fixture).findClass("com.app.util.Interface1", GlobalSearchScope.moduleRuntimeScope(fixture.getModule(), false));
        IntellijJavaClassType intellijJavaClassType = new IntellijJavaClassType(fixture.getModule(), interface1.getContainingFile());

        assert intellijJavaClassType.isInterface();
    }