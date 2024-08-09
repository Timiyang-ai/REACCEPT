@SuppressWarnings("unchecked")
	 void split() throws IOException, ExecException
	 {
		 if (children == null)
			 return;
		 
		 int size = children.size();
		 if (st == Partition.SplitType.RECORD)
		 {
			 for (int i = 0; i < size; i++)
			 {
				 if (children.get(i).projIndex != -1) // a leaf: set projection directly
			 		((Tuple)children.get(i).leaf.field).set(children.get(i).projIndex, ((Tuple) field).get(children.get(i).fieldIndex));
				 else
					 children.get(i).field = ((Tuple) field).get(children.get(i).fieldIndex);
			 }
		 } else if (st == Partition.SplitType.COLLECTION) {
		    DataBag srcBag, tgtBag;
		    srcBag = (DataBag) field;
		    Tuple tuple;
		    for (int i = 0; i < size; i++)
		    {
		      if (children.get(i).projIndex != -1) // a leaf: set projection directly
		      {
		        tgtBag = (DataBag)((Tuple)children.get(i).leaf.field).get(children.get(i).projIndex);
		      } else {
		        tgtBag = (DataBag) children.get(i).field;
		        tgtBag.clear();
		      }
		      for (Iterator<Tuple> it = srcBag.iterator(); it.hasNext(); )
		      {
		        tuple = TypesUtils.createTuple(scratchSchema);
		        tuple.set(0, it.next().get(children.get(i).fieldIndex));
		        tgtBag.add(tuple);
		      }
		    }
		 } else if (st == Partition.SplitType.MAP && keys != null) {
       String key;
       Iterator<String> it;

			 for (int i = 0; i < size; i++)
			 {
				 if (children.get(i).projIndex != -1) // a leaf: set projection directly
         {
           for (it = keys.iterator(); it.hasNext(); )
           {
             key = it.next();
			 		   ((Map<String, Object>) (((Tuple)children.get(i).leaf.field).get(children.get(i).projIndex))).put(key, ((Map<String, Object>) field).get(key));
           }
         } else {
           for (it = keys.iterator(); it.hasNext(); )
           {
             key = it.next();
					   children.get(i).field = ((Map<String, Object>) field).get(key);
           }
         }
			 }
		 }
	 }