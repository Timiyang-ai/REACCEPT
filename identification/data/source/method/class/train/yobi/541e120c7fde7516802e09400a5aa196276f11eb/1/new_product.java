@Transactional
    @With(AnonymousCheckAction.class)
    @IsAllowed(Operation.DELETE)
    public static Result deletePushedBranch(String ownerId, String projectName, Long id) {
        PushedBranch pushedBranch = PushedBranch.find.byId(id);
        if (pushedBranch != null) {
            pushedBranch.delete();
        }
        return ok();
    }