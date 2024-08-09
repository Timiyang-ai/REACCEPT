    @Test
    public void isCompactionPending(){
        //set with no shard

        final Shard minShard = Shard.MIN_SHARD;

        final ShardEntryGroup oneShardGroup = new ShardEntryGroup( 1 );

        oneShardGroup.addShard( minShard );

        assertFalse(oneShardGroup.isCompactionPending());


        //now test with 2 shards, 1 marked as non compacted.

        final Shard nextShard = new Shard( 1000, 1000, false );

        final ShardEntryGroup pendingGroup = new ShardEntryGroup( 1 );

        pendingGroup.addShard( nextShard );
        pendingGroup.addShard( minShard );

        assertTrue(pendingGroup.isCompactionPending());



    }