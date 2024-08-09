    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void getStringRepresentation(IdeaProjectTestFixture fixture) throws IncorrectOperationException {
        assert new IntellijJavaField(fixture.getModule(), getJavaFacade(fixture).getElementFactory().createField("_fieldName", PsiType.BOOLEAN)).getStringRepresentation()
                .equals("private boolean _fieldName;");
    }