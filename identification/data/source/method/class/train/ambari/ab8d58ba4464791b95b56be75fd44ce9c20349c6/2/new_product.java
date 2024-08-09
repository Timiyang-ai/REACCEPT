public Map<String, List<RepositoryInfo>> getRepository(String stackName,
                                                         String version) throws AmbariException {
    StackInfo stack = getStack(stackName, version);
    List<RepositoryInfo> repository = stack.getRepositories();

    Map<String, List<RepositoryInfo>> reposResult = new HashMap<>();
    for (RepositoryInfo repo : repository) {
      if (!reposResult.containsKey(repo.getOsType())) {
        reposResult.put(repo.getOsType(), new ArrayList<>());
      }
      reposResult.get(repo.getOsType()).add(repo);
    }
    return reposResult;
  }