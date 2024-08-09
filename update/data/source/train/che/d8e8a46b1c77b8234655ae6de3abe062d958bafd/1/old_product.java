@Override
    public void go(AcceptsOneWidget container) {

        view.setProjectName(dataObject.getName());
        view.setProjectDescription(dataObject.getDescription());
        view.setProjectUrl(dataObject.getSource().getLocation());

        container.setWidget(view);

        view.setUrlTextBoxFocused();
    }