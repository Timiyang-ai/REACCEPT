@Transactional
    public static Result newProject() {
        if( !AccessControl.isCreatable(UserApp.currentUser(), ResourceType.PROJECT) ){
            return forbidden("'" + UserApp.currentUser().name + "' has no permission");
        }

        Form<Project> filledNewProjectForm = form(Project.class).bindFromRequest();

        String gitUrl = filledNewProjectForm.data().get("url");
        if(gitUrl == null || gitUrl.trim().isEmpty()) {
            flash(Constants.WARNING, "import.error.empty.url");
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
        try {
            GitRepository.cloneRepository(gitUrl, project);
            Long projectId = Project.create(project);
            ProjectUser.assignRole(UserApp.currentUser().id, projectId, RoleType.MANAGER);
        } catch (Exception e) {
            flash(Constants.WARNING, "import.error.wrong.url");
            FileUtil.rm_rf(new File(GitRepository.getGitDirectory(project)));
            return badRequest(importing.render("title.newProject", filledNewProjectForm));
        }

        return redirect(routes.ProjectApp.project(project.owner, project.name));
    }