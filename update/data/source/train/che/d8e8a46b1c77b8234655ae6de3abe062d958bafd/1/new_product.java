@Override
    public void go(AcceptsOneWidget container) {

        if (Strings.isNullOrEmpty(dataObject.getName()) && Strings.isNullOrEmpty(dataObject.getSource().getLocation())) {
            ignoreChanges = true;

            view.setProjectUrlErrorHighlight(false);
            view.setProjectNameErrorHighlight(false);
            view.setURLErrorMessage(null);
        }

        view.setProjectName(dataObject.getName());
        view.setProjectDescription(dataObject.getDescription());
        view.setProjectUrl(dataObject.getSource().getLocation());

        container.setWidget(view);

        view.setInputsEnableState(true);

        ignoreChanges = false;
    }