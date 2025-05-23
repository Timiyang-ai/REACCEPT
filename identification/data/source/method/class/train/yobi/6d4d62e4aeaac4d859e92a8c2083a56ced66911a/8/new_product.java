public static Result detachLabel(String ownerName, String projectName, Long id) {
        Project project = Project.findByOwnerAndProjectName(ownerName, projectName);

        if (project == null) {
            return notFound(Views.NotFound.render("error.notfound"));
        }

        if (!AccessControl.isAllowed(UserApp.currentUser(), project.labelsAsResource(), Operation.UPDATE)) {
            return forbidden(Views.Forbidden.render("error.forbidden", project));
        }

        // _method must be 'delete'
        Map<String, String[]> data = request().body().asFormUrlEncoded();
        if (!HttpUtil.getFirstValueFromQuery(data, "_method").toLowerCase()
                .equals("delete")) {
            return badRequest(Views.BadRequest.render("_method must be 'delete'.", project));
        }

        Label tag = Label.find.byId(id);

        if (tag == null) {
            return notFound(Views.NotFound.render("error.notfound"));
        }

        project.detachLabel(tag);

        return status(Http.Status.NO_CONTENT);
    }