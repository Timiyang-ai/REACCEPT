public InputStream getRevision(String parent, String basename, String rev) throws IOException {
        InputStream in = null;
        File history;
        
        switch (previousFile) {
            case RCS :
                File rcsfile = RCSHistoryParser.getRCSFile(parent, basename);
                if (rcsfile != null) {
                    String rcspath = rcsfile.getPath();
                    in = new BufferedInputStream(new RCSget(rcspath, rev));
                }
                break;
                
            case SCCS :
                history = SCCSHistoryParser.getSCCSFile(parent, basename);
                if (history != null && history.canRead()) {
                    in = SCCSget.getRevision(history, rev);
                    in.mark(32);
                    in.read();
                    in.reset();
                }
                break;
                
            case EXTERNAL :
                in = lookupHistoryGet(parent, basename, rev);
                break;
                
            default:
                ;
        }
        
        if (in == null) {
            in = guessGetRevision(parent, basename, rev);
        }
        
        return in;
    }