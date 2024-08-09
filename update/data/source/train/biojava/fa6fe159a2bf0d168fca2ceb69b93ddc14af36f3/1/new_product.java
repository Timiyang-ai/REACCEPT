public String toPDB() {
        String newline = System.getProperty("line.separator");

        StringBuffer str = new StringBuffer();
        //int i = 0 ;

        // Locale should be english, e.g. in DE separator is "," -> PDB files have "." !
        DecimalFormat d3 = (DecimalFormat)NumberFormat.getInstance(java.util.Locale.UK);
        d3.setMaximumIntegerDigits(3);
        d3.setMinimumFractionDigits(3);
        d3.setMaximumFractionDigits(3);

        DecimalFormat d2 = (DecimalFormat)NumberFormat.getInstance(java.util.Locale.UK);
        d2.setMaximumIntegerDigits(2);
        d2.setMinimumFractionDigits(2);
        d2.setMaximumFractionDigits(2);

        // TODO: print all the PDB header informaton in PDB style
        // some objects (PDBHeader, Compound) are still missing
        //

        PDBHeader header = structure.getPDBHeader();
        header.toPDB(str);

       
        //REMARK 800
        if (!structure.getSites().isEmpty()) {
            str.append("REMARK 800                                                                      " + newline);
            str.append("REMARK 800 SITE                                                                 " + newline);
            for (Site site : structure.getSites()) {
                site.remark800toPDB(str);
            }
        }
        //DBREF
        for (DBRef dbref : structure.getDBRefs()){
        	dbref.toPDB(str);
        	str.append(newline);
        }
        //SSBOND
        for (SSBond ssbond : structure.getSSBonds()){
        	ssbond.toPDB(str);
        	str.append(newline);
        }
        //SITE
        for (Site site : structure.getSites()) {
            site.toPDB(str);           
        }

        //
        // print the atom records
        //

        // do for all models
        int nrModels = structure.nrModels() ;
        if ( structure.isNmr()) {
            str.append("EXPDTA    NMR, "+ nrModels+" STRUCTURES"+newline) ;
        }
        for (int m = 0 ; m < nrModels ; m++) {
            List<Chain> model = structure.getModel(m);
            // todo support NMR structures ...
            if ( structure.isNmr()) {
                str.append("MODEL      " + (m+1)+ newline);
            }
            // do for all chains
            int nrChains = model.size();
            for ( int c =0; c<nrChains;c++) {
                Chain  chain   = model.get(c);
                String chainID = chain.getName();
                //if ( chainID.equals(DEFAULTCHAIN) ) chainID = " ";
                // do for all groups
                int nrGroups = chain.getAtomLength();
                for ( int h=0; h<nrGroups;h++){

                    Group g= chain.getAtomGroup(h);
                    String type = g.getType() ;

                    String record = "" ;
                    if ( type.equals("hetatm") ) {
                        record = "HETATM";
                    } else {
                        record = "ATOM  ";
                    }


                    // format output ...
                    int groupsize  = g.size();
                    String resName = g.getPDBName();
                    String pdbcode = g.getPDBCode();
                    String line    = "" ;

                    // iteratate over all atoms ...
                    for ( int atompos = 0 ; atompos < groupsize; atompos++) {
                        Atom a = null ;
                        try {
                            a = g.getAtom(atompos);
                        } catch ( StructureException e) {
                            System.err.println(e);
                            continue ;
                        }

                        int    seri       = a.getPDBserial()        ;
                        String serial     = alignRight(""+seri,5)   ;
                        String fullname   = a.getFullName()         ;

                       // System.out.println(" fullname: " + fullname + " : " + a.getAltLoc() + " : " + pdbcode);

                        Character  altLoc = a.getAltLoc()           ;
                        String resseq = "" ;
                        if ( hasInsertionCode(pdbcode) )
                            resseq     = alignRight(""+pdbcode,5);
                        else
                            resseq     = alignRight(""+pdbcode,4)+" ";
                        String x          = alignRight(""+d3.format(a.getX()),8);
                        String y          = alignRight(""+d3.format(a.getY()),8);
                        String z          = alignRight(""+d3.format(a.getZ()),8);
                        String occupancy  = alignRight(""+d2.format(a.getOccupancy()),6) ;
                        String tempfactor = alignRight(""+d2.format(a.getTempFactor()),6);

                        //System.out.println("fullname,zise:" + fullname + " " + fullname.length());

                        String leftResName = alignLeft(resName,3);

                        str.append(record);
                        str.append(serial);
                        str.append(" ");
                        str.append(fullname);
                        str.append(altLoc);
                        str.append(leftResName);
                        str.append(" ");
                        str.append(chainID);
                        str.append(resseq);
                        str.append("   ");
                        str.append(x);
                        str.append(y);
                        str.append(z);
                        str.append(occupancy);
                        str.append(tempfactor);

                        str.append(" ");
                        str.append(newline);

                        //line = record + serial + " " + fullname +altLoc
                        //+ leftResName + " " + chainID + resseq
                        //+ "   " + x+y+z
                        //+ occupancy + tempfactor;
                        //str.append(line + newline);
                        //System.out.println(line);
                    }
                }
            }

            if ( structure.isNmr()) {
                str.append("ENDMDL"+newline);
            }



        }

        if ( doPrintConnections() )
            str.append(printPDBConnections());

        return str.toString() ;
    }