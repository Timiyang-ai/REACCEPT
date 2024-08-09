@Transactional
    @With(AnonymousCheckAction.class)
    @IsAllowed(Operation.DELETE)
    public static Result deletePushedBranch(String ownerName, String projectName, Long id) {
        PushedBranch pushedBranch = PushedBranch.find.byId(id);
        if (pushedBranch != null) {
            pushedBranch.delete();
        }
        return ok();
    }