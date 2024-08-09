public static boolean isAllowed(Object userSessionId, Long projectId, Resource resource,
            Operation operation, Long resourceId) {
        Long userId=0l;
        if(userSessionId instanceof String) {
        	if(StringUtils.isNumber(userSessionId.toString())) {
        		userId = Long.parseLong((String) userSessionId);
        	}
        } else {
            userId = (Long) userSessionId;
        }
        
        // Site administrator is all-mighty.
        /*
        if (Permission.hasPermission(userId, 0l, Resource.SITE_SETTING, Operation.WRITE)) {
            return true;
        }
        */
        
        boolean isAuthorEditible;

        switch (resource)
            {
            case ISSUE_POST:
                isAuthorEditible = isAuthor(userId, resourceId, new Finder<Long, Issue>(
                        Long.class, Issue.class))
                        && Project.findById(projectId).isAuthorEditable;
                break;
            case ISSUE_COMMENT:
                isAuthorEditible = isAuthor(userId, resourceId, new Finder<Long, IssueComment>(
                        Long.class, IssueComment.class));
                break;
            case BOARD_POST:
                isAuthorEditible = isAuthor(userId, resourceId, new Finder<Long, Post>(
                        Long.class, Post.class));
                break;
            case BOARD_COMMENT:
                isAuthorEditible = isAuthor(userId, resourceId, new Finder<Long, Comment>(
                        Long.class, Comment.class));
                break;
            case USER:
                return userId == resourceId;
            case ISSUE_LABEL:
                return ProjectUser.isMember(userId, projectId);
            case PROJECT:
                return Project.findById(projectId).share_option || ProjectUser.isMember(userId, projectId);
            default:
                isAuthorEditible = false;
                break;
            }
        if (ProjectUser.isMember(userId, projectId)) {
            return isAuthorEditible
                    || Permission.hasPermission(userId, projectId, resource, operation);
        } else { // Anonymous
            if (!Project.findById(projectId).share_option) {
                return false;
            }
            return isAuthorEditible
                    || Permission.hasPermission(RoleType.ANONYMOUS, resource, operation);
      }
    }