    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getAnnotation(IdeaProjectTestFixture fixture) {

        PsiMethod psiMethod1 = getJavaFacade(fixture).findClass("com.app.util.Class1", GlobalSearchScope.allScope(fixture.getProject())).findMethodsByName("method1", false)[0];
        IntellijJavaMethod method1 = new IntellijJavaMethod(fixture.getModule(), psiMethod1);

        assert method1.getAnnotation(null) == null;

        assert method1.getAnnotation("java.lang.SuppressWarnings").getFullyQualifiedName().equals("java.lang.SuppressWarnings");
    }