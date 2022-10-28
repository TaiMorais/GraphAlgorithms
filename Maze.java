import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// * Guarda uma tupla i, j
// * Representa uma posição
class Pair {
    int i;
    int j;

    public Pair(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d) ", i, j);
    }

}

public class Maze {
    private int[][] matrix;
    private Pair start;
    private Pair end;
    private int lines, columns;

    // * Todas as direções possíveis a partir de um vertice
    static Pair[] directionPairs = { new Pair(-1, 0), new Pair(1, 0), new Pair(0, -1), new Pair(0, 1) };

    // * CONSTRUTOR
    public Maze(String fileName) throws IOException {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        BufferedReader bf = new BufferedReader(new InputStreamReader(fis));

        String str;

        int lines = 0;
        int columns = 0;

        // * Descobre as dimensões do labirinto
        while ((str = bf.readLine()) != null) {

            String[] splitNodes = str.split("");

            columns = splitNodes.length;

            lines++;
        }

        // * Inicializa a representação matricial do labirinto
        this.lines = lines;
        this.columns = columns;
        matrix = new int[lines][columns];

        // * Reseta o bufferedReader até o inicio do arquivo
        fis.getChannel().position(0);
        bf = new BufferedReader(new InputStreamReader(fis));

        // * Esse loop monta o labirinto de fato
        int i = 0;
        while ((str = bf.readLine()) != null) {

            String[] splitNodes = str.split("");

            for (int j = 0; j < splitNodes.length; j++) {
                if (splitNodes[j].equals("#")) // * PAREDE
                    matrix[i][j] = 0;
                else { // * CAMINHO VALIDO
                    if (splitNodes[j].equals("S"))
                        start = new Pair(i, j); // * Marca a posição inicial descoberta
                    else if (splitNodes[j].equals("E"))
                        end = new Pair(i, j); // * Marca a posição final descoberta
                    matrix[i][j] = 1; // * marca na matriz como um caminho válido
                }

            }

            i++;
        }

        bf.close();
        fis.close();
    }

    public ArrayList<Pair> BFS() {
        // * Fila de Vértices a serem percorridos
        Queue<Pair> Q = new LinkedList<Pair>();
        // * Adiciona o vértice inicial na fila
        Q.add(start);

        // * Cria um vetor de vertices visitados, do mesmo tamanho do labirinto original
        boolean[][] visited = new boolean[this.lines][this.columns];
        // * Marca o vertice inicial como visitado
        visited[start.i][start.j] = true;

        // * Cria um vetor do tamanho do labirinto que guarda o vertice que levou a essa
        // * posição
        Pair[][] paths = new Pair[lines][columns];

        // * Loop principal, enquanto a fila não for vazia
        while (!Q.isEmpty()) {
            Pair u = Q.poll(); // * Desenfileira

            for (Pair d : directionPairs) {
                int i = u.i + d.i; // * Anda uma casa para essa direção
                int j = u.j + d.j; // * Anda uma casa para essa direção

                // * Verifica se esse vértice caminhado é um caminho valido, ou seja, 1
                // * Verifica se esse vertice já foi visitado
                // * Verifica se essa é uma posição válida
                if ((i >= 0 && i < lines && j >= 0 && j < columns) && !visited[i][j] && matrix[i][j] == 1) {

                    // * Adiciona esse vertice na lista, para continuar uma proxima iteração a
                    // * partir dele
                    Q.add(new Pair(i, j));
                    visited[i][j] = true; // * Marca como visitado

                    // * Salva o vertice u como o que originou esse caminho
                    paths[i][j] = u;
                }
            }
        }

        // * Se o vertice do fim nao foi marcado como visitado, o caminho nao foi feito,
        // * nao existe solução
        if (!visited[end.i][end.j])
            return null;

        // * Cria um vetor para representar o caminho final
        // * O caminho final seria percorrer o vetor de paths até a origem
        // * Começando por end, veriamos qual vertice o originou, e assim por diante
        // * Isso possibilita seguir a "trilha" da resposta
        ArrayList<Pair> result = new ArrayList<Pair>();
        Pair current = end;

        // * Percorro o vetor de caminhos até encontrar a origem
        while (paths[current.i][current.j] != null) {
            result.add(current);
            current = paths[current.i][current.j];
        }

        result.add(start); // * Adiciono a origem a resposta

        // * Já que percorremos de tras para frente, invertemos o caminho para
        // * visualizar
        Collections.reverse(result);

        // * Imprimo o caminho
        System.out.print("Caminho: ");
        for (Pair v : result) {
            System.out.print(String.format("(%d,%d) ", v.i, v.j));
        }
        System.out.println();

        return result;
    }

    public void showMaze() {
        System.out.println();
        System.out.println("Maze: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
