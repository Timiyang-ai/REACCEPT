public Object execute(CommandLine commandLine)
    throws Exception
  {
    String file = commandLine.getValue(Options.FILE_OPTION);
    String projectName = commandLine.getValue(Options.PROJECT_OPTION);
    IProject project = ProjectUtils.getProject(projectName);

    // only refresh the file.
    if(!commandLine.hasOption(Options.VALIDATE_OPTION)){
      // getting the file will refresh it.
      ProjectUtils.getFile(project, file);

    // validate the src file.
    }else{
      // JavaUtils refreshes the file when getting it.
      IJavaProject javaProject = JavaUtils.getJavaProject(project);
      ICompilationUnit src = JavaUtils.getCompilationUnit(javaProject, file);

      IProblem[] problems = JavaUtils.getProblems(src);

      ArrayList<Error> errors = new ArrayList<Error>();
      String filename = src.getResource()
        .getLocation().toOSString().replace('\\', '/');
      FileOffsets offsets = FileOffsets.compile(filename);
      for(int ii = 0; ii < problems.length; ii++){
        int[] lineColumn =
          offsets.offsetToLineColumn(problems[ii].getSourceStart());

        // one day vim might support ability to mark the offending text.
        /*int[] endLineColumn =
          offsets.offsetToLineColumn(problems[ii].getSourceEnd());*/

        errors.add(new Error(
            problems[ii].getMessage(),
            filename,
            lineColumn[0],
            lineColumn[1],
            problems[ii].isWarning()));
      }

      if(commandLine.hasOption(Options.BUILD_OPTION)){
        project.build(
            IncrementalProjectBuilder.INCREMENTAL_BUILD,
            new NullProgressMonitor());
      }
      return errors;
    }
    return null;
  }