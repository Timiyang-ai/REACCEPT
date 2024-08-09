public String toString(){

		String str = "AminoAcid "+ recordType + ":"+ pdb_name + " " + amino_char +
		" " + pdb_code +  " "+ pdb_flag + " " + recordType  ;
		if (pdb_flag) {
			str = str + " atoms: "+atoms.size();
		}
		return str ;

	}