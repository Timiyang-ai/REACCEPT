    private byte[] getRawFile(Repository repository, String path) throws IOException {
        RevTree tree = new RevWalk(repository).parseTree(repository.resolve(Constants.HEAD));
        TreeWalk treeWalk = TreeWalk.forPath(repository, path, tree);
        if (treeWalk.isSubtree()) {
            return null;
        } else {
            return repository.open(treeWalk.getObjectId(0)).getBytes();
        }
    }