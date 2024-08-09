public void setApplications(final Set<Application> applications) {
        //Clear references to this command in existing applications
        if (this.applications != null) {
            this.applications.stream()
                    .filter(application -> application.getCommands() != null)
                    .forEach(application -> application.getCommands().remove(this));
        }
        //set the application for this command
        if (this.applications == null) {
            this.applications = new HashSet<>();
        }
        this.applications.clear();
        if (applications != null) {
            this.applications.addAll(applications);
        }

        //Add the reverse reference in the new applications
        this.applications.stream()
                .forEach(
                        application -> {
                            if (application.getCommands() == null) {
                                application.setCommands(new HashSet<>());
                            }
                            application.getCommands().add(this);
                        }
                );
    }