public Map<String, List<RepositoryInfo>> getRepository(String stackName,
                                                         String version) throws AmbariException {
    StackInfo stack = getStack(stackName, version);
    List<RepositoryInfo> repository = stack.getRepositories();

    Map<String, List<RepositoryInfo>> reposResult = new HashMap<String, List<RepositoryInfo>>();
    for (RepositoryInfo repo : repository) {
      if (!reposResult.containsKey(repo.getOsType())) {
        reposResult.put(repo.getOsType(),
          new ArrayList<RepositoryInfo>());
      }
      reposResult.get(repo.getOsType()).add(repo);
    }
    return reposResult;
  }