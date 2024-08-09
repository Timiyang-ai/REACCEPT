    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getFields(IdeaProjectTestFixture fixture) {

        PsiClass class1 = getJavaFacade(fixture).findClass("com.app.util.Class1", GlobalSearchScope.moduleRuntimeScope(fixture.getModule(), false));
        IntellijJavaClassType intellijJavaClassType = new IntellijJavaClassType(fixture.getModule(), class1.getContainingFile());

        assert intellijJavaClassType.getFields(true).size() == 4;

        assert intellijJavaClassType.getFields(false).size() == 3;

        IntellijResourceFinder resourceFinder = new IntellijResourceFinder(fixture.getModule());
        _notJavaClassType = new IntellijJavaClassType(fixture.getModule(), ((IntellijResource) resourceFinder.findClasspathResource("/com/app/util/Home.tml", false).toArray()[0]).getPsiFile());

        assert _notJavaClassType.getFields(true).size() == 0;
    }