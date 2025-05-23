@Override
    public ObjectNode getMetaDataFromPath(String branch, String path) throws IOException, GitAPIException {
        RevCommit headCommit = getRevCommit(branch);
        if (headCommit == null) {
            Logger.debug("GitRepository : init Project - No Head commit");
            return null;
        }

        RevWalk revWalk = new RevWalk(repository);
        RevTree revTree = revWalk.parseTree(headCommit);
        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(revTree);

        if (path.isEmpty()) {
            return treeAsJson(path, treeWalk, headCommit);
        }

        PathFilter pathFilter = PathFilter.create(path);
        treeWalk.setFilter(pathFilter);
        while (treeWalk.next()) {
            if (pathFilter.isDone(treeWalk)) {
                break;
            } else if (treeWalk.isSubtree()) {
                treeWalk.enterSubtree();
            }
        }

        if (treeWalk.isSubtree()) {
            treeWalk.enterSubtree();
            return treeAsJson(path, treeWalk, headCommit);
        } else {
            try {
                return fileAsJson(treeWalk, headCommit);
            } catch (MissingObjectException e) {
                Logger.debug("Unavailable access. " + branch + "/" + path + " does not exist.");
                return null;
            }
        }
    }