@Test
  public void allocateBlock() throws Exception {
    ServerConfiguration.set(PropertyKey.WORKER_ALLOCATOR_CLASS,
        RoundRobinAllocator.class.getName());
    mAllocator = Allocator.Factory.create(getManagerView());
    //
    // idx | tier1 | tier2 | tier3
    //  0    1000
    //  0      ├───── 2000
    //  1      └───── 2000
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 500, true, "MEM", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500   <--- alloc
    //  0      ├───── 2000
    //  1      └───── 2000
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 600, true, "SSD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500
    //  0      ├───── 1400   <--- alloc
    //  1      └───── 2000
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc2, 700, true, "SSD", 1);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500
    //  0      ├───── 1400
    //  1      └───── 1300   <--- alloc
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 700, true, "SSD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500
    //  0      ├───── 700   <--- alloc
    //  1      └───── 1300
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 1000, true, "SSD", 1);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500
    //  0      ├───── 700
    //  1      └───── 300   <--- alloc
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 700, true, "SSD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500
    //  0      ├───── 0   <--- alloc
    //  1      └───── 300
    //  0               ├─── 3000
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyTierLoc, 700, true, "HDD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    500
    //  0      ├───── 0
    //  1      └───── 300
    //  0               ├─── 2300   <--- alloc
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc1, 200, true, "MEM", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    300   <--- alloc
    //  0      ├───── 0
    //  1      └───── 300
    //  0               ├─── 2300
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc1, 100, true, "MEM", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200   <--- alloc
    //  0      ├───── 0
    //  1      └───── 300
    //  0               ├─── 2300
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc1, 700, false, "", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 300
    //  0               ├─── 2300
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc2, 100, true, "SSD", 1);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 200   <--- alloc
    //  0               ├─── 2300
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc2, 100, true, "SSD", 1);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100   <--- alloc
    //  0               ├─── 2300
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc2, 1500, false, "", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100
    //  0               ├─── 2300
    //  1               ├─── 3000
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc3, 2000, true, "HDD", 1);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100
    //  0               ├─── 2300
    //  1               ├─── 1000   <--- alloc
    //  2               └─── 3000
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc3, 3000, true, "HDD", 2);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100
    //  0               ├─── 2300
    //  1               ├─── 1000
    //  2               └─── 0   <--- alloc
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc3, 500, true, "HDD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100
    //  0               ├─── 1800   <--- alloc
    //  1               ├─── 1000
    //  2               └─── 0
    //
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc3, 2000, false, "HDD", 0);
    //
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100
    //  0               ├─── 1800
    //  1               ├─── 1000
    //  2               └─── 0
    //
    // tier 3, dir 0, remain 0
    assertTempBlockMeta(mAllocator, mAnyDirInTierLoc3, 300, true, "HDD", 1);
    // idx | tier1 | tier2 | tier3
    //  0    200
    //  0      ├───── 0
    //  1      └───── 100
    //  0               ├─── 1800
    //  1               ├─── 700   <--- alloc
    //  2               └─── 0
    //
    ServerConfiguration.reset();
  }