import java.util.ArrayList;
public class Graph {
  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public Graph(int countNodes) {
    this.countNodes = countNodes;
    this.adjMatrix = new int[countNodes][countNodes];
  }

  public int getCountNodes() {
    return this.countNodes;
  }

  public int getCountEdges() {
    return this.countEdges;
  }

  public int[][] getAdjMatrix() {
    return this.adjMatrix;
  }

  public String toString() {
    String str = "";
    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; ++j) {
        str += this.adjMatrix[i][j] + "\t";
      }
      str += "\n";
    }
    return str;

  }

  public void addEdge(int source, int sink, int weight) {
    if (source < 0 || source > this.countNodes - 1 || sink < 0 || sink > this.countNodes - 1 || weight <= 0) {
      System.err.println("Invalid value for source, sink or weight!");
      return;
    }
    this.adjMatrix[source][sink] = weight;
    this.countEdges++;
  }

  public int degree(int node) {
    // returns the degree of a node grau
    if (node < 0 || node > this.countNodes - 1)
      System.err.println("Invalid node: " + node);
    int degree = 0;
    for (int i = 0; i < 4; i++) {
      if (this.adjMatrix[node][i] != 0) {
        degree++;
      }
    }
    return degree;
  }

  public int highestDegree() {
    // returns the highest degree in the graph
    int highest_degree = 0;
    for (int i = 0; i < countNodes; i++) {
      if (degree(i) > highest_degree) {
        highest_degree = degree(i);
      }
    }
    return highest_degree;
  }

  /*
   * public int highestDegree(){
   * //returns the highest degree in the graph
   * int highest_degree = 0;
   * int degreeNodeI = 0;
   * for (int i = 0; i < this.adjMatrix.lenght; i++){
   * degreeNodeI = this.degree(i);
   * if(this.degree(i)> highest_degree ){
   * highest_degree = degreeNodeI; //boa pratica de prog usar o this para metodos
   * dentro da propria classe
   * }
   * }
   * return highest_degree;
   * }
   */

  public int lowestDegree() {
    int lowest_degree = countNodes;
    for (int i = 0; i < countNodes; i++) {
      if (degree(i) < lowest_degree) {
        lowest_degree = degree(i);
      }
    }
    return lowest_degree;
  }

  public Graph complement() {
    // returns the complement of the current graph
    Graph g2 = new Graph(this.adjMatrix.length);
    for (int i = 0; i < this.countNodes; i++) {
      for (int j = 0; j < this.countNodes; j++) {
        // g2.adjMatrix[i][j] = 0;
        if (i != j && this.adjMatrix[i][j] == 0) {
          // g2.adjMatrix[i][j] = 1;
          g2.addEdge(i, j, 1);
        }
      }
    }
    return g2;
  }

  public float density() {
    float density = this.countEdges * 2 / (this.countNodes * (this.countNodes - 1));
    return density;
  }

  public boolean subGraph(Graph g2) {
    // retorna true se g2 é subgrafo de this; falso caso contrário
    if (g2.countNodes > this.countNodes || g2.countEdges > this.countEdges) {
      return false;
    }
    for (int i = 0; i < g2.adjMatrix.length; i++) {
      for (int j = 0; j < g2.adjMatrix[i].length; j++) {
        if (g2.adjMatrix[i][j] != 0 && this.adjMatrix[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  public void addEdgeUnoriented(int u, int v, int w){

    if (u < 0 || u > this.countNodes - 1 || v < 0 || v > this.countNodes - 1 || w <= 0) {
      System.err.println("Invalid value for u, v or w!");
      return;
    }
    this.adjMatrix[u][v] = w;
    this.adjMatrix[v][u] = w;
    this.countEdges +=2;
    
  }

  public ArrayList<Integer> bfs(int s){
  //public busca_largura(Graph, int s){
  int[] desc = new int[this.countNodes];
   ArrayList<Integer> Q = new ArrayList<>();
    Q.add(s);
    ArrayList<Integer> R = new ArrayList<>();
    R.add(s);
    desc[s] = 1;
    //loop principal
    while(Q.size() >0){
      int u = Q.remove(0);
      for(int v=0; v < this.adjMatrix[u].length; v++){
        if(this.adjMatrix[u][v] != 0){ //verifica se v é adj a u
          if(desc[v] == 0){// significa que essa posição ainda nao foi examinada
          Q.add(v);
          R.add(v);
          desc[v] = 1;
          }
        }
      }
    }
  return R;
  }

  public boolean connected(){
    return this.bfs(0).size() == this.countNodes;
  }

}
