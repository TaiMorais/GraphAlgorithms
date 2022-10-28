import java.io.IOException;
import java.util.Scanner;

public class Main {

    static void MENU() {
        System.out.println("Informe a tarefa:");
        System.out.println("\t1-Caminho Minimo");
        System.out.println("\t2-Labirinto");
        System.out.println("\t3-Sair");
        System.out.println();
    }

    static void TYPEMENU() {
        System.out.println("Informe o algoritmo:");
        System.out.println("\t1-Dijkstra");
        System.out.println("\t2-Bellman-Ford");
        System.out.println("\t3-Floyd-Warshall");
        System.out.println("\t4-Bellman-Ford Aprimorado");
        System.out.println();
    }

    public static void main(String args[]) throws IOException {

        int option = 0;

        Scanner reader = new Scanner(System.in);

        do {

            MENU();

            option = Integer.parseInt(reader.nextLine());

            switch (option) {
                case 1: {
                    String file;

                    System.out.print("Arquivo: ");
                    file = reader.nextLine();

                    int src, dest;

                    System.out.print("Origem: ");
                    src = Integer.parseInt(reader.nextLine());

                    System.out.print("Destino: ");
                    dest = Integer.parseInt(reader.nextLine());

                    int type = 0;

                    do {
                        TYPEMENU();

                        type = Integer.parseInt(reader.nextLine());

                        switch (type) {
                            case 1: {
                                GraphList graph = new GraphList(file);

                                System.out.println("Processando...");

                                // * Inicio do relogio
                                long clockBegin = System.currentTimeMillis();

                                graph.djkstra(src, dest);

                                long clockEnd = System.currentTimeMillis();

                                System.out.println("Tempo: " + (clockEnd - clockBegin) / 1000F + "s");

                                break;
                            }

                            case 2: {
                                GraphList graph = new GraphList(file);

                                System.out.println("Processando...");

                                // * Inicio do relogio
                                long clockBegin = System.currentTimeMillis();

                                graph.bellmanFord(src, dest);

                                long clockEnd = System.currentTimeMillis();

                                System.out.println("Tempo: " + (clockEnd - clockBegin) / 1000F + "s");

                                break;
                            }

                            case 3: {
                                GraphMatrix graph = new GraphMatrix(file);

                                System.out.println("Processando...");

                                // * Inicio do relogio
                                long clockBegin = System.currentTimeMillis();

                                graph.floydWarshall(src, dest);

                                long clockEnd = System.currentTimeMillis();

                                System.out.println("Tempo: " + (clockEnd - clockBegin) / 1000F + "s");

                                break;
                            }

                            case 4: {
                                GraphList graph = new GraphList(file);

                                System.out.println("Processando...");

                                // * Inicio do relogio
                                long clockBegin = System.currentTimeMillis();

                                graph.bellmanFordImproved(src, dest);

                                long clockEnd = System.currentTimeMillis();

                                System.out.println("Tempo: " + (clockEnd - clockBegin) / 1000F + "s");

                                break;
                            }

                            default:
                                System.out.println("Digite uma opcao valida!");
                                break;
                        }

                    } while (type != 1 && type != 2 && type != 3 && type != 4);

                    break;
                }

                case 2: {
                    String file;

                    System.out.print("Arquivo: ");
                    file = reader.nextLine();

                    Maze maze = new Maze(file);

                    System.out.println("Processando...");

                    // * Inicio do relogio
                    long clockBegin = System.currentTimeMillis();

                    maze.BFS();

                    long clockEnd = System.currentTimeMillis();

                    System.out.println("Tempo: " + (clockEnd - clockBegin) / 1000F + "s");

                    break;
                }

                case 3:
                    break;

                default:
                    System.out.println("Digite uma opcao valida!");
                    break;
            }

        } while (option != 3);

        reader.close();
    }
}