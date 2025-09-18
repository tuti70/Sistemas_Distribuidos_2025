package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        try {
            // Criar instância do objeto remoto
            RemoteControlImpl remoteControl = new RemoteControlImpl();

            // Criar registro RMI na porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registrar o objeto remoto
            registry.rebind("RemoteControl", remoteControl);

            System.out.println("Servidor RMI iniciado!");
            System.out.println("RemoteControl registrado como 'RemoteControl'");
            System.out.println("Aguardando conexões de clientes...");

        } catch (RemoteException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}