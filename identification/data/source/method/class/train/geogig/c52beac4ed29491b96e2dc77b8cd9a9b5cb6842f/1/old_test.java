    private Map<String, Ref> createTestRefs() {
        Map<String, Ref> refs = new TreeMap<>();

        // known root refs
        putRef(Ref.CHERRY_PICK_HEAD, sampleId, refs);
        putRef(Ref.ORIG_HEAD, sampleId, refs);
        putRef(Ref.MASTER, sampleId, refs);
        putSymRef(Ref.HEAD, "refs/heads/master", refs);
        putRef(Ref.WORK_HEAD, sampleId, refs);
        putRef(Ref.STAGE_HEAD, sampleId, refs);
        putRef(Ref.MERGE_HEAD, sampleId, refs);

        // some heads
        String branch1 = Ref.append(Ref.HEADS_PREFIX, "branch1");
        String tag1 = Ref.append(Ref.TAGS_PREFIX, "tag1");
        String remoteBranch1 = Ref.append(Ref.append(Ref.REMOTES_PREFIX, "r1"), "master");

        putRef(branch1, id("branch1"), refs);
        putRef(tag1, id("tag1"), refs);
        putRef(remoteBranch1, id("r1master"), refs);

        // some refs in a transaction namespace
        String txNamespace1 = Ref.append(Ref.TRANSACTIONS_PREFIX, "txnamespace1");
        String txNamespace2 = Ref.append(Ref.TRANSACTIONS_PREFIX, "txnamespace2");

        String tx1Head = Ref.append(txNamespace1, Ref.HEAD);
        String tx1Master = Ref.append(txNamespace1, Ref.MASTER);
        putRef(tx1Head, id("tx1Head"), refs);
        putRef(tx1Master, id("tx1Master"), refs);

        String tx2Head = Ref.append(txNamespace2, Ref.HEAD);
        String tx2Master = Ref.append(txNamespace2, Ref.MASTER);
        putRef(tx2Head, id("tx2Head"), refs);
        putRef(tx2Master, id("tx2Master"), refs);
        return refs;
    }