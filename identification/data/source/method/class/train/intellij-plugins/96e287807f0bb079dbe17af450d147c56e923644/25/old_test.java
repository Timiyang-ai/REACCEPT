    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void createField(IdeaProjectTestFixture fixture) {
        IntellijJavaTypeCreator intellijJavaTypeCreator = new IntellijJavaTypeCreator(fixture.getModule());

        IJavaField psiField1 = intellijJavaTypeCreator.createField("field1",
                new IntellijJavaClassType(fixture.getModule(), getJavaFacade(fixture).findClass("java.lang.String", getAllScope(fixture)).getContainingFile()), true, true);
        assert psiField1.getName().equals("field1");
        assert ((IJavaClassType) psiField1.getType()).getFullyQualifiedName().equals("java.lang.String");
        assert psiField1.isPrivate();

        IJavaField psiField2 = intellijJavaTypeCreator.createField("Field1",
                new IntellijJavaClassType(fixture.getModule(), getJavaFacade(fixture).findClass("java.lang.String", getAllScope(fixture)).getContainingFile()), true, true);
        assert psiField2.getName().equals("field1");

        IJavaField psiField3 = intellijJavaTypeCreator.createField("Field1",
                new IntellijJavaClassType(fixture.getModule(), getJavaFacade(fixture).findClass("java.lang.String", getAllScope(fixture)).getContainingFile()), true, false);
        assert psiField3.getName().equals("Field1");

        IJavaField psiField4 = intellijJavaTypeCreator.createField("field1",
                new IntellijJavaClassType(fixture.getModule(), getJavaFacade(fixture).findClass("java.lang.String", getAllScope(fixture)).getContainingFile()), false, true);
        assert !psiField4.isPrivate();
    }