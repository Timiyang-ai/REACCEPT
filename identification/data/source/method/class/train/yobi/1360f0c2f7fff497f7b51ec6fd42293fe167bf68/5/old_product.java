@Transactional
    public static Result newProject() throws GitAPIException, IOException {
        if( !AccessControl.isCreatable(UserApp.currentUser()) ){
            return forbidden("'" + UserApp.currentUser().name + "' has no permission");
        }

        Form<Project> filledNewProjectForm = form(Project.class).bindFromRequest();

        String gitUrl = filledNewProjectForm.data().get("url");
        if(gitUrl == null || gitUrl.trim().isEmpty()) {
            flash(Constants.WARNING, "project.import.error.empty.url");
            return badRequest(importing.render("title.newProject", filledNewProjectForm));
        }

        if (Project.exists(UserApp.currentUser().loginId, filledNewProjectForm.field("name").value())) {
            flash(Constants.WARNING, "project.name.duplicate");
            filledNewProjectForm.reject("name");
            return badRequest(importing.render("title.newProject", filledNewProjectForm));
        }

        if (filledNewProjectForm.hasErrors()) {
            filledNewProjectForm.reject("name");
            flash(Constants.WARNING, "project.name.alert");
            return badRequest(importing.render("title.newProject", filledNewProjectForm));
        }

        Project project = filledNewProjectForm.get();
        project.owner = UserApp.currentUser().loginId;
        String errorMessageKey = null;
        try {
            GitRepository.cloneRepository(gitUrl, project);
            Long projectId = Project.create(project);
            ProjectUser.assignRole(UserApp.currentUser().id, projectId, RoleType.MANAGER);
        } catch (InvalidRemoteException e) {
            // It is not an url.
            errorMessageKey = "project.import.error.wrong.url";
        } catch (JGitInternalException e) {
            // The url seems that does not locate a git repository.
            errorMessageKey = "project.import.error.wrong.url";
        } catch (TransportException e) {
            errorMessageKey = "project.import.error.transport";
        }

        if (errorMessageKey != null) {
            flash(Constants.WARNING, errorMessageKey);
            FileUtil.rm_rf(new File(GitRepository.getGitDirectory(project)));
            return badRequest(importing.render("title.newProject", filledNewProjectForm));
        } else {
            return redirect(routes.ProjectApp.project(project.owner, project.name));
        }
    }