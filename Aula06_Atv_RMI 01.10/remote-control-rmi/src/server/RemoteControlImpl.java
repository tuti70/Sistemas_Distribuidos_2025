package server;

import shared.RemoteControl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteControlImpl extends UnicastRemoteObject implements RemoteControl {
    private boolean poweredOn;
    private int volume;
    private int currentChannel;
    private final int MIN_VOLUME = 0;
    private final int MAX_VOLUME = 100;
    private final int MIN_CHANNEL = 1;
    private final int MAX_CHANNEL = 999;

    public RemoteControlImpl() throws RemoteException {
        super();
        this.poweredOn = false;
        this.volume = 50; // Volume médio inicial
        this.currentChannel = 1; // Canal inicial
    }

    @Override
    public String power() throws RemoteException {
        poweredOn = !poweredOn;
        if (poweredOn) {
            return "TV Ligada! Canal: " + currentChannel + ", Volume: " + volume;
        } else {
            return "TV Desligada";
        }
    }

    @Override
    public String increaseVolume() throws RemoteException {
        if (!poweredOn) {
            return "TV está desligada!";
        }

        if (volume < MAX_VOLUME) {
            volume += 5;
            return "Volume aumentado para: " + volume;
        } else {
            return "Volume máximo atingido (" + volume + ")";
        }
    }

    @Override
    public String decreaseVolume() throws RemoteException {
        if (!poweredOn) {
            return "TV está desligada!";
        }

        if (volume > MIN_VOLUME) {
            volume -= 5;
            return "Volume diminuído para: " + volume;
        } else {
            return "Volume mínimo atingido (" + volume + ")";
        }
    }

    @Override
    public String nextChannel() throws RemoteException {
        if (!poweredOn) {
            return "TV está desligada!";
        }

        if (currentChannel < MAX_CHANNEL) {
            currentChannel++;
            return "Canal alterado para: " + currentChannel;
        } else {
            currentChannel = MIN_CHANNEL;
            return "Canal alterado para: " + currentChannel;
        }
    }

    @Override
    public String previousChannel() throws RemoteException {
        if (!poweredOn) {
            return "TV está desligada!";
        }

        if (currentChannel > MIN_CHANNEL) {
            currentChannel--;
            return "Canal alterado para: " + currentChannel;
        } else {
            currentChannel = MAX_CHANNEL;
            return "Canal alterado para: " + currentChannel;
        }
    }

    @Override
    public String getStatus() throws RemoteException {
        if (poweredOn) {
            return "Status: Ligada | Canal: " + currentChannel + " | Volume: " + volume;
        } else {
            return "Status: Desligada";
        }
    }
}