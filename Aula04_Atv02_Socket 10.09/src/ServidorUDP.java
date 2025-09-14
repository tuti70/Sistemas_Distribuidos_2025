import java.io.*;
import java.net.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ServidorUDP {
    private static final int PORTA = 9876;
    private static final int BUFFER_SIZE = 1024;
    private static Map<String, String> ultimasLeituras = new HashMap<>();
    private static PrintWriter logWriter;

    public static void main(String[] args) {
        try {
            // Configurar logging
            LocalDateTime agora = LocalDateTime.now();
            String nomeArquivoLog = String.format("log_sensores_%s.txt",
                    agora.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")));

            logWriter = new PrintWriter(new FileWriter("dados_sensores/" + nomeArquivoLog));
            logWriter.println(
                    "=== INÍCIO DO LOG - " + agora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " ===");

            // Criar socket UDP
            DatagramSocket socket = new DatagramSocket(PORTA);
            System.out.println("🟢 Servidor UDP iniciado na porta " + PORTA);
            System.out.println("Aguardando leituras de sensores...\n");

            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                // Preparar para receber datagrama
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote);

                // Processar dados recebidos
                String dados = new String(pacote.getData(), 0, pacote.getLength());
                String enderecoSensor = pacote.getAddress().getHostAddress() + ":" + pacote.getPort();

                // Registrar leitura e exibir
                registrarLeitura(dados, enderecoSensor);

                // Limpar buffer para próximo pacote
                Arrays.fill(buffer, (byte) 0);
            }

        } catch (IOException e) {
            System.err.println("❌ Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (logWriter != null) {
                logWriter.println("=== FIM DO LOG ===");
                logWriter.close();
            }
        }
    }

    private static void registrarLeitura(String dados, String enderecoSensor) {
        String timestamp = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // Adicionar timestamp aos dados
        String leituraFormatada = String.format("[%s] %s - %s", timestamp, enderecoSensor, dados);

        // Atualizar última leitura do sensor
        ultimasLeituras.put(enderecoSensor, leituraFormatada);

        // Exibir no console
        System.out.println("📊 Nova leitura: " + leituraFormatada);

        // Log em arquivo
        logWriter.println(leituraFormatada);
        logWriter.flush();

        // Exibir resumo a cada 10 leituras
        if (ultimasLeituras.size() % 10 == 0) {
            exibirResumoSensores();
        }
    }

    private static void exibirResumoSensores() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📈 RESUMO DOS SENSORES CONECTADOS");
        System.out.println("=".repeat(50));

        if (ultimasLeituras.isEmpty()) {
            System.out.println("Nenhum sensor conectado.");
        } else {
            for (Map.Entry<String, String> entry : ultimasLeituras.entrySet()) {
                System.out.println("🔹 " + entry.getValue());
            }
            System.out.println("Total de sensores ativos: " + ultimasLeituras.size());
        }
        System.out.println("=".repeat(50) + "\n");
    }
}