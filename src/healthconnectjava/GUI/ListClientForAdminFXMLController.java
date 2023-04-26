/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.Client;
import healthconnectjava.services.ServiceUser;
import healthconnectjava.utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author wiki
 */
public class ListClientForAdminFXMLController implements Initializable {
    
    ServiceUser su = new ServiceUser();
    
    public Connection conx;
    @FXML
    private TextField recherche;
    @FXML
    private Text NomPrenomUSerr;
    @FXML
    private Text emailUserr;
    @FXML
    private Pagination table_pagi;
    
    public ListClientForAdminFXMLController() {
        conx = MyDB.getInstance().getConx();
    }

    @FXML
    private Button gestionClient;
    @FXML
    private Button gestionCoach;
    @FXML
    private Button logoutB;
    @FXML
    private TableView<Client> listClient;
    @FXML
    private TableColumn<Client, String> Nom;
    @FXML
    private TableColumn<Client, String> Prenom;
    @FXML
    private TableColumn<Client, String> Tel;
    @FXML
    private TableColumn<Client, String> Email;
    @FXML
    private TableColumn<Client, String> IsVerified;
    @FXML
    private TableColumn<Client, String> Action;
    @FXML
    private Button refreshListClient;
    
    ObservableList<Client> ClientList;
    
    Client client = null ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NomPrenomUSerr.setText(su.getNom() +" "+su.getPrenom());
        emailUserr.setText(su.getEmail());
        
        loadDate();
        ClientSearch();
    }    
    
    private void loadDate() {
        ClientList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM user JOIN client ON user.id = client.id WHERE type = 'client'";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                Client co = new Client();
            
                co.setId(rs.getInt("id"));
                co.setNom(rs.getString("nom"));
                co.setPrenom(rs.getString("prenom"));
                co.setEmail(rs.getString("email"));
                co.setTelephone(rs.getString("telephone"));
                co.setIsVerified(rs.getBoolean("is_verified"));
                
                ClientList.add(co);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        IsVerified.setCellValueFactory(new PropertyValueFactory<>("isVerified"));
        
        listClient.setItems(ClientList);
        
        Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFoctory = (TableColumn<Client, String> param) -> {

            final TableCell<Client, String> cell = new TableCell<Client, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button activerClient = new Button("Activer");
                        Button desactiverClient = new Button("Désactiver");

                        activerClient.setStyle(
                            "-fx-background-color: #2196f3;"+
                            "-fx-font-family: Tahoma;"+
                            "-fx-font-size: 12px;"+
                            "-fx-text-fill: #FFF;"+
                            "-fx-background-radius: 20px;"
                        );
                        desactiverClient.setStyle(
                            "-fx-background-color: #2196f3;"+
                            "-fx-font-family: Tahoma;"+
                            "-fx-font-size: 12px;"+
                            "-fx-text-fill: #FFF;"+
                            "-fx-background-radius: 20px;"
                        );
                        
                        activerClient.setOnAction((ActionEvent event) -> {
                            try {
                                client = listClient.getSelectionModel().getSelectedItem();
                                String req = "UPDATE user JOIN client ON user.id = client.id SET user.is_verified = true WHERE user.id = "+client.getId();
                                PreparedStatement pst = conx.prepareStatement(req);
                                System.out.println("Client activé");
                                pst.executeUpdate();
                                loadDate();
                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                        });
                        
                        desactiverClient.setOnAction((ActionEvent event) -> {
                            try {
                                client = listClient.getSelectionModel().getSelectedItem();
                                String req = "UPDATE user JOIN client ON user.id = client.id SET user.is_verified = false WHERE user.id = "+client.getId();
                                PreparedStatement pst = conx.prepareStatement(req);
                                System.out.println("Client desactivé");
                                pst.executeUpdate();
                                loadDate();
                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                        });
                       

                        HBox managebtn = new HBox(activerClient, desactiverClient);
                        managebtn.setStyle("-fx-alignment:center");

                        setGraphic(managebtn);
                        setText(null);
                    }
                }

            };

            return cell;
        };
        Action.setCellFactory(cellFoctory);
        listClient.setItems(ClientList);
        
        int itemsPerPage = 2; // number of rows per page
        table_pagi.setPageCount((int) Math.ceil((double) ClientList.size() / itemsPerPage));
        table_pagi.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage, ClientList.size());
            listClient.setItems(FXCollections.observableArrayList(ClientList.subList(fromIndex, toIndex)));
            return listClient;
        });
    }

    @FXML
    private void gestClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListClientForAdminFXML.fxml"));
            Parent root = loader.load();
            gestionClient.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void gestCoach(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCoachForAdminFXML.fxml"));
            Parent root = loader.load();
            gestionCoach.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = loader.load();
            logoutB.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void refreshList(ActionEvent event) {
        loadDate();
    }
    
    public void ClientSearch() {

        FilteredList<Client> filter = new FilteredList<>(ClientList, e -> true);

        recherche.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateClientData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateClientData.getNom().toLowerCase().startsWith(searchKey)) {
                    return true;
                } 
                else {
                    return false;
                }
            });
        });

        SortedList<Client> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(listClient.comparatorProperty());
        listClient.setItems(sortList);
    }

    
}
