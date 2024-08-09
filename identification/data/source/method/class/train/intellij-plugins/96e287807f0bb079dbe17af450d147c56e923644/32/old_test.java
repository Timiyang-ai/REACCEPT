    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getName(IdeaProjectTestFixture fixture) throws IncorrectOperationException {
        PsiField psiField = getJavaFacade(fixture).getElementFactory().createField("_fieldName", PsiType.BOOLEAN);

        assert new IntellijJavaField(fixture.getModule(), psiField).getName().equals("_fieldName");

        assert new IntellijJavaField(fixture.getModule(), psiField).getPsiField().getName().equals("_fieldName");
    }