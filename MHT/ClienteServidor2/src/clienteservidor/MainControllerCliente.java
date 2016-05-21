/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteservidor;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.SwingUtilities;

import myhometop.Sensor; 
import myhometop.Atuador; 



import myhometop.ServerActions;

/**
 *
 * @author hp
 */
public class MainControllerCliente implements Initializable, Serializable{

    
    private static final long serialVersionUID = 20220831227701L;
    
    private static ServerActions sa;
    
    @FXML 
    private static TableView<Sensor> tabelaSensor;
        
    @FXML 
    private TableColumn<Sensor, String> nomeSensor;
    @FXML 
    private TableColumn<Sensor, String> descricaoSensor;
    @FXML 
    private TableColumn<Sensor, String> tipoSensor;
    @FXML 
    private TableColumn<Sensor, String> localSensor;
    
    
    @FXML 
    private static TableView<Atuador> tabelaAtuador;
    
    @FXML 
    private TableColumn<Atuador, String> nomeAtuador;
    @FXML 
    private TableColumn<Atuador, String> descricaoAtuador;
    @FXML 
    private TableColumn<Atuador, String> tipoAtuador;
    @FXML 
    private TableColumn<Atuador, String> localAtuador;
   
   
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      nomeSensor.setCellValueFactory(new PropertyValueFactory<Sensor, String>("nomeSensor"));
      descricaoSensor.setCellValueFactory(new PropertyValueFactory<Sensor, String>("descricaoSensor"));
      tipoSensor.setCellValueFactory(new PropertyValueFactory<Sensor, String>("tipoSensor"));
      localSensor.setCellValueFactory(new PropertyValueFactory<Sensor, String>("localSensor"));
      
      
      nomeAtuador.setCellValueFactory(new PropertyValueFactory<Atuador, String>("nomeAtuador"));
      descricaoAtuador.setCellValueFactory(new PropertyValueFactory<Atuador, String>("descricaoAtuador"));
      tipoAtuador.setCellValueFactory(new PropertyValueFactory<Atuador, String>("tipoAtuador"));
      localAtuador.setCellValueFactory(new PropertyValueFactory<Atuador, String>("localAtuador"));
    }
    
    public static void addSensorTable(Sensor s){
     
        tabelaSensor.getItems().add(s);
    }

    public static void addAtuadorTable(Atuador a){
        tabelaAtuador.getItems().add(a);
    }
    
    public static void forceRefresh(ArrayList<Sensor> ls, ArrayList<Atuador> la) {     
         ObservableList<Sensor> data2 = FXCollections.observableArrayList();

           for(Sensor s : ls){
               data2.add(s);
           }
               tabelaSensor.getItems().clear();
               tabelaSensor.setItems(data2); 
  
         ObservableList<Atuador> data3 = FXCollections.observableArrayList();
           for(Atuador a : la){
               data3.add(a);
           }
                tabelaAtuador.getItems().clear();
                tabelaAtuador.setItems(data3);  
          
    }
    
    
    @FXML
    private static TextField novoSensorNome;
    @FXML
    private static TextField novoSensorDescricao;
    @FXML
    private static TextField novoSensorTipo;
    @FXML
    private static TextField novoSensorLocal;
    
    @FXML
    private static TextField novoAtuadorNome;
    @FXML
    private static TextField novoAtuadorDescricao;
    @FXML
    private static TextField novoAtuadorTipo;
    @FXML
    private static TextField novoAtuadorLocal; 

    @FXML
    private static TextField estadoAtuador;   
    
    public Atuador aa;
    public Sensor ss;
    
    @FXML
    public static void adicionarSA() throws RemoteException{  
        if(validate() && novoSensorTipo.getText().equalsIgnoreCase(novoAtuadorTipo.getText()) && 
           novoSensorLocal.getText().equalsIgnoreCase(novoAtuadorLocal.getText())){
        
            Sensor s = new Sensor(novoSensorNome.getText(), novoSensorDescricao.getText(), novoSensorTipo.getText(), novoSensorLocal.getText());
            Atuador a = new Atuador(novoAtuadorNome.getText(), novoAtuadorDescricao.getText(), novoAtuadorTipo.getText(), novoAtuadorLocal.getText());
            boolean x = true;
                //PREVENT DUPLICATES
                for(Map.Entry<Sensor, Atuador> entry : sa.getSpotConcat().entrySet()) {
                  if(entry.getKey().getNomeSensor().equals(s.getNomeSensor()) && 
                      entry.getValue().getNomeAtuador().equals(a.getNomeAtuador())){
                          x = false;  
                    }
                }
              if(x){
                sa.concatSA(s, a, "adicionar");  
                sa.atualizarTabelas();
                forceRefresh(sa.getListaSensor(), sa.getListaAtuador());
            }
        }
    }
    
    private static boolean validate(){
        return !("".equals(novoSensorNome.getText()) || "".equals(novoSensorDescricao.getText()) || "".equals(novoSensorTipo.getText()) || "".equals(novoSensorLocal.getText()) ||
                 "".equals(novoAtuadorNome.getText()) || "".equals(novoAtuadorDescricao.getText()) || "".equals(novoAtuadorTipo.getText()) || "".equals(novoAtuadorLocal.getText()));
         }
 
    @FXML
    public void alterarAtuador() throws RemoteException{    
        if(!tabelaAtuador.getSelectionModel().isEmpty()) {       
            String nomeAtuadorSelecionado = tabelaAtuador.getSelectionModel().getSelectedItem().getNomeAtuador();
            String localAtuadorSelecionado = tabelaAtuador.getSelectionModel().getSelectedItem().getLocalAtuador();
            String tipoAtuadorSelecionado = tabelaAtuador.getSelectionModel().getSelectedItem().getTipoAtuador();
            
            for (int i = 0; i < sa.getListaAtuador().size(); i++) {
                if (sa.getListaAtuador().get(i).getNomeAtuador().equalsIgnoreCase(nomeAtuadorSelecionado)) 
                {                     
                 aa = sa.getListaAtuador().get(i);
                 aa.setDescricaoAtuador(estadoAtuador.getText());
               }
            }

            for (int i = 0; i < sa.getListaSensor().size(); i++) {
                if (sa.getListaSensor().get(i).getLocalSensor().equalsIgnoreCase(localAtuadorSelecionado) &&
                    sa.getListaSensor().get(i).getTipoSensor().equalsIgnoreCase(tipoAtuadorSelecionado)) 
                {
                 ss = sa.getListaSensor().get(i);
                 ss.setDescricaoSensor(aa.getDescricaoAtuador());
               }
            }
                sa.concatSA(ss, aa, "atualizar");  
                sa.atualizarTabelas();
                forceRefresh(sa.getListaSensor(), sa.getListaAtuador());
                    
        }else {
            System.out.println("Falha, selecione um campo preenchido na tabela");
        } 
    }
    

      public static void cliente1() throws Exception{
            
        Registry r = LocateRegistry.getRegistry("localhost", 222);
        sa = (ServerActions) r.lookup("CASA");
       
        
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try { 
                                sa.atualizarTabelas();
                                forceRefresh(sa.getListaSensor(), sa.getListaAtuador());
                        
                            } catch (RemoteException ex) {
                                Logger.getLogger(LoginControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                }
            }, 0, 5000);
        
      }
        //THE END   
 
}
