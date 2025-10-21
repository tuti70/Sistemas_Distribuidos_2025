package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteControl extends Remote {
    // Ligar/Desligar
    String power() throws RemoteException;

    // Controle de volume
    String increaseVolume() throws RemoteException;

    String decreaseVolume() throws RemoteException;

    // Controle de canal
    String nextChannel() throws RemoteException;

    String previousChannel() throws RemoteException;

    // Status atual
    String getStatus() throws RemoteException;
}