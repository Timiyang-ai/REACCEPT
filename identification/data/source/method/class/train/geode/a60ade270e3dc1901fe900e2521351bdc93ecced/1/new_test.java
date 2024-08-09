  @Test
  public void findMembers() {
    assertThat(validator.findMembers(null)).flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");

    assertThat(validator.findMembers(null))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");
    assertThat(validator.findMembers(null, new String[] {null}))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");
    assertThat(validator.findMembers(null, "cluster"))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");
    assertThat(validator.findMembers(null, "Cluster"))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");
    assertThat(validator.findMembers(null, "CLUSTER"))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");
    assertThat(validator.findMembers(null, ""))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");

    assertThat(validator.findMembers("member1")).flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1");

    assertThat(validator.findMembers("member1", "group1"))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1");

    assertThat(validator.findMembers(null, "group1")).flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member2", "member4");

    assertThat(validator.findMembers(null, "group1", "cluster"))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member1", "member2", "member3", "member4", "member5");

    assertThat(validator.findMembers(null, "group1", "group2"))
        .flatExtracting(DistributedMember::getName)
        .containsExactlyInAnyOrder("member2", "member3", "member4");
  }