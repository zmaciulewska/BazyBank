package com.pabwoopj;


import DataAccessObject.jdbcAccountDao;
import DataAccessObject.jdbcClientDao;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.stage.Stage;
import models.Account;
import models.Client;

import java.util.Collections;
import java.util.List;

public class App extends Application {

    private jdbcAccountDao accDao = new jdbcAccountDao();
    private jdbcClientDao cliDao = new jdbcClientDao();
    private Stage window;
    private Scene sceneMenu;
    private Scene scSaveCli, scDelCli, scUpCli, scFindIdCli, scFindNameCli;
    private Scene scSaveAcc, scDelAcc, scUpAcc, scFindIdAcc, scAllAcc;

    public static void main(final String[] argv) {
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Bank - database");
        window = primaryStage;

        //////////////////////
        // CLIENT OPERATIONS
        Label labCli = new Label("Clients");
        labCli.setFont(new Font("Helvetica", 20));
        labCli.setTextFill(Color.web("#00BFFF"));


        //save
        Button buttonSaveCli = new Button();
        buttonSaveCli.setText("Create client");
        buttonSaveCli.setOnAction(e -> window.setScene(scSaveCli));

        GridPane laySaveCli = new GridPane();
        laySaveCli.setAlignment(Pos.CENTER);
        laySaveCli.setVgap(20);
        laySaveCli.setHgap(15);

        Label l1 = new Label("Insert data");
        GridPane.setConstraints(l1, 0, 0);

        TextField tfname = new TextField();
        tfname.setPromptText("First name");
        GridPane.setConstraints(tfname, 0, 1);

        TextField tflname = new TextField();
        tflname.setPromptText("Lastname");
        GridPane.setConstraints(tflname, 0, 2);

        TextField tfpesel = new TextField();
        tfpesel.setPromptText("PESEL");
        GridPane.setConstraints(tfpesel, 0, 3);

        TextField tfmail = new TextField();
        tfmail.setPromptText("email");
        GridPane.setConstraints(tfmail, 0, 4);

        Button accept1= new Button();
        accept1.setText("Accept");
        GridPane.setConstraints(accept1, 0, 5);
        accept1.setOnAction(e -> {
            cliDao.save(new Client(tfname.getText(), tflname.getText(), tfpesel.getText(), tfmail.getText()));
            tfname.clear();
            tflname.clear();
            tfpesel.clear();
            tfmail.clear();
            window.setScene(sceneMenu);
            InformationWindow.display("Information", "Creating new client succeeded " );
        });

        laySaveCli.getChildren().addAll(l1, tfname, tflname, tfpesel, tfmail, accept1);
        scSaveCli = new Scene(laySaveCli, 400, 400);

        //delete
        Button buttonDelCli = new Button();
        buttonDelCli.setText("Delete client");
        buttonDelCli.setOnAction(e -> window.setScene(scDelCli));

        GridPane layDelCli = new GridPane();
        layDelCli.setAlignment(Pos.CENTER);
        layDelCli.setVgap(20);
        layDelCli.setHgap(15);

        Label l2 = new Label("Which account you want to delete?");
        GridPane.setConstraints(l1, 0, 0);

        TextField tfid1 = new TextField();
        tfid1.setPromptText("Id");
        GridPane.setConstraints(tfid1, 0, 1);

        Button accept2= new Button();
        accept2.setText("Accept");
        GridPane.setConstraints(accept2, 0, 2);
        accept2.setOnAction(e -> {
            Client c=cliDao.findById(cliDao.findById(Integer.parseInt(tfid1.getText())));
            tfid1.clear();
            if( c != null) {
                cliDao.delete();
                window.setScene(sceneMenu);
                InformationWindow.display("Information", "Deleting client succeeded " );
            }
            else {
                window.setScene(sceneMenu);
                InformationWindow.display("Information", "Client doesn't exist. " );
            }

        });

        layDelCli.getChildren().addAll(l2, tfid1, accept2);
        scDelCli = new Scene(layDelCli, 400, 400);

        //update
        Button buttonUpCli = new Button();
        buttonUpCli.setText("Update client");
        buttonUpCli.setOnAction(e -> window.setScene(scUpCli));

        GridPane layUpCli = new GridPane();
        layUpCli.setAlignment(Pos.CENTER);
        layUpCli.setVgap(20);
        layUpCli.setHgap(15);

        Label l3 = new Label("Insert data");
        GridPane.setConstraints(l1, 0, 0);

        TextField tfname3 = new TextField();
        tfname3.setPromptText("First name");
        GridPane.setConstraints(tfname3, 0, 1);

        TextField tflname3 = new TextField();
        tflname3.setPromptText("Lastname");
        GridPane.setConstraints(tflname3, 0, 2);

        TextField tfpesel3 = new TextField();
        tfpesel3.setPromptText("PESEL");
        GridPane.setConstraints(tfpesel3, 0, 3);

        TextField tfmail3 = new TextField();
        tfmail3.setPromptText("email");
        GridPane.setConstraints(tfmail3, 0, 4);

        TextField tfid3 = new TextField();
        tfid3.setPromptText("Account ID");
        GridPane.setConstraints(tfid3, 0, 5);

        Button accept3= new Button();
        accept3.setText("Accept");
        GridPane.setConstraints(accept3, 0, 6);
        accept3.setOnAction(e -> {

            Client c=cliDao.findById(Integer.parseInt(tfid3.getText()));
            c.setFirstName(tfname3.getText());
            c.setLastName(tflname3.getText());
            c.setPesel(tfpesel3.getText());
            c.setEmail(tfmail3.getText());
            cliDao.update(c);
            tfname3.clear();
            tflname3.clear();
            tfpesel3.clear();
            tfmail3.clear();
            tfid3.clear();
            window.setScene(sceneMenu);
            InformationWindow.display("Information", "Updating client succeeded " );
        });

        layUpCli.getChildren().addAll(l3, tfname3, tflname3, tfpesel3, tfmail3, tfid3,  accept3);
        scUpCli = new Scene(layUpCli, 400, 400);



        //find by id
        Button findIdCli = new Button();
        findIdCli.setText("Find client by id");
        findIdCli.setOnAction(e -> window.setScene(scFindIdCli));

        GridPane layFindIdCli = new GridPane();
        layFindIdCli.setAlignment(Pos.CENTER);
        layFindIdCli.setVgap(20);
        layFindIdCli.setHgap(15);

        Label l4 = new Label("Which account you want to find?");
        GridPane.setConstraints(l1, 0, 0);

        TextField tfid4 = new TextField();
        tfid4.setPromptText("Id");
        GridPane.setConstraints(tfid4, 0, 1);

        Button accept4 = new Button();
        accept4.setText("Accept");
        GridPane.setConstraints(accept4, 0, 2);
        accept4.setOnAction(e -> {
            Client c = cliDao.findById(Integer.parseInt(tfid4.getText()));
            tfid4.clear();
            window.setScene(sceneMenu);
            if( c != null ) InformationWindow.display("Information", "Account found: \n"+c.toString() );
            else InformationWindow.display("Information", "Account not found. ");
        });

        layFindIdCli.getChildren().addAll(l4, tfid4, accept4);
        scFindIdCli = new Scene(layFindIdCli, 400, 400);

        //find by name
        Button findNameCli = new Button();
        findNameCli.setText("Find client by last name");
        findNameCli.setOnAction(e -> window.setScene(scFindNameCli));

        GridPane layFindNameCli = new GridPane();
        layFindNameCli.setAlignment(Pos.CENTER);
        layFindNameCli.setVgap(20);
        layFindNameCli.setHgap(15);

        Label l5 = new Label("Which client you want to find?");
        GridPane.setConstraints(l1, 0, 0);

        TextField tfsname5 = new TextField();
        tfsname5.setPromptText("Second Name");
        GridPane.setConstraints(tfsname5, 0, 1);

        Button accept5= new Button();
        accept5.setText("Accept");
        GridPane.setConstraints(accept5, 0, 2);
        accept5.setOnAction(e -> {
            List<Client> list=cliDao.findByName(tfsname5.getText());
            tfsname5.clear();
            window.setScene(sceneMenu);
            if( list != null ) InformationWindow.display("Information", Collections.singletonList(list));
            else InformationWindow.display("Information", "Account not found. ");
        });

        layFindNameCli.getChildren().addAll(l5, tfsname5, accept5);
        scFindNameCli = new Scene(layFindNameCli, 400, 400);

        //find all
        Button findAllCli = new Button();
        findAllCli.setText("Get list of all clients");
        findAllCli.setOnAction(e -> {
            List<Client> list = cliDao.findAll();
            if(list.isEmpty()) InformationWindow.display("Information", "Clients not found.");
            else InformationWindow.display("List of clients", Collections.singletonList(list));
        });

        /////////////////////
        // ACCOUNT OPERATIONS
        Label labAcc = new Label("Accounts");
        labAcc.setFont(new Font("Helvetica", 20));
        labAcc.setTextFill(Color.web("#00BFFF"));

        //save
        Button buttonSaveAcc = new Button();
        buttonSaveAcc.setText("Create account");
        buttonSaveAcc.setOnAction(e -> window.setScene(scSaveAcc));

        GridPane laySaveAcc = new GridPane();
        laySaveAcc .setAlignment(Pos.CENTER);
        laySaveAcc .setVgap(20);
        laySaveAcc .setHgap(15);

        Label l6 = new Label("Insert data");
        GridPane.setConstraints(l6, 0, 0);

        TextField tfnotes = new TextField();
        tfnotes.setPromptText("Notes");
        GridPane.setConstraints(tfnotes, 0, 1);

        TextField tfbalance = new TextField();
        tfbalance.setPromptText("Balance");
        GridPane.setConstraints(tfbalance, 0, 2);

        TextField tfidclient = new TextField();
        tfidclient.setPromptText("Owner ID");
        GridPane.setConstraints(tfidclient, 0, 3);

        Button accept6= new Button();
        accept6.setText("Accept");
        GridPane.setConstraints(accept6, 0, 4);
        accept6.setOnAction(e -> {
            if(cliDao.findById(Integer.parseInt(tfidclient.getText())) != null){
                accDao.save(new Account(Integer.parseInt(tfidclient.getText()), tfnotes.getText(), Float.parseFloat(tfbalance.getText())));
                tfnotes.clear();
                tfbalance.clear();
                tfidclient.clear();
                window.setScene(sceneMenu);
                InformationWindow.display("Information", "Creating new account succeeded " );
            }
            else {
                InformationWindow.display("Information", "Client (id:  " +
                        Integer.parseInt(tfidclient.getText()) + " ) doesn't exist.");
                window.setScene(sceneMenu);
            }

        });


        laySaveAcc.getChildren().addAll(l6, tfnotes, tfbalance, tfidclient, accept6);
        scSaveAcc = new Scene(laySaveAcc, 400, 400);




        //delete
        Button buttonDelAcc = new Button();
        buttonDelAcc.setText("Delete account");
        buttonDelAcc.setOnAction(e -> window.setScene(scDelAcc));

        GridPane layDelAcc = new GridPane();
        layDelAcc.setAlignment(Pos.CENTER);
        layDelAcc.setVgap(20);
        layDelAcc.setHgap(15);

        Label l7 = new Label("Insert data");
        GridPane.setConstraints(l6, 0, 0);

        TextField tfid7 = new TextField();
        tfnotes.setPromptText("Notes");
        GridPane.setConstraints(tfnotes, 0, 1);

        Button accept7= new Button();
        accept7.setText("Accept");
        GridPane.setConstraints(accept7, 0, 2);
        accept7.setOnAction(e -> {
            Account a = accDao.findById(Integer.parseInt(tfid7.getText()));
            tfid7.clear();
            if( a!= null){
                accDao.delete(a);

                window.setScene(sceneMenu);
                InformationWindow.display("Information", "Deleting new account succeeded " );
            }
            else {
                InformationWindow.display("Information", "Client doesn't exist.");
                window.setScene(sceneMenu);
            }

        });

        layDelAcc.getChildren().addAll(l7, tfid7, accept7);
        scDelAcc = new Scene(layDelAcc, 400, 400);

        //update
        Button buttonUpAcc = new Button();
        buttonUpAcc.setText("Update account");
        buttonUpAcc.setOnAction(e -> window.setScene(scUpAcc));

        GridPane layUpAcc = new GridPane();
        layUpAcc.setAlignment(Pos.CENTER);
        layUpAcc.setVgap(20);
        layUpAcc.setHgap(15);

        Label l8 = new Label("Insert data");
        GridPane.setConstraints(l6, 0, 0);

        TextField tfnotes8 = new TextField();
        tfnotes8.setPromptText("Notes");
        GridPane.setConstraints(tfnotes8, 0, 1);

        TextField tfbalance8 = new TextField();
        tfbalance8.setPromptText("Balance");
        GridPane.setConstraints(tfbalance8, 0, 2);

        TextField tfidclient8 = new TextField();
        tfidclient8.setPromptText("Owner ID");
        GridPane.setConstraints(tfidclient8, 0, 3);

        TextField tfid8 = new TextField();
        tfid8.setPromptText("Account ID");
        GridPane.setConstraints(tfid8 , 0, 4);

        Button accept8= new Button();
        accept8.setText("Accept");
        GridPane.setConstraints(accept8, 0, 5);
        accept8.setOnAction(e -> {
            Account a = accDao.findById(Integer.parseInt(tfid8.getText()));
            if( a!= null) {
                a.setNotes(tfnotes8.getText());
                a.setBalance(Float.parseFloat(tfbalance8.getText()));
                a.setIdClient(Integer.parseInt(tfidclient8.getText()));
                Client c = cliDao.findById(Integer.parseInt(tfidclient8.getText()));
                if(c !=null) {
                    accDao.update(a);
                    window.setScene(sceneMenu);
                    InformationWindow.display("Information", "Updating account succeeded " );
                }
                else {
                    InformationWindow.display("Information", "Client doesn't exist.");
                    window.setScene(sceneMenu);
                }
            }
            else {
                InformationWindow.display("Information", "Account (id:  " +
                        Integer.parseInt(tfid8.getText()) + " ) doesn't exist.");
                window.setScene(sceneMenu);
            }
            tfid8.clear();
            tfnotes8.clear();
            tfbalance8.clear();
            tfidclient8.clear();

        });


        layUpAcc.getChildren().addAll(l8, tfnotes8, tfbalance8, tfidclient8, tfid8, accept8);
        scUpAcc = new Scene(layUpAcc, 400, 400);

        //find by id
        Button findIdAcc = new Button();
        findIdAcc.setText("Find account by id");
        findIdAcc.setOnAction(e -> window.setScene(scFindIdAcc));

        GridPane layFindIdAcc = new GridPane();
        layFindIdAcc.setAlignment(Pos.CENTER);
        layFindIdAcc.setVgap(20);
        layFindIdAcc.setHgap(15);

        Label l9 = new Label("Which account you want to find?");
        GridPane.setConstraints(l9, 0, 0);

        TextField tfid9 = new TextField();
        tfid9.setPromptText("Account Id");
        GridPane.setConstraints(tfid9, 0, 1);

        Button accept9 = new Button();
        accept9.setText("Accept");
        GridPane.setConstraints(accept9, 0, 2);
        accept9.setOnAction(e -> {
            Account a = accDao.findById(Integer.parseInt(tfid9.getText()));
            tfid9.clear();
            window.setScene(sceneMenu);
            if( a != null ) InformationWindow.display("Information", "Account found: \n" +  a.toString());
            else InformationWindow.display("Information", "Account not found. ");
        });

        layFindIdAcc.getChildren().addAll(l9, tfid9, accept9);
        scFindIdAcc = new Scene(layFindIdAcc, 400, 400);

        //find all
        Button findAllAcc = new Button();
        findAllAcc.setText("Get list of all accounts");
        findAllAcc.setOnAction(e -> {
            List<Account> list = accDao.findAll();
            if(list.isEmpty()) InformationWindow.display("Information", "Accounts not found.");
            else InformationWindow.display("List of accounts", Collections.singletonList(list));
        });



        ////////////////
        // MENU
        VBox layoutMenu = new VBox();
        layoutMenu.setSpacing(10);
        layoutMenu.setAlignment(Pos.CENTER);
        layoutMenu.getChildren().addAll(
                labCli, buttonSaveCli, buttonDelCli, buttonUpCli, findIdCli, findNameCli, findAllCli,
                labAcc, buttonSaveAcc, buttonDelAcc, buttonUpAcc, findIdAcc, findAllAcc);
        sceneMenu = new Scene(layoutMenu, 400, 500);
        window.setScene(sceneMenu);
        window.show();
    }

}