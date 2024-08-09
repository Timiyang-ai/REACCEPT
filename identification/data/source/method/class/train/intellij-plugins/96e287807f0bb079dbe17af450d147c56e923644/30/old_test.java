    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getFullyQualifiedName(IdeaProjectTestFixture fixture) {
        PsiClass class1 = getJavaFacade(fixture).findClass("com.app.util.Class1", GlobalSearchScope.moduleRuntimeScope(fixture.getModule(), false));
        IntellijJavaClassType intellijJavaClassType = new IntellijJavaClassType(fixture.getModule(), class1.getContainingFile());

        assert intellijJavaClassType.getFullyQualifiedName().equals("com.app.util.Class1");

        assert intellijJavaClassType.getPsiClass().getQualifiedName().equals("com.app.util.Class1");
    }