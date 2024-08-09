public static List<Project> isOnlyManager(Long userId) {
        List<Project> projects = find
                                    .select("id")
                                    .select("name")
                                    .where()
                                        .eq("projectUser.user.id", userId)
                                        .eq("projectUser.role.id", Role.MANAGER)
                                    .findList();
        
        Iterator<Project> iterator = projects.iterator();
        while(iterator.hasNext()){
            Project project = iterator.next();
            if(ProjectUser.isManager(project.id))
                projects.remove(project);
        }
        
        return projects;
    }