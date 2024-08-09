public List<String> getProjectDescriptions() {
        return threadConfig.get().getProjects().stream().
            map(Project::getName).collect(Collectors.toList());
    }