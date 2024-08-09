  @Test
  public void setAcl() throws Exception {
    SetAclContext context = SetAclContext.defaults();
    createFileWithSingleBlock(NESTED_FILE_URI);

    Set<String> entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertEquals(3, entries.size());

    // replace
    Set<String> newEntries = Sets.newHashSet("user::rwx", "group::rwx", "other::rwx");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertEquals(newEntries, entries);

    // replace
    newEntries = Sets.newHashSet("user::rw-", "group::r--", "other::r--");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.REPLACE,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertEquals(newEntries, entries);

    // modify existing
    newEntries = Sets.newHashSet("user::rwx", "group::r--", "other::r-x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertEquals(newEntries, entries);

    // modify add
    Set<String> oldEntries = new HashSet<>(entries);
    newEntries = Sets.newHashSet("user:usera:---", "group:groupa:--x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertTrue(entries.containsAll(oldEntries));
    assertTrue(entries.containsAll(newEntries));
    // check if the mask got updated correctly
    assertTrue(entries.contains("mask::r-x"));

    // modify existing and add
    newEntries = Sets.newHashSet("user:usera:---", "group:groupa:--x", "other::r-x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertTrue(entries.containsAll(newEntries));

    // remove all
    mFileSystemMaster
        .setAcl(NESTED_FILE_URI, SetAclAction.REMOVE_ALL, Collections.emptyList(), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertEquals(3, entries.size());

    // remove
    newEntries =
        Sets.newHashSet("user:usera:---", "user:userb:rwx", "group:groupa:--x", "group:groupb:-wx");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.MODIFY,
        newEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);
    oldEntries = new HashSet<>(entries);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    assertTrue(entries.containsAll(oldEntries));

    Set<String> deleteEntries = Sets.newHashSet("user:userb:rwx", "group:groupa:--x");
    mFileSystemMaster.setAcl(NESTED_FILE_URI, SetAclAction.REMOVE,
        deleteEntries.stream().map(AclEntry::fromCliString).collect(Collectors.toList()), context);

    entries = Sets.newHashSet(mFileSystemMaster
        .getFileInfo(NESTED_FILE_URI, GET_STATUS_CONTEXT).convertAclToStringEntries());
    Set<String> remainingEntries = new HashSet<>(newEntries);
    assertTrue(remainingEntries.removeAll(deleteEntries));
    assertTrue(entries.containsAll(remainingEntries));

    final Set<String> finalEntries = entries;
    assertTrue(deleteEntries.stream().noneMatch(finalEntries::contains));
  }