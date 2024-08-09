    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getType_primitive(IdeaProjectTestFixture fixture) throws IncorrectOperationException {
        PsiField psiField = getJavaFacade(fixture).getElementFactory().createField("_fieldName", PsiType.BOOLEAN);

        assert new IntellijJavaField(fixture.getModule(), psiField).getType() instanceof IJavaPrimitiveType;

        assert new IntellijJavaField(fixture.getModule(), psiField).getType().getName().equals("boolean");
    }