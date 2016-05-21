/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myhometop;

import java.io.Serializable;


/**
 *
 * @author hp
 */
public class Sensor implements Serializable{
    
    public String nomeSensor;
    public String descricaoSensor;
    public String tipoSensor;
    public String localSensor;
    
    private static final long serialVersionUID = 20120731125500L;

    public Sensor(String nomeSensor2, String descrdescricaoSensoricao2, String tipoSensor2, String localSensor2) {
        nomeSensor = nomeSensor2;
        descricaoSensor = descrdescricaoSensoricao2;
        tipoSensor = tipoSensor2;
        localSensor = localSensor2;
    }
    
 
    public String getNomeSensor() {
        return nomeSensor;
    }

    public void setNomeSensor(String nomeSensor) {
        this.nomeSensor = nomeSensor;
    }

    public String getDescricaoSensor() {
        if(this.tipoSensor.equalsIgnoreCase("porta")){
            if(descricaoSensor.equalsIgnoreCase("abrir")){
                this.descricaoSensor = "ABERTA";
            }
            if(descricaoSensor.equalsIgnoreCase("fechar")){
                this.descricaoSensor = "FECHADA";
            }
        }
         if(this.tipoSensor.equalsIgnoreCase("luz")){
            if(descricaoSensor.equalsIgnoreCase("ligar")){
                this.descricaoSensor = "LIGADA";
            }
            if(descricaoSensor.equalsIgnoreCase("desligar")){
                this.descricaoSensor = "DESLIGADA";
            }
        }
        return descricaoSensor;
    }

    public void setDescricaoSensor(String descricaoSensor) {
       
        this.descricaoSensor = descricaoSensor;
    }


    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public String getLocalSensor() {
        return localSensor;
    }

    public void setLocalSensor(String localSensor) {
        this.localSensor = localSensor;
    }

    
 
  
    
    
}

