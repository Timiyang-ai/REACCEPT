  @Test
  public void allocateBlock() throws Exception {
    ServerConfiguration.set(PropertyKey.WORKER_ALLOCATOR_CLASS, MaxFreeAllocator.class.getName());
    mAllocator = Allocator.Factory.create(getMetadataEvictorView());
    //
    // idx | tier1 | tier2 | tier3
    //  0    1000
    //  0      ├───── 2000
    //  1      └───── 2000
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 1500, true, "SSD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    1000
    //  0      ├───── 500   <--- alloc
    //  1      └───── 2000
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 2000, true, "SSD", 1);
    //
    // idx | tier1 | tier2 | tier3
    //  0    1000
    //  0      ├───── 500
    //  1      └───── 0   <--- alloc
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 300, true, "MEM", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0     700   <--- alloc
    //  0      ├───── 500
    //  1      └───── 0
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc2, 300, true, "SSD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0     700
    //  0      ├───── 200   <--- alloc
    //  1      └───── 0
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    ServerConfiguration.reset();
  }