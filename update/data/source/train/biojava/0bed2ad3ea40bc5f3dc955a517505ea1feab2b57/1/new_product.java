public LinkedHashMap<String,S> process(int max) throws IOException {
        LinkedHashMap<String,S> sequences = new LinkedHashMap<String,S>();

        // Get an ordered list of key->value pairs in array-tuples
        List section = null;
        try{
            do {
                section = this.readSection();
                sectionKey = ((String[])section.get(0))[0];
                if(sectionKey == null){
                    throw new ParserException("Section key was null");
                }
                // process section-by-section
                if (sectionKey.equals(LOCUS_TAG)) {
                    String loc = ((String[])section.get(0))[1];
                    Matcher m = lp.matcher(loc);
                    if (m.matches()) {
                    	headerParser.setName(m.group(1));
                        accession = m.group(1); // default if no accession found
                        headerParser.setAccession(accession);
                    } else {
                        throw new ParserException("Bad locus line");
                    }
                } else if (sectionKey.equals(DEFINITION_TAG)) {
                	headerParser.setDescription(((String[])section.get(0))[1]);
                } else if (sectionKey.equals(ACCESSION_TAG)) {
                    // if multiple accessions, store only first as accession,
                    // and store rest in annotation
                    String[] accs = ((String[])section.get(0))[1].split("\\s+");
                    accession = accs[0].trim();
                    headerParser.setAccession(accession);
                } else if (sectionKey.equals(VERSION_TAG)) {
                    String ver = ((String[])section.get(0))[1];
                    Matcher m = vp.matcher(ver);
                    if (m.matches()) {
                        String verAcc = m.group(1);
                        if (!accession.equals(verAcc)) {
                            // the version refers to a different accession!
                            // believe the version line, and store the original
                            // accession away in the additional accession set
                            accession = verAcc;
                            headerParser.setAccession(accession);
                        }
                        if (m.group(3)!=null) headerParser.setVersion(Integer.parseInt(m.group(3)));
                        if (m.group(5)!=null) {
                            identifier = m.group(5);
                            headerParser.setIdentifier(identifier);
                        }
                    } else {
                        throw new ParserException("Bad version line");
                    }
                } else if (sectionKey.equals(KEYWORDS_TAG)) {
                } else if (sectionKey.equals(SOURCE_TAG)) {
                    // ignore - can get all this from the first feature
                } else if (sectionKey.equals(REFERENCE_TAG) ) {
                } else if (sectionKey.equals(COMMENT_TAG) ) {
                    // Set up some comments
                    headerParser.setComment(((String[])section.get(0))[1]);
                } else if (sectionKey.equals(FEATURE_TAG) ) {
                } else if (sectionKey.equals(BASE_COUNT_TAG)) {
                    // ignore - can calculate from sequence content later if needed
                } else if (sectionKey.equals(START_SEQUENCE_TAG) ) {
                	// our first line is ignorable as it is the ORIGIN tag
                    // the second line onwards conveniently have the number as
                    // the [0] tuple, and sequence string as [1] so all we have
                    // to do is concat the [1] parts and then strip out spaces,
                    // and replace '.' and '~' with '-' for our parser.
                	StringBuffer seq = new StringBuffer();
                    for (int i = 1 ; i < section.size(); i++) seq.append(((String[])section.get(i))[1]);
                	String seqData = seq.toString().replaceAll("\\s+","").replaceAll("[\\.|~]","-").toUpperCase();

                    S sequence = (S)sequenceCreator.getSequence(seqData, sequenceIndex);
                    headerParser.parseHeader(header, sequence);
                    sequences.put(sequence.getAccession().getID(),sequence);
                }
            } while (!sectionKey.equals(END_SEQUENCE_TAG));
        }catch(RuntimeException e){
            throw new ParserException("Bad sequence section", e);
        }
        return sequences;
        
    }