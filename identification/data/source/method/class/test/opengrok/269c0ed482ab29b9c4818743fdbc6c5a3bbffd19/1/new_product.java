public List<String> getProjectNames() {
        return getProjectList().stream().
            map(Project::getName).collect(Collectors.toList());
    }