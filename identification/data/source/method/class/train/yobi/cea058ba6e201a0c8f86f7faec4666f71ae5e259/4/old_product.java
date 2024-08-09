public static boolean isAllowed(Object userSessionId, Long projectId, Resource resource,
            Operation operation, Long resourceId) {
        Long userId;
        if(userSessionId instanceof String) {
            userId = Long.parseLong((String) userSessionId);
        } else {
            userId = (Long) userSessionId;
        }
        
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