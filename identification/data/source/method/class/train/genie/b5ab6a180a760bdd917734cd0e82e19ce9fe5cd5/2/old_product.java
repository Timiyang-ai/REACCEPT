public void setApplications(final List<ApplicationEntity> applications) throws GeniePreconditionException {
        if (applications != null
            && applications.stream().map(ApplicationEntity::getId).distinct().count() != applications.size()) {
            throw new GeniePreconditionException("List of applications to set cannot contain duplicates");
        }

        //Clear references to this command in existing applications
        for (final ApplicationEntity application : this.applications) {
            application.getCommands().remove(this);
        }
        this.applications.clear();

        if (applications != null) {
            //set the application for this command
            this.applications.addAll(applications);

            //Add the reverse reference in the new applications
            for (final ApplicationEntity application : this.applications) {
                application.getCommands().add(this);
            }
        }
    }