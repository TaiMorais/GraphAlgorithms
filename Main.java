class Main {
  public static void main(String[] args) {
    Graph g1 = new Graph(9);
    g1.addEdgeUnoriented(7, 5, 1);
    g1.addEdgeUnoriented(7, 1, 1);
    g1.addEdgeUnoriented(7, 2, 1);
    g1.addEdgeUnoriented(1, 0, 1);
    g1.addEdgeUnoriented(1, 4, 1);
    g1.addEdgeUnoriented(2, 3, 1);
    g1.addEdgeUnoriented(5, 6, 1);
    g1.addEdgeUnoriented(6, 8, 1);
    System.out.println(g1.bfs(7));
    System.out.println(g1.connected());

    /*
     * g1.addEdge(0, 1, 3);
     * g1.addEdge(1, 0, 3);
     * g1.addEdge(0, 3, 4);
     * g1.addEdge(3, 0, 4);
     * g1.addEdge(3, 4, 2); // aviso
     * // g1.degree(0);
     * System.out.println(g1);
     * System.out.println(g1.degree(1));
     * System.out.println("maior :" + g1.highestDegree());
     * System.out.println("menor :" + g1.lowestDegree());
     * System.out.println(g1.complement().toString());
     * System.out.println("Densidade" + g1.density());
     * Graph g2 = new Graph(3);
     * g2.addEdge(0, 1, 1);
     * g2.addEdge(1, 0, 1);
     * System.out.println("Subgrafo?" + g1.subGraph(g2));
     */
  }
}