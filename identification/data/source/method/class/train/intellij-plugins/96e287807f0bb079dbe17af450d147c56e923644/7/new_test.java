    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getContainingClass(IdeaProjectTestFixture fixture) {

        PsiMethod psiMethod1 = getJavaFacade(fixture).findClass("com.app.util.Class1", GlobalSearchScope.allScope(fixture.getProject())).findMethodsByName("method1", false)[0];
        IntellijJavaMethod method1 = new IntellijJavaMethod(fixture.getModule(), psiMethod1);

        assert method1.getContainingClass().getName().equals("Class1");
    }