public static List<Project> isOnlyManager(Long userId) {
        List<Project> projects = find
                                    .select("id")
                                    .select("name")
                                    .where()
                                        .eq("projectUser.user.id", userId)
                                        .eq("projectUser.role.id", RoleType.MANAGER.roleType())
                                    .findList();
        synchronized (java.lang.Object.class) {
        Iterator<Project> iterator = projects.iterator();
        while(iterator.hasNext()){
            Project project = iterator.next();
            if(ProjectUser.checkOneMangerPerOneProject(project.id)) {
                iterator.remove();
            }
        }
        }
        
        return projects;
    }