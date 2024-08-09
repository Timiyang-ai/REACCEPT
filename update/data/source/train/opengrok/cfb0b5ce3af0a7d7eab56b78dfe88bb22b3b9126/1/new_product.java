public List<String> getProjectDescriptions() {
        return getProjectList().stream().
            map(Project::getName).collect(Collectors.toList());
    }