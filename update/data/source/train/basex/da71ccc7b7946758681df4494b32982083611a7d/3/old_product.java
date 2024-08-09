void delete(final int p, final int s) {
    // find the node to deleted
    int i = find(p);
    // if the node is not directly contained as a child, either start at array index 0 or
    // proceed with the next node in the child array to search for descendants of pre
    if(i == -1 || nodes[i].pre != p) ++i;
    // first pre value which is not deleted
    final int upper = p + s;
    // number of nodes to be deleted
    int num = 0;
    // determine number of nodes to be deleted
    for(int n = i; n < size && nodes[n].pre < upper; ++n, ++num);
    // new size of child array
    size -= num;

    if(size == 0) {
      // if all nodes are deleted, just create an empty array
      nodes = new NSNode[0];
    } else if(num > 0) {
      // otherwise remove nodes from the child array
      System.arraycopy(nodes, i + num, nodes, i, size - i);
      for(int n = size; n < size + num; n++) nodes[n] = null;
    }
  }