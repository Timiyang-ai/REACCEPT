@Transactional
    public static Result newProject() throws GitAPIException, IOException {
        if(!AccessControl.isCreatable(UserApp.currentUser())){
            return forbidden(ErrorViews.Forbidden.render("'" + UserApp.currentUser().name + "' has no permission"));
        }
        Form<Project> filledNewProjectForm = form(Project.class).bindFromRequest();
        String owner = filledNewProjectForm.field("owner").value();
        Organization organization = Organization.findByName(owner);
        User user = User.findByLoginId(owner);

        ValidationResult result = validateForm(filledNewProjectForm, organization, user);
        if (result.hasError()) {
            return result.getResult();
        }

        String gitUrl = filledNewProjectForm.data().get("url");
        Project project = filledNewProjectForm.get();

        if (Organization.isNameExist(owner)) {
            project.organization = organization;
        }
        String errorMessageKey = null;
        try {
            GitRepository.cloneRepository(gitUrl, project);
            Long projectId = Project.create(project);

            if (User.isLoginIdExist(owner)) {
                ProjectUser.assignRole(UserApp.currentUser().id, projectId, RoleType.MANAGER);
            }
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
            List<OrganizationUser> orgUserList = OrganizationUser.findByAdmin(UserApp.currentUser().id);
            filledNewProjectForm.reject("url", errorMessageKey);
            FileUtil.rm_rf(new File(GitRepository.getGitDirectory(project)));
            return badRequest(importing.render("title.newProject", filledNewProjectForm, orgUserList));
        } else {
            return redirect(routes.ProjectApp.project(project.owner, project.name));
        }
    }