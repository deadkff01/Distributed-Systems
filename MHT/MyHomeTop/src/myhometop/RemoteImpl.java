/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhometop;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;



/**
 *
 * @author hp
 */
public class RemoteImpl extends UnicastRemoteObject implements ServerActions{
    
   private HashMap<Sensor, Atuador> spotConcat = new HashMap<>();
 

   public ArrayList<Sensor> sensorList = new ArrayList<>(); 
   
   public ArrayList<Sensor> listaSensor;
   public ArrayList<Atuador> listaAtuador;

   protected RemoteImpl() throws RemoteException {
       super();
       
   }
  @Override
    public HashMap<Sensor, Atuador> getSpotConcat() {
        return spotConcat;
    }

    public void setSpotConcat(HashMap<Sensor, Atuador> spotConcat) {
        this.spotConcat = spotConcat;
    }
   
   @Override
    public ArrayList<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(ArrayList<Sensor> sensorList) {
        this.sensorList = sensorList;
    }


    @Override
    public ArrayList<Sensor> getListaSensor() throws RemoteException {
       return listaSensor;
    }
    
    @Override
    public ArrayList<Atuador> getListaAtuador() throws RemoteException {
       return listaAtuador;
    }
    
    @Override
    public void concatSA(Sensor s1, Atuador a1, String function) {
        
        if(function.equals("adicionar")){
         //prevent duplicates
       
          this.spotConcat.put(s1, a1);
        }
        else {
         for(Map.Entry<Sensor, Atuador> entry : spotConcat.entrySet()) {
                Sensor s = entry.getKey();
                Atuador a = entry.getValue();
                
                if(s.getNomeSensor().equals(s1.getNomeSensor()) && 
                   a.getNomeAtuador().equals(a1.getNomeAtuador())){
                    
                    entry.getKey().setDescricaoSensor(s1.getDescricaoSensor());
                    entry.getValue().setDescricaoAtuador(a1.getDescricaoAtuador());
                }
         }
        }

    }
    
   @Override
    public void atualizarTabelas() {          
        listaSensor = new ArrayList<>();
        listaAtuador = new ArrayList<>();
        
            for(Map.Entry<Sensor, Atuador> entry : spotConcat.entrySet()) {
                Sensor s = entry.getKey();
                Atuador a = entry.getValue();             
                //String tmp = a.getDescricaoAtuador();
                // s.setDescricaoSensor(tmp); 
                listaSensor.add(new Sensor(s.getNomeSensor(), s.getDescricaoSensor(), s.getTipoSensor(), s.getLocalSensor()));
                listaAtuador.add(new Atuador(a.getNomeAtuador(), a.getDescricaoAtuador(), a.getTipoAtuador(), a.getLocalAtuador()));    
            }
    }
    
    
    @Override
    public void addSensor(String nome, String descricao, String tipo, String local) throws RemoteException {
       Sensor tmp = new Sensor( nome,descricao,tipo,local);
       sensorList.add(tmp);
    }



}
