@SuppressWarnings("PMD.UseStringBufferForStringAppends")
    public static void main(String argv[]) {
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        boolean runIndex = true;
        boolean update = true;
        boolean optimizedChanged = false;
        CommandLineOptions cmdOptions = new CommandLineOptions();
        
        if (argv.length == 0) {
            System.err.println(cmdOptions.getUsage());
            System.exit(1);
        } else {
            boolean searchRepositories = false;
            ArrayList<String> subFiles = new ArrayList<String>();
            ArrayList<String> repositories = new ArrayList<String>();
            String configFilename = null;
            String configHost = null;
            boolean addProjects = false;
            boolean refreshHistory = false;
            String defaultProject = null;
            boolean listFiles = false;
            boolean createDict = false;
            int noThreads = Runtime.getRuntime().availableProcessors();
            
            // Parse command line options:
            Getopt getopt = new Getopt(argv, cmdOptions.getCommandString());

            try {
                getopt.parse();
            } catch (ParseException ex) {
                System.err.println("OpenGrok: " + ex.getMessage());
                System.err.println(cmdOptions.getUsage());
                System.exit(1);
            }

            try{
                int cmd;
                
                // We need to read the configuration file first, since we
                // will try to overwrite options..
                while ((cmd = getopt.getOpt()) != -1) {
                    if (cmd == 'R') {
                        env.readConfiguration(new File(getopt.getOptarg()));
                        break;
                    }
                }
                
                // Now we can handle all the other options..
                getopt.reset();                
                while ((cmd = getopt.getOpt()) != -1) {
                    switch (cmd) {
                    case 't':
                        createDict = true;
                        runIndex = false;
                        break;

                    case 'q': env.setVerbose(false); break;
                    case 'e': env.setGenerateHtml(false); break;
                    case 'P': addProjects = true; break;
                    case 'p': defaultProject = getopt.getOptarg(); break;
                    case 'c': env.setCtags(getopt.getOptarg()); break;
                    case 'w': {
                        String webapp = getopt.getOptarg();
                        if (webapp.charAt(0) != '/' && !webapp.startsWith("http")) {
                            webapp = "/" + webapp;
                        }
                        if (webapp.endsWith("/")) {
                            env.setUrlPrefix(webapp + "s?");
                        } else {
                            env.setUrlPrefix(webapp + "/s?");
                        }
                    }
                    break;
                    case 'W': configFilename = getopt.getOptarg(); break;
                    case 'U': configHost = getopt.getOptarg(); break;
                    case 'R': 
                        // already handled
                        break;
                    case 'n': runIndex = false; break;
                    case 'H': refreshHistory = true; break;
                    case 'h' : repositories.add(getopt.getOptarg()); break;
                    case 'r': {
                        if (getopt.getOptarg().equalsIgnoreCase(ON)) {
                            env.setRemoteScmSupported(true);
                        } else if (getopt.getOptarg().equalsIgnoreCase(OFF)) {
                            env.setRemoteScmSupported(false);
                        } else {
                            System.err.println("ERROR: You should pass either \"on\" or \"off\" as argument to -r");
                            System.err.println("       Ex: \"-r on\" will allow retrival for remote SCM systems");
                            System.err.println("           \"-r off\" will ignore SCM for remote systems");
                        }
                    }
                    break;
                    case 'O': {
                       boolean oldval = env.isOptimizeDatabase();
                        if (getopt.getOptarg().equalsIgnoreCase(ON)) {
                            env.setOptimizeDatabase(true);
                        } else if (getopt.getOptarg().equalsIgnoreCase(OFF)) {
                            env.setOptimizeDatabase(false);
                        } else {
                            System.err.println("ERROR: You should pass either \"on\" or \"off\" as argument to -O");
                            System.err.println("       Ex: \"-O on\" will optimize the database as part of the index generation");
                            System.err.println("           \"-O off\" disable optimization of the index database");
                        }
                       if (oldval != env.isOptimizeDatabase()) {
                           optimizedChanged = true;
                        }
                    }
                    break;
                    case 'v': env.setVerbose(true); break;

                    case 's': {
                        File file = new File(getopt.getOptarg());
                        if (!file.isDirectory()) {
                            System.err.println("ERROR: No such directory: " + file.toString());
                            System.exit(1);
                        }

                        env.setSourceRootFile(file);
                        break;
                    }
                    case 'd': 
                        env.setDataRoot(getopt.getOptarg());
                        break;
                    case 'i':  
                        env.getIgnoredNames().add(getopt.getOptarg()); 
                        break;
                    case 'S' : searchRepositories = true; break;
                    case 'Q' : 
                        if (getopt.getOptarg().equalsIgnoreCase(ON)) {
                            env.setQuickContextScan(true);
                        } else if (getopt.getOptarg().equalsIgnoreCase(OFF)) {
                            env.setQuickContextScan(false);
                        } else {
                            System.err.println("ERROR: You should pass either \"on\" or \"off\" as argument to -Q");
                            System.err.println("       Ex: \"-Q on\" will just scan a \"chunk\" of the file and insert \"[..all..]\"");
                            System.err.println("           \"-Q off\" will try to build a more accurate list by reading the complete file.");
                        }
                        
                        break;
                    case 'm' : {
                        try {
                            env.setIndexWordLimit(Integer.parseInt(getopt.getOptarg()));
                        } catch (NumberFormatException exp) {
                            System.err.println("ERROR: Failed to parse argument to \"-m\": " + exp.getMessage());
                            System.exit(1);
                        }
                        break;
                    }
                    case 'a' : 
                        if (getopt.getOptarg().equalsIgnoreCase(ON)) {
                            env.setAllowLeadingWildcard(true);
                        } else if (getopt.getOptarg().equalsIgnoreCase(OFF)) {
                            env.setAllowLeadingWildcard(false);
                        } else {
                            System.err.println("ERROR: You should pass either \"on\" or \"off\" as argument to -a");
                            System.err.println("       Ex: \"-a on\" will allow a search to start with a wildcard");
                            System.err.println("           \"-a off\" will disallow a search to start with a wildcard");
                            System.exit(1);
                        }
                        
                        break;

                    case 'A': {
                            String[] arg = getopt.getOptarg().split(":");
                            if (arg.length != 2) {
                                System.err.println("ERROR: You must specify: -A extension:class");
                                System.err.println("       Ex: -A foo:org.opensolaris.opengrok.analysis.c.CAnalyzer");
                                System.err.println("           will use the C analyzer for all files ending with .foo");
                                System.err.println("       Ex: -A c:-");
                                System.err.println("           will disable the c-analyzer for for all files ending with .c");
                                System.exit(1);
                            }

                            arg[0] = arg[0].substring(arg[0].lastIndexOf('.') + 1).toUpperCase();
                            if (arg[1].equals("-")) {
                                AnalyzerGuru.addExtension(arg[0], null);
                                break;
                            } 

                            try {
                                AnalyzerGuru.addExtension(
                                        arg[0],
                                        AnalyzerGuru.findFactory(arg[1]));
                            } catch (Exception e) {
                                System.err.println("Unable to use " + arg[1] +
                                                   " as a FileAnalyzerFactory");
                                e.printStackTrace();
                                System.exit(1);
                            }
                        }
                        break;
                    case 'L' :
                        env.setWebappLAF(getopt.getOptarg());
                        break;
                    case 'T' :
                        try {
                            noThreads = Integer.parseInt(getopt.getOptarg());
                        } catch (NumberFormatException exp) {
                            System.err.println("ERROR: Failed to parse argument to \"-T\": " + exp.getMessage());
                            System.exit(1);
                        }
                        break;
                    case 'l' : 
                        if (getopt.getOptarg().equalsIgnoreCase(ON)) {
                            env.setUsingLuceneLocking(true);
                        } else if (getopt.getOptarg().equalsIgnoreCase(OFF)) {
                            env.setUsingLuceneLocking(false);
                        } else {
                            System.err.println("ERROR: You should pass either \"on\" or \"off\" as argument to -l");
                            System.err.println("       Ex: \"-l on\" will enable locks in Lucene");
                            System.err.println("           \"-l off\" will disable locks in Lucene");
                        }  
                        break;
                    case 'V' :
                        System.out.println(Info.getFullVersion());
                        System.exit(0);
                        break;
                        
                    case '?':
                        System.err.println(cmdOptions.getUsage());
                        System.exit(0);
                        break;
                        
                    default: 
                        System.err.println("Internal Error - Unimplemented cmdline option: " + (char)cmd);
                        System.exit(1);
                    }
                }

                int optind = getopt.getOptind();
                if (optind != -1) {                
                    while (optind < argv.length) {
                        subFiles.add(argv[optind]);
                        ++optind;
                    }
                }
                
                getInstance().prepareIndexer(env, searchRepositories, addProjects,
                    defaultProject,configFilename,refreshHistory,
                    listFiles,createDict,subFiles,repositories);
            if (runIndex || (optimizedChanged && env.isOptimizeDatabase())) {
                IndexChangedListener progress = new DefaultIndexChangedListener();
                getInstance().doIndexerExecution(update, noThreads, subFiles,
                        progress);
            }
            getInstance().sendToConfigHost(env, configHost);
         } catch (IndexerException ex) {
            OpenGrokLogger.getLogger().log(Level.SEVERE, "Exception running indexer", ex);
            System.err.println(cmdOptions.getUsage());
            System.exit(1);
         } catch (IOException ioe) {
            System.err.println("Got IOException " + ioe);
            OpenGrokLogger.getLogger().log(Level.SEVERE, "Exception running indexer", ioe);
            System.exit(1);
         }
      }

   }