@Override
    public String getPatch(String rev) throws GitAPIException, MissingObjectException,
            IncorrectObjectTypeException, IOException {
        // Get the trees, from current commit and its parent, as treeWalk.
        ObjectId commitId = repository.resolve(rev);
        TreeWalk treeWalk = new TreeWalk(repository);
        RevWalk revWalk = new RevWalk(repository);
        RevCommit commit = revWalk.parseCommit(commitId);
        if (commit.getParentCount() > 0) {
            RevTree tree = revWalk.parseCommit(commit.getParent(0).getId()).getTree();
            treeWalk.addTree(tree);
        } else {
            treeWalk.addTree(new EmptyTreeIterator());
        }
        treeWalk.addTree(commit.getTree());

        // Render the difference from treeWalk which has two trees.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DiffFormatter diffFormatter = new DiffFormatter(out);
        diffFormatter.setRepository(repository);
        diffFormatter.format(DiffEntry.scan(treeWalk));

        return out.toString("UTF-8");
    }