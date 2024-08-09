    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void isPrivate(IdeaProjectTestFixture fixture) throws IncorrectOperationException {
        IntellijJavaField field1 = new IntellijJavaField(
                fixture.getModule(),
                getJavaFacade(fixture).findClass("com.app.util.Class1", GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(fixture.getModule())).getFields()[0]
        );

        IntellijJavaField field2 = new IntellijJavaField(
                fixture.getModule(),
                getJavaFacade(fixture).findClass("com.app.util.Class1", GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(fixture.getModule())).getFields()[1]
        );

        assert field1.isPrivate();

        assert !field2.isPrivate();
    }