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
                    header = loc;
                    Matcher m = lp.matcher(loc);
                    if (m.matches()) {
                    	seqName = m.group(1);
                    }
                } else if (sectionKey.equals(DEFINITION_TAG)) {
                } else if (sectionKey.equals(ACCESSION_TAG)) {
                    // if multiple accessions, store only first as accession,
                    // and store rest in annotation
                    String[] accs = ((String[])section.get(0))[1].split("\\s+");
                    accession = accs[0].trim();
                } else if (sectionKey.equals(VERSION_TAG)) {
                } else if (sectionKey.equals(KEYWORDS_TAG)) {
                } else if (sectionKey.equals(SOURCE_TAG)) {
                    // ignore - can get all this from the first feature
                } else if (sectionKey.equals(REFERENCE_TAG) ) {
                } else if (sectionKey.equals(COMMENT_TAG) ) {
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
                    sequence.setOriginalHeader(seqName);
                    sequence.setAccession(new AccessionID(accession));
                    
                    sequences.put(sequence.getAccession().getID(),sequence);
                }
            } while (!sectionKey.equals(END_SEQUENCE_TAG));
        } catch(RuntimeException e) {
            throw new ParserException("Bad sequence section");
        }
        return sequences;
        
        /*
        
        StringBuilder sb = new StringBuilder();
        int processedSequences=0;
        boolean keepGoing = true;

        do {
            line = line.trim(); // nice to have but probably not needed
            if (line.length() != 0) {
                if (line.startsWith(">")) {
                    if (sb.length() > 0) {//i.e. if there is already a sequence before
                    //    System.out.println("Sequence index=" + sequenceIndex);
                    	@SuppressWarnings("unchecked")
                        S sequence = (S)sequenceCreator.getSequence(sb.toString(), sequenceIndex);
                        headerParser.parseHeader(header, sequence);
                        sequences.put(sequence.getAccession().getID(),sequence);
                        processedSequences++;
//                        if (maxSequenceLength < sb.length()) {
//                            maxSequenceLength = sb.length();
//                        }
//                        sb = new StringBuilder(maxSequenceLength);
                        sb.setLength(0); //this is faster, better memory utilization (same buffer)
                    }
                    header = line.substring(1);
                } else if (line.startsWith(";")) {
                } else {
                    //mark the start of the sequence with the fileIndex before the line was read
                    if(sb.length() == 0){
                        sequenceIndex = fileIndex;
                    }
                    sb.append(line);
                }
            }
            fileIndex = br.getBytesRead();
            line = br.readLine();
			if (line == null) {//i.e. EOF
				@SuppressWarnings("unchecked")
				//    System.out.println("Sequence index=" + sequenceIndex + " " + fileIndex );
                S sequence = (S)sequenceCreator.getSequence(sb.toString(), sequenceIndex);
                headerParser.parseHeader(header, sequence);
                sequences.put(sequence.getAccession().getID(),sequence);
                processedSequences++;
                keepGoing = false;
            }
			if (max > -1 && processedSequences>=max) {
				keepGoing=false;
			}
        } while (keepGoing);
        this.line  = line;
        this.header= header;
        return sequences;
*/        
    }