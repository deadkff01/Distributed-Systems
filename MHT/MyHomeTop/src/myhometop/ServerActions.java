/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhometop;

import static java.awt.PageAttributes.MediaType.B;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author hp
 */
public interface ServerActions extends Remote{
    public ArrayList<Sensor> getListaSensor() throws RemoteException;
    public ArrayList<Atuador> getListaAtuador() throws RemoteException;
    public void addSensor(String nome, String descricao, String tipo, String local) throws RemoteException;
    public ArrayList<Sensor> getSensorList() throws RemoteException;
    public void concatSA(Sensor s1, Atuador a1, String function) throws RemoteException;
    public void atualizarTabelas() throws RemoteException;
    public HashMap<Sensor, Atuador> getSpotConcat() throws RemoteException;
  
}
