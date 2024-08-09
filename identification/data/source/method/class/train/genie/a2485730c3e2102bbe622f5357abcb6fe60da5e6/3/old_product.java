public void setApplications(final Set<ApplicationEntity> applications) {
        //Clear references to this command in existing applications
        for (final ApplicationEntity application : this.applications) {
            application.getCommands().remove(this);
        }
        //set the application for this command
        this.applications.clear();
        if (applications != null) {
            this.applications.addAll(applications);
        }

        //Add the reverse reference in the new applications
        for (final ApplicationEntity application : this.applications) {
            application.getCommands().add(this);
        }
    }