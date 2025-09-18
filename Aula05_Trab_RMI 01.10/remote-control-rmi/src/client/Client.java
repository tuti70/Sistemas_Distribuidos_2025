package client;

import shared.RemoteControl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Conectar ao registry do servidor
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obter referência do objeto remoto
            RemoteControl remoteControl = (RemoteControl) registry.lookup("RemoteControl");

            Scanner scanner = new Scanner(System.in);
            int choice;

            System.out.println("=== CONTROLE REMOTO RMI ===");

            do {
                System.out.println("\n=== MENU ===");
                System.out.println("1. Ligar/Desligar TV");
                System.out.println("2. Aumentar Volume");
                System.out.println("3. Diminuir Volume");
                System.out.println("4. Próximo Canal");
                System.out.println("5. Canal Anterior");
                System.out.println("6. Ver Status");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println(remoteControl.power());
                        break;
                    case 2:
                        System.out.println(remoteControl.increaseVolume());
                        break;
                    case 3:
                        System.out.println(remoteControl.decreaseVolume());
                        break;
                    case 4:
                        System.out.println(remoteControl.nextChannel());
                        break;
                    case 5:
                        System.out.println(remoteControl.previousChannel());
                        break;
                    case 6:
                        System.out.println(remoteControl.getStatus());
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }

            } while (choice != 0);

            scanner.close();

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}