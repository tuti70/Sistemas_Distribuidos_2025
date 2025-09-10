import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int PORTA = 12345;

        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA + ". Aguardando conexões...");

            // Aceita conexão de um cliente
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

            // Configura streams de entrada e saída
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            String mensagemCliente;

            // Loop para múltiplas mensagens
            while ((mensagemCliente = entrada.readLine()) != null) {
                System.out.println("Cliente disse: " + mensagemCliente);

                // Verifica se cliente quer sair
                if ("sair".equalsIgnoreCase(mensagemCliente.trim())) {
                    saida.println("Conexão encerrada pelo cliente. Adeus!");
                    break;
                }

                // Responde ao cliente
                String resposta = "Servidor recebeu: " + mensagemCliente;
                saida.println(resposta);
                System.out.println("Resposta enviada: " + resposta);
            }

            // Fecha conexão
            socket.close();
            System.out.println("Conexão com cliente encerrada.");

        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}