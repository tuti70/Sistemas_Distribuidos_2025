import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String SERVIDOR = "localhost";
        final int PORTA = 12345;

        try (Socket socket = new Socket(SERVIDOR, PORTA);
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado ao servidor " + SERVIDOR + ":" + PORTA);

            // Configura streams de entrada e saída
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            String mensagemUsuario;

            // Loop para múltiplas mensagens
            while (true) {
                System.out.print("Digite uma mensagem (ou 'sair' para encerrar): ");
                mensagemUsuario = scanner.nextLine();

                // Envia mensagem para o servidor
                saida.println(mensagemUsuario);

                // Verifica se quer sair
                if ("sair".equalsIgnoreCase(mensagemUsuario.trim())) {
                    System.out.println("Encerrando conexão...");
                    break;
                }

                // Aguarda resposta do servidor
                String respostaServidor = entrada.readLine();
                if (respostaServidor != null) {
                    System.out.println("Servidor respondeu: " + respostaServidor);
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Servidor não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de conexão: " + e.getMessage());
        }

        System.out.println("Cliente finalizado.");
    }
}