package com.example.gestionvente.gui;

import com.example.gestionvente.entites.Produit;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ProduitFrontController {
    @FXML
    private TableColumn<?, ?> menuColProduitName;

    @FXML
    private TableColumn<?, ?> menuColProduitPrix;

    @FXML
    private TableColumn<?, ?> menuColProduitQuantite;

    @FXML
    private Pane menuForm;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private TableView<?> menuTableView;

    @FXML
    private Label menuTot;

    private ObservableList<Produit> cardListData;
}
