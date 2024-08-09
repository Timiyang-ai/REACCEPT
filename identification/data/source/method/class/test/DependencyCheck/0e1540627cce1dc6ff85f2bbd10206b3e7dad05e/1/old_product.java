protected ExceptionCollection scanArtifacts(MavenProject project, Engine engine, boolean aggregate) {
        try {
            final List<String> filterItems = Collections.singletonList(String.format("%s:%s", project.getGroupId(), project.getArtifactId()));
            final ProjectBuildingRequest buildingRequest = newResolveArtifactProjectBuildingRequest();
            buildingRequest.setProject(project);
            //For some reason the filter does not filter out the project being analyzed
            //if we pass in the filter below instead of null to the dependencyGraphBuilder
            final ArtifactFilter filter = new ExcludesArtifactFilter(filterItems);
            final DependencyNode dn = dependencyGraphBuilder.buildDependencyGraph(buildingRequest, null, reactorProjects);
            final CollectingDependencyNodeVisitor visitor = new CollectingDependencyNodeVisitor();
            dn.accept(visitor);

            //collect dependencies with the filter - see comment above.
            final List<DependencyNode> nodes = new ArrayList<>();
            for (DependencyNode node : visitor.getNodes()) {
                if (filter.include(node.getArtifact())) {
                    nodes.add(node);
                }
            }

            return collectDependencies(engine, project, nodes, buildingRequest, aggregate);
        } catch (DependencyGraphBuilderException ex) {
            final String msg = String.format("Unable to build dependency graph on project %s", project.getName());
            getLog().debug(msg, ex);
            return new ExceptionCollection(msg, ex);
        }
    }