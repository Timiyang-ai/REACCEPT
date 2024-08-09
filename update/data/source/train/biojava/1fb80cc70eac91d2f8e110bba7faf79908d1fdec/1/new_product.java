public String toString(){

		String str = "AminoAcid "+ recordType + ":"+ pdb_name + " " + amino_char +
		" " + residueNumber +  " "+ pdb_flag + " " + recordType  ;
		if (pdb_flag) {
			str = str + " atoms: "+atoms.size();
		}
		if ( altLocs != null)
			str += " has altLocs :" + altLocs.size(); 

		return str ;

	}