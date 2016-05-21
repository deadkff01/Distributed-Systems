/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhometop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;


/**
 *
 * @author hp
 */
public class Atuador implements Serializable{
    
    public String nomeAtuador;
    public String descricaoAtuador;
    public String tipoAtuador;
    public String localAtuador;
    
    private static final long serialVersionUID = 20220831226601L;

    public List<String> porta;  
    
    public Atuador(String nomeAtuador, String descricaoAtuador2, String tipoAtuador2, String localAtuador) {
        this.porta = new ArrayList<>();
        this.porta.add("ABERTA");
        this.porta.add("FECHADA");
        
        this.nomeAtuador = nomeAtuador;
        this.descricaoAtuador = descricaoAtuador2;
        this.tipoAtuador = tipoAtuador2;
        this.localAtuador = localAtuador;
        
    }
   
   
    public String getNomeAtuador() {
        return nomeAtuador;
    }

    public void setNomeAtuador(String nomeAtuador) {
        this.nomeAtuador = nomeAtuador;
    }

    public String getDescricaoAtuador() {
        return descricaoAtuador;
    }

    public void setDescricaoAtuador(String descricaoAtuador) {
        this.descricaoAtuador = descricaoAtuador;
    }

    public String getTipoAtuador() {
        return tipoAtuador;
    }

    public void setTipoAtuador(String tipoAtuador) {
        this.tipoAtuador = tipoAtuador;
    }

    public String getLocalAtuador() {
        return localAtuador;
    }

    public void setLocalAtuador(String localAtuador) {
        this.localAtuador = localAtuador;
    }
    
         
    public void init() {
        /*
           if(tipoAtuador.equals("PORTA")){
                    Random r = new Random();
                    descricaoAtuador = porta.get(r.nextInt(2));
                }
                if(tipoAtuador.equals("TEMPERATURA")){
            
                }*/
        
                //System.out.println(descricaoAtuador);
            
       
    }

    
}
