    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getFullyQualifiedName(IdeaProjectTestFixture fixture) {

      IntellijJavaAnnotation annotation = new IntellijJavaAnnotation(
                getJavaFacade(fixture).findClass(
                        "com.app.util.Class1", GlobalSearchScope.moduleRuntimeScope(fixture.getModule(), false)
                ).getModifierList().getAnnotations()[0]
        );

        assert annotation.getFullyQualifiedName().equals("java.lang.Deprecated");
    }