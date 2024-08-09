protected ExceptionCollection scanArtifacts(MavenProject project, Engine engine, boolean aggregate) {
        try {
            final List<String> filterItems = Collections.singletonList(String.format("%s:%s", project.getGroupId(), project.getArtifactId()));
            final ProjectBuildingRequest buildingRequest = newResolveArtifactProjectBuildingRequest();
            buildingRequest.setProject(project);
            //For some reason the filter does not filter out the project being analyzed
            //if we pass in the filter below instead of null to the dependencyGraphBuilder
            final DependencyNode dn = dependencyGraphBuilder.buildDependencyGraph(buildingRequest, null, reactorProjects);

            CollectingDependencyNodeVisitor collectorVisitor = new CollectingDependencyNodeVisitor();
            // exclude artifact by pattern and its dependencies
            DependencyNodeVisitor transitiveFilterVisitor = new FilteringDependencyTransitiveNodeVisitor(collectorVisitor, 
                    new ArtifactDependencyNodeFilter(new PatternExcludesArtifactFilter(getExcludes())));
            // exclude exact artifact but not its dependencies, this filter must be appied on the root for first otherwise
            // in case the exclude has the same groupId of the current bundle its direct dependencies are not visited
            DependencyNodeVisitor artifactFilter = new FilteringDependencyNodeVisitor(transitiveFilterVisitor,
                    new ArtifactDependencyNodeFilter(new ExcludesArtifactFilter(filterItems)));
            dn.accept(artifactFilter);

            //collect dependencies with the filter - see comment above.
            final List<DependencyNode> nodes = collectorVisitor.getNodes();

            return collectDependencies(engine, project, nodes, buildingRequest, aggregate);
        } catch (DependencyGraphBuilderException ex) {
            final String msg = String.format("Unable to build dependency graph on project %s", project.getName());
            getLog().debug(msg, ex);
            return new ExceptionCollection(msg, ex);
        }
    }