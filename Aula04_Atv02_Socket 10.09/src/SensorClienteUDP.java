import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SensorClienteUDP {
    private static final String SERVIDOR = "localhost";
    private static final int PORTA = 9876;
    private static final Random random = new Random();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java SensorClienteUDP <nome-sensor> <tipo-dado> [intervalo-ms]");
            System.out.println("Exemplo: java SensorClienteUDP Sensor1 temperatura 1000");
            System.out.println("Tipos disponíveis: temperatura, umidade, pressao, luminosidade");
            return;
        }

        String nomeSensor = args[0];
        String tipoDado = args[1].toLowerCase();
        int intervalo = args.length > 2 ? Integer.parseInt(args[2]) : 1000;

        try {
            InetAddress enderecoServidor = InetAddress.getByName(SERVIDOR);
            DatagramSocket socket = new DatagramSocket();

            System.out.println("🔧 Sensor '" + nomeSensor + "' iniciado");
            System.out.println("📡 Enviando dados do tipo: " + tipoDado);
            System.out.println("⏰ Intervalo: " + intervalo + "ms");
            System.out.println("🎯 Servidor: " + SERVIDOR + ":" + PORTA + "\n");

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            // Agendar envio periódico
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    String leitura = gerarLeitura(tipoDado);
                    String mensagem = String.format("%s: %s", nomeSensor, leitura);

                    byte[] buffer = mensagem.getBytes();
                    DatagramPacket pacote = new DatagramPacket(
                            buffer, buffer.length, enderecoServidor, PORTA);

                    socket.send(pacote);
                    System.out.println("📤 Enviado: " + mensagem);

                } catch (IOException e) {
                    System.err.println("❌ Erro ao enviar dados: " + e.getMessage());
                }
            }, 0, intervalo, TimeUnit.MILLISECONDS);

            // Manter programa rodando
            System.out.println("Pressione Ctrl+C para parar o sensor...");
            Thread.sleep(Long.MAX_VALUE);

        } catch (UnknownHostException e) {
            System.err.println("❌ Servidor não encontrado: " + SERVIDOR);
        } catch (SocketException e) {
            System.err.println("❌ Erro ao criar socket: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("\n🛑 Sensor parado.");
        }
    }

    private static String gerarLeitura(String tipoDado) {
        switch (tipoDado) {
            case "temperatura":
                double temp = 20 + random.nextDouble() * 30; // 20°C to 50°C
                return String.format("%.1f°C", temp);

            case "umidade":
                double umidade = 30 + random.nextDouble() * 70; // 30% to 100%
                return String.format("%.1f%%RH", umidade);

            case "pressao":
                double pressao = 900 + random.nextDouble() * 200; // 900hPa to 1100hPa
                return String.format("%.1fhPa", pressao);

            case "luminosidade":
                int lux = random.nextInt(10000); // 0 to 10000 lux
                return String.format("%dlux", lux);

            default:
                return "tipo_desconhecido: " + random.nextInt(100);
        }
    }
}