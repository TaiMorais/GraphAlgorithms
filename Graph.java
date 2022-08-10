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

public int highestDegree(){
  //returns the highest degree in the graph
  int highest_degree = 0;
  for (int i = 0; i < countNodes; i++){
      if(degree(i)> highest_degree ){
        highest_degree = degree(i);
      }      
    }
return highest_degree;
}


 /* public int highestDegree(){
  //returns the highest degree in the graph
  int highest_degree = 0;
  int degreeNodeI = 0;
  for (int i = 0; i < this.adjMatrix.lenght; i++){
      degreeNodeI = this.degree(i);
      if(this.degree(i)> highest_degree ){
        highest_degree = degreeNodeI; //boa pratica de prog usar o this para metodos dentro da propria classe
      }      
    }
return highest_degree;
}*/

public int lowestDegree(){
  int lowest_degree = countNodes;
  for (int i = 0; i < countNodes; i++){
  if(degree(i)<lowest_degree){
        lowest_degree = degree(i);
      }      
    }
return lowest_degree;
}

  public Graph complement(){
    //returns the complement of the current graph
    Graph g2 = new Graph(this.adjMatrix.length);
    for (int i = 0; i < this.countNodes; i++){
      for (int j = 0; j < this.countNodes; j++){
        //g2.adjMatrix[i][j] = 0;
        if (i != j && this.adjMatrix[i][j] == 0){
          //g2.adjMatrix[i][j] = 1;
          g2.addEdge(i, j, 1);
        }  
      }
    }   
    return g2;
}


  
     
      
}

