    @Test(dataProvider = JAVA_MODULE_FIXTURE_PROVIDER)
    public void isModuleNode(IdeaProjectTestFixture fixture) {
        MapDataContext dataContext = new MapDataContext();
        dataContext.put(CommonDataKeys.PROJECT.getName(), fixture.getProject());
        dataContext.put(LangDataKeys.MODULE_CONTEXT.getName(), fixture.getModule());

        AnActionEvent actionEvent = new AnActionEvent(null, dataContext, "", new Presentation(), ActionManager.getInstance(), 0);
        assert IdeaUtils.isModuleNode(actionEvent);


      dataContext.put(CommonDataKeys.PROJECT.getName(), null);
      dataContext.put(LangDataKeys.MODULE_CONTEXT.getName(), null);

        actionEvent = new AnActionEvent(null, dataContext, "", new Presentation(), ActionManager.getInstance(), 0);
        assert !IdeaUtils.isModuleNode(actionEvent);
    }