@Override
    public ObjectNode getMetaDataFromPath(String branch, String path) throws IOException, GitAPIException {
        ObjectId headCommit;

        if (branch == null){
            headCommit = repository.resolve(Constants.HEAD);
        } else {
            headCommit = repository.resolve(branch);
        }
        // 만약 특정 커밋을 얻오오고싶다면 바꾸어 주면 된다.
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
            return fileAsJson(treeWalk, headCommit);
        }
    }