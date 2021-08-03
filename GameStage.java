/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phase10;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author yalinzhang
 */
public class GameStage extends javax.swing.JFrame {
    ArrayList<Card> selectedCards = new ArrayList<>();
    ArrayList<JButton> cardButtons = new ArrayList<>();
    private AddPlayerNames addPlayers = new AddPlayerNames();
    

    Player current;
    private ArrayList<Player> opponents;
    private Player opponent1;
    private Player opponent2;
    private Player opponent3;
    private int phaseGroupIndex;
    private GuiManager gManage;
    private PhaseGroup newPhaseGroup;
    private PhaseGroup newPhaseGroup2;
    private boolean isSecondPhaseGroup;
    private boolean isPhasing;
    private boolean isAtBeginning;
    /**
     * Creates new form GameStage
     */
    public GameStage(){}
    public GameStage(final GuiManager gManage){
         opponents = new ArrayList<>();
         this.gManage = gManage;
         isSecondPhaseGroup = false;
         current = gManage.mainManager.getGame().getCurrentPlayer();
         
//         opponent2=new Player(gManage.mainManager.getGame(), "");
//         opponent3=new Player(gManage.mainManager.getGame(),"");
         for(int i = 0; i< gManage.mainManager.getGame().getNumberOfPlayers(); i++){
             if(gManage.mainManager.getGame().getPlayer(i)!=gManage.mainManager.getGame().getCurrentPlayer()){
                 opponents.add(gManage.mainManager.getGame().getPlayer(i));
             }
         
        
         }
         if(opponents.size()==1){
             opponent1 = opponents.get(0);
             opponent2=new Player(gManage.mainManager.getGame(), "");
             opponent3=new Player(gManage.mainManager.getGame(),"");
             initComponents();
             oppName2.setVisible(false);
             numCard2.setVisible(false);
             oppPhase2.setVisible(false);
             score2.setVisible(false);
             oppName3.setVisible(false);
             numCard3.setVisible(false);
             oppPhase3.setVisible(false);
             score3.setVisible(false);
             
             
         }
         else if(opponents.size()==2){
             opponent1 = opponents.get(0);
             opponent2 = opponents.get(1);
             opponent3=new Player(gManage.mainManager.getGame(), "");
             initComponents();
             oppName3.setVisible(false);
             oppPhase3.setVisible(false);
             numCard3.setVisible(false);
             score3.setVisible(false);
             
             
         }
         else if(opponents.size()==3){
             
              opponent1 = opponents.get(0);
             opponent2 = opponents.get(1);
             opponent3 = opponents.get(2);
             initComponents();
         }
//         startWithNoPhaseGroup();
         populateArrayList();
         updateFrame(gManage.mainManager.getGame());

         
         
    }
    
    private void startWithNoPhaseGroup(){
        pg1a1.setVisible(false);
        pg1a2.setVisible(false);
        pg1a3.setVisible(false);
        pg1a4.setVisible(false);
        
        pg1b1.setVisible(false);
        pg1b2.setVisible(false);
        pg1b3.setVisible(false);
        pg1b4.setVisible(false);
        
        pg2a1.setVisible(false);
        pg2a2.setVisible(false);
        pg2a3.setVisible(false);
        pg2a4.setVisible(false);
        
        pg2b1.setVisible(false);
        pg2b2.setVisible(false);
        pg2b3.setVisible(false);
        pg2b4.setVisible(false);
        
        lblTo1.setVisible(false);
        lblTo2.setVisible(false);
        lblTo3.setVisible(false);
        lblTo4.setVisible(false);
        lblTo5.setVisible(false);
        lblTo6.setVisible(false);
        lblTo7.setVisible(false);
        lblTo8.setVisible(false);
        
        AddToPhase1Opp1.setVisible(false);
        AddToPhase1Opp2.setVisible(false);
        AddToPhase1Opp3.setVisible(false);
        AddToPhase2Opp1.setVisible(false);
        AddToPhase2Opp2.setVisible(false);
        AddToPhase2Opp3.setVisible(false);
        
        AddToYourPhase1.setVisible(false);
        AddToYourPhase2.setVisible(false); 
    }

    
    public void updateFrame(Phase10 currentGame){
        current = currentGame.getCurrentPlayer();
        opponents = new ArrayList<>();
        
        //update info panel
        lblPlayerName.setText("Name: " + current.getName());
        phaseNumber.setText(Integer.toString(current.getPhase()));
        scoreNumber.setText(Integer.toString(current.getScore()));
        
        if(current.getHasDrawnCard())
            lblTurnMode.setText("You may Discard");
        else{
            lblTurnMode.setText("You may Draw");
        }
        
        
        //update opponent info panel
        for(int i = 0; i< currentGame.getNumberOfPlayers(); i++){
             if(currentGame.getPlayer(i)!=current){
                 opponents.add(gManage.mainManager.getGame().getPlayer(i));
             }

         }
         if(opponents.size()==1){
             opponent1 = opponents.get(0);
             oppName1.setText("Name: " + opponent1.getName());
             oppPhase1.setText("Phase: " + opponent1.getPhase());
             numCard1.setText("Cards in Hand: " + opponent1.getHand().getNumberOfCards());
             score1.setText("Score: " + opponent1.getScore());
             
             phaseAreaUpdate(opponent1);
         }
         else if(opponents.size()==2){
             opponent1 = opponents.get(0);
             opponent2 = opponents.get(1);
             
             oppName1.setText("Name: " +opponent1.getName());
             oppPhase1.setText("Phase: " + opponent1.getPhase());
             numCard1.setText("Cards in Hand: " + opponent1.getHand().getNumberOfCards());
             score1.setText("Score: " + opponent1.getScore());
             
             
             oppName2.setText("Name: " +opponent2.getName());
             oppPhase2.setText("Phase: " + opponent2.getPhase());
             numCard2.setText("Cards in Hand: " + opponent2.getHand().getNumberOfCards());
             score2.setText("Score: " + opponent2.getScore());
             phaseAreaUpdate(opponent1);
             phaseAreaUpdate(opponent2);
             

         }
         else if(opponents.size()==3){
             
              opponent1 = opponents.get(0);
             opponent2 = opponents.get(1);
             opponent3 = opponents.get(2);
             
             oppName1.setText("Name: " +opponent1.getName());
             oppPhase1.setText("Phase: " + opponent1.getPhase());
             numCard1.setText("Cards in Hand: " + opponent1.getHand().getNumberOfCards());
             score1.setText("Score: " + opponent1.getScore());
             
             
             oppName2.setText("Name: " +opponent2.getName());
             oppPhase2.setText("Phase: " + opponent2.getPhase());
             numCard2.setText("Cards in Hand: " + opponent2.getHand().getNumberOfCards());
             score2.setText("Score: " + opponent2.getScore());
             
             oppName3.setText("Name: " +opponent3.getName());
             oppPhase3.setText("Phase: " + opponent3.getPhase());
             numCard3.setText("Cards in Hand: " + opponent3.getHand().getNumberOfCards());
             score3.setText("Score: " + opponent3.getScore());
             
             phaseAreaUpdate(opponent1);
             phaseAreaUpdate(opponent2);
             phaseAreaUpdate(opponent3);
         }
         
        //when draw a card
        updateCardImages();
        
        updatecurrentPlayerPhase();
        
        //update cards
        
        for(int i = 0; i < cardButtons.size(); i++){
            cardButtons.get(i).setSelected(false);
        }
        selectedCards.clear();
        
        if(currentGame.getRound().getTopOfDiscardStack() == null){
            discardButton.setIcon(new ImageIcon("/Users/yalinzhang/Desktop/cardImages/NoCardsLeft.png"));
        }
        else{
            discardButton.setIcon(new ImageIcon(getCardFile(currentGame.getRound().getTopOfDiscardStack())));
            discardButton.setEnabled(true);
        }
        
        
        deckCardButton.setEnabled(true);
        
        
    }
    /** Add all cards into cardButtons*/
    public void populateArrayList(){
        cardButtons.add(jButton1);
        cardButtons.add(jButton2);
        cardButtons.add(jButton3);
        cardButtons.add(jButton4);
        cardButtons.add(jButton5);
        cardButtons.add(jButton6);
        cardButtons.add(jButton7);
        cardButtons.add(jButton8);
        cardButtons.add(jButton9);
        cardButtons.add(jButton10);
        cardButtons.add(jButton11);
    }
    
    public void phaseAreaUpdate(Player opp){
        if(opp.hasPlacedDownPhase()){
            if(opp.getNumberOfPhaseGroups()==1){
                if(opp == opponent1){
                    pg1a1.setIcon(new ImageIcon(getCardFile(opponent1.getPhaseGroup(0).getCard(0))));
                    pg1b1.setIcon(new ImageIcon(getCardFile(opponent1.getPhaseGroup(0).getCard(opp.getPhaseGroup(0).getNumberOfCards()-1))));
                    
                    pg2a1.setIcon(null);
                    pg2b1.setIcon(null);
//                    pg1a1.setVisible(true);
//                    lblTo1.setVisible(true);
//                    pg1b1.setVisible(true);
//                    AddToPhase1Opp1.setVisible(true);
                }
                else if(opp == opponent2){
                    pg1a2.setIcon(new ImageIcon(getCardFile(opponent2.getPhaseGroup(0).getCard(0))));
                    pg1b2.setIcon(new ImageIcon(getCardFile(opponent2.getPhaseGroup(0).getCard(opp.getPhaseGroup(0).getNumberOfCards()-1))));
                    
                    pg2a2.setIcon(null);
                    pg2b2.setIcon(null);
//                    pg1a2.setVisible(true);
//                    lblTo3.setVisible(true);
//                    pg1b2.setVisible(true);
//                    AddToPhase1Opp2.setVisible(true);
                }
                else if(opp == opponent3){
                    pg1a3.setIcon(new ImageIcon(getCardFile(opponent3.getPhaseGroup(0).getCard(0))));
                    pg1b3.setIcon(new ImageIcon(getCardFile(opponent3.getPhaseGroup(0).getCard(opp.getPhaseGroup(0).getNumberOfCards()-1))));
                    
                    pg2a3.setIcon(null);
                    pg2b3.setIcon(null);
//                    pg1a3.setVisible(true);
//                    lblTo5.setVisible(true);
//                    pg1b3.setVisible(true);
//                    AddToPhase1Opp3.setVisible(true);
                }
                
            }
            else if(opp.getNumberOfPhaseGroups()==2){
                if(opp == opponent1){
                    pg1a1.setIcon(new ImageIcon(getCardFile(opponent1.getPhaseGroup(0).getCard(0))));
                    pg1b1.setIcon(new ImageIcon(getCardFile(opponent1.getPhaseGroup(0).getCard(opp.getPhaseGroup(0).getNumberOfCards()-1))));
                    
                    pg2a1.setIcon(new ImageIcon(getCardFile(opponent1.getPhaseGroup(1).getCard(0))));
                    pg2b1.setIcon(new ImageIcon(getCardFile(opponent1.getPhaseGroup(1).getCard(opp.getPhaseGroup(1).getNumberOfCards()-1))));
                    
//                    pg1a1.setVisible(true);
//                    lblTo1.setVisible(true);
//                    pg1b1.setVisible(true);
//                    AddToPhase1Opp1.setVisible(true);
                    
//                    pg2a1.setVisible(true);
//                    lblTo2.setVisible(true);
//                    pg2b1.setVisible(true);
//                    AddToPhase2Opp1.setVisible(true);
                }
                else if(opp == opponent2){
                    pg1a2.setIcon(new ImageIcon(getCardFile(opponent2.getPhaseGroup(0).getCard(0))));
                    pg1b2.setIcon(new ImageIcon(getCardFile(opponent2.getPhaseGroup(0).getCard(opp.getPhaseGroup(0).getNumberOfCards()-1))));
                    
                    pg2a2.setIcon(new ImageIcon(getCardFile(opponent2.getPhaseGroup(1).getCard(0))));
                    pg2b2.setIcon(new ImageIcon(getCardFile(opponent2.getPhaseGroup(1).getCard(opp.getPhaseGroup(1).getNumberOfCards()-1))));
                    
                    
//                    pg1a2.setVisible(true);
//                    lblTo3.setVisible(true);
//                    pg1b2.setVisible(true);
//                    AddToPhase1Opp2.setVisible(true);
//                    
//                    pg2a2.setVisible(true);
//                    lblTo4.setVisible(true);
//                    pg2b2.setVisible(true);
//                    AddToPhase2Opp2.setVisible(true);
                }
                else if(opp == opponent3){
                    pg1a3.setIcon(new ImageIcon(getCardFile(opponent3.getPhaseGroup(0).getCard(0))));
                    pg1b3.setIcon(new ImageIcon(getCardFile(opponent3.getPhaseGroup(0).getCard(opp.getPhaseGroup(0).getNumberOfCards()-1))));
                    
                    pg2a3.setIcon(new ImageIcon(getCardFile(opponent3.getPhaseGroup(1).getCard(0))));
                    pg2b3.setIcon(new ImageIcon(getCardFile(opponent3.getPhaseGroup(1).getCard(opp.getPhaseGroup(1).getNumberOfCards()-1))));
                    
//                    pg1a3.setVisible(true);
//                    lblTo5.setVisible(true);
//                    pg1b3.setVisible(true);
//                    AddToPhase1Opp3.setVisible(true);
//                    
//                    pg2a3.setVisible(true);
//                    lblTo6.setVisible(true);
//                    pg2b3.setVisible(true);
//                    AddToPhase2Opp3.setVisible(true);
                }
            }
//            else{
//                startWithNoPhaseGroup();
//            }
        }
        else{
            if(opp == opponent1){
            pg1a1.setIcon(null);
            pg1b1.setIcon(null);
            pg2a1.setIcon(null);
            pg2b1.setIcon(null);
            lblTo1.setIcon(null);
            lblTo2.setIcon(null);
            }
            else if(opp == opponent2){
            pg1a2.setIcon(null);
            pg1b2.setIcon(null);
            pg2a2.setIcon(null);
            pg2b2.setIcon(null);
            lblTo3.setIcon(null);
            lblTo4.setIcon(null);    
            }
            else if(opp==opponent3){
            pg1a3.setIcon(null);
            pg1b3.setIcon(null);
            pg2a3.setIcon(null);
            pg2b3.setIcon(null);
            lblTo5.setIcon(null);
            lblTo6.setIcon(null);
            }
        }
    }
    
    
    
    public void updateCardImages(){
        Hand currentHand = current.getHand();
        
        //update cards on hand
        int i =0;
        for(; i < currentHand.getNumberOfCards(); i++){
            cardButtons.get(i).setVisible(true);
            
            cardButtons.get(i).setIcon(new ImageIcon(getCardFile(currentHand.getCard(i))));
            
//           cardButtons.get(i).setSelectedIcon(new ImageIcon((getSelectedCardFile(currentHand.getCard(i)))));
        }

        for(; i< cardButtons.size(); i++){
            cardButtons.get(i).setVisible(false);
        }

        if(gManage.mainManager.getGame().getRound().getTopOfDiscardStack() == null){
           discardButton.setIcon(new ImageIcon(("/Users/yalinzhang/Desktop/cardImages/NoCardsLeft.png")));
        }
       else{
            discardButton.setIcon(new ImageIcon((getCardFile(gManage.mainManager.getGame().getRound().getTopOfDiscardStack()))));

        }
        
    }
    
    protected void updatecurrentPlayerPhase(){
        if(current.getHasDrawnCard()){
            btnNewPhase.setVisible(true);
//            AddToYourPhase1.setVisible(false);
//            AddToYourPhase2.setVisible(false);
            
        }
        else{
            btnNewPhase.setVisible(false);
        }
        
        if(current.hasPlacedDownPhase()){
            btnNewPhase.setVisible(false);
           if(current.getNumberOfPhaseGroups() == 1){
//           pg1a4.setVisible(true);
//           lblTo7.setVisible(true);
//           pg1b4.setVisible(true);
//           AddToYourPhase1.setVisible(true);
//           
//           pg2a4.setVisible(false);
//           lblTo8.setVisible(false);
//           pg2b4.setVisible(false);
//           AddToYourPhase2.setVisible(false);
           
           pg1a4.setIcon(new ImageIcon(getCardFile(current.getPhaseGroup(0).getCard(0))));
           pg1b4.setIcon(new ImageIcon(getCardFile(current.getPhaseGroup(0).getCard(current.getPhaseGroup(0).getNumberOfCards() -1))));
           
           pg2a4.setIcon(null);
           pg2b4.setIcon(null);
        }
           else{
//           pg1a4.setVisible(true);
//           lblTo7.setVisible(true);
//           pg1b4.setVisible(true);
//           AddToYourPhase1.setVisible(true);
           
           pg1a4.setIcon(new ImageIcon(getCardFile(current.getPhaseGroup(0).getCard(0))));
           pg1b4.setIcon(new ImageIcon(getCardFile(current.getPhaseGroup(0).getCard(current.getPhaseGroup(0).getNumberOfCards() -1))));
           
           
//           pg2a4.setVisible(true);
//           lblTo8.setVisible(true);
//           pg2b4.setVisible(true);
//           AddToYourPhase2.setVisible(true);
           
           pg2a4.setIcon(new ImageIcon(getCardFile(current.getPhaseGroup(1).getCard(0))));
           pg2b4.setIcon(new ImageIcon(getCardFile(current.getPhaseGroup(1).getCard(current.getPhaseGroup(1).getNumberOfCards() -1))));
           
           
           }
        }
        
        else{
////           pg1a4.setVisible(false);
////           lblTo7.setVisible(false);
////           pg1b4.setVisible(false);
////           AddToYourPhase1.setVisible(false); 
////           
////           pg2a4.setVisible(false);
////           lblTo8.setVisible(false);
////           pg2b4.setVisible(false);
////           AddToYourPhase2.setVisible(false);
            pg1a4.setIcon(null);
            pg1b4.setIcon(null);
            pg2a4.setIcon(null);
            pg2b4.setIcon(null);
            lblTo7.setIcon(null);
            lblTo8.setIcon(null);
        }
        
    }
    
    
    
    String getCardFile(Card c){
        String fileName = "/Users/yalinzhang/Desktop/cardImages/";
        
        if(c.getColor().equals(Color.RED)){
            fileName += "Red";
        }
        else if(c.getColor().equals(Color.BLUE)){
            fileName += "Blue";
        }
        else if(c.getColor().equals(Color.YELLOW)){
            fileName += "Yellow";
        }
        else if(c.getColor().equals(Color.GREEN)){
            fileName += "Green";
        }
        
        if(c.getValue() == 13){
            fileName += "Wild";
        }
        else if(c.getValue() == 14){
            fileName += "Skip";
        }
        else{
            fileName += c.getValue();
        }
        fileName +=".png";
        
        return fileName;
        
    }
    
    private String getSelectedCardFile(Card c){
        String fileName = getCardFile(c);
        fileName = fileName.substring(0, fileName.length()-4);
        fileName += "Selected.png";
        
        return fileName;
    }
    protected void hideAndClearSelectedCards() {
		for(int i = 0; i < cardButtons.size(); i++) {
			cardButtons.get(i).setVisible(true);
			cardButtons.get(i).setSelected(false);
		}
		selectedCards.clear();
	}
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        deckCardButton = new javax.swing.JButton();
        discardButton = new javax.swing.JButton();
        lblTurnMode = new javax.swing.JLabel();
        textAreaPhase = new javax.swing.JScrollPane();
        phaseNumber = new javax.swing.JTextArea();
        phaseDescription = new javax.swing.JButton();
        textAreaScore = new javax.swing.JScrollPane();
        scoreNumber = new javax.swing.JTextArea();
        scoreBoard = new javax.swing.JButton();
        lblPhase = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        opponentPanel = new javax.swing.JPanel();
        oppName1 = new javax.swing.JTextField();
        oppPhase1 = new javax.swing.JTextField();
        oppName2 = new javax.swing.JTextField();
        oppName3 = new javax.swing.JTextField();
        oppPhase2 = new javax.swing.JTextField();
        oppPhase3 = new javax.swing.JTextField();
        numCard1 = new javax.swing.JTextField();
        numCard2 = new javax.swing.JTextField();
        numCard3 = new javax.swing.JTextField();
        score1 = new javax.swing.JTextField();
        score2 = new javax.swing.JTextField();
        score3 = new javax.swing.JTextField();
        pg2a1 = new javax.swing.JButton();
        lblTo2 = new javax.swing.JLabel();
        pg2b1 = new javax.swing.JButton();
        pg1a1 = new javax.swing.JButton();
        pg1b1 = new javax.swing.JButton();
        lblTo1 = new javax.swing.JLabel();
        pg1a2 = new javax.swing.JButton();
        lblTo3 = new javax.swing.JLabel();
        lblTo4 = new javax.swing.JLabel();
        pg2a2 = new javax.swing.JButton();
        pg2b2 = new javax.swing.JButton();
        pg1b2 = new javax.swing.JButton();
        pg1b3 = new javax.swing.JButton();
        pg1a3 = new javax.swing.JButton();
        lblTo5 = new javax.swing.JLabel();
        pg2a3 = new javax.swing.JButton();
        lblTo6 = new javax.swing.JLabel();
        pg2b3 = new javax.swing.JButton();
        pg1a4 = new javax.swing.JButton();
        pg2a4 = new javax.swing.JButton();
        lblTo7 = new javax.swing.JLabel();
        lblTo8 = new javax.swing.JLabel();
        pg1b4 = new javax.swing.JButton();
        pg2b4 = new javax.swing.JButton();
        AddToPhase1Opp1 = new javax.swing.JButton();
        AddToPhase1Opp2 = new javax.swing.JButton();
        AddToPhase1Opp3 = new javax.swing.JButton();
        AddToPhase2Opp1 = new javax.swing.JButton();
        AddToPhase2Opp2 = new javax.swing.JButton();
        AddToPhase2Opp3 = new javax.swing.JButton();
        AddToYourPhase1 = new javax.swing.JButton();
        AddToYourPhase2 = new javax.swing.JButton();
        btnNewPhase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phase10");
        setPreferredSize(new java.awt.Dimension(2560, 1200));

        jPanel2.setPreferredSize(new java.awt.Dimension(2560, 1600));

        jButton1.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setPreferredSize(new java.awt.Dimension(77, 99));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        deckCardButton.setIcon(new javax.swing.ImageIcon("/Users/yalinzhang/Desktop/cardImages/cardback.png")); // NOI18N
        deckCardButton.setPreferredSize(new java.awt.Dimension(72, 95));
        deckCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckCardButtonActionPerformed(evt);
            }
        });

        discardButton.setIcon(new ImageIcon(getCardFile(gManage.mainManager.getGame().getRound().getTopOfDiscardStack()))
        );
        discardButton.setPreferredSize(new java.awt.Dimension(72, 99));
        discardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardButtonActionPerformed(evt);
            }
        });

        lblTurnMode.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        lblTurnMode.setText("You may draw or discard");

        phaseNumber.setEditable(false);
        phaseNumber.setColumns(20);
        phaseNumber.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        phaseNumber.setRows(5);
        textAreaPhase.setViewportView(phaseNumber);

        phaseDescription.setFont(new java.awt.Font(".AppleSystemUIFont", 2, 13)); // NOI18N
        phaseDescription.setText("Phase Description");
        phaseDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phaseDescriptionActionPerformed(evt);
            }
        });

        scoreNumber.setEditable(false);
        scoreNumber.setColumns(20);
        scoreNumber.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 18)); // NOI18N
        scoreNumber.setRows(5);
        textAreaScore.setViewportView(scoreNumber);

        scoreBoard.setFont(new java.awt.Font(".AppleSystemUIFont", 2, 13)); // NOI18N
        scoreBoard.setText("Score Board");
        scoreBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreBoardActionPerformed(evt);
            }
        });

        lblPhase.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        lblPhase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhase.setText("phase");

        lblPlayerName.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 14)); // NOI18N
        lblPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font(".AppleSystemUIFont", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Score");

        oppName1.setEditable(false);
        oppName1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        oppName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oppName1.setText("Name: " +opponent1.getName());

        oppPhase1.setEditable(false);
        oppPhase1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        oppPhase1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oppPhase1.setText("Phase: " + opponent1.getPhase());

        oppName2.setEditable(false);
        oppName2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        oppName2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oppName2.setText("Name: " +opponent2.getName());
        oppName2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oppName2ActionPerformed(evt);
            }
        });

        oppName3.setEditable(false);
        oppName3.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        oppName3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oppName3.setText("Name: " +opponent3.getName());

        oppPhase2.setEditable(false);
        oppPhase2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        oppPhase2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oppPhase2.setText("Phase: " + opponent2.getPhase());
        oppPhase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oppPhase2ActionPerformed(evt);
            }
        });

        oppPhase3.setEditable(false);
        oppPhase3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        oppPhase3.setText("Phase: " + opponent3.getPhase());

        numCard1.setEditable(false);
        numCard1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        numCard1.setText("Cards in hand: " + opponent1.getHand().getNumberOfCards());

        numCard2.setEditable(false);
        numCard2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        numCard2.setText("Cards in hand: " + opponent2.getHand().getNumberOfCards());
        numCard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numCard2ActionPerformed(evt);
            }
        });

        numCard3.setText("Cards in hand: " + opponent3.getHand().getNumberOfCards());

        score1.setEditable(false);
        score1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        score1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score1.setText("Score: " + opponent1.getScore());

        score2.setEditable(false);
        score2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score2.setText("Score: " + opponent2.getScore());
        score2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                score2ActionPerformed(evt);
            }
        });

        score3.setEditable(false);
        score3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        score3.setText("Score: " + opponent3.getScore());

        javax.swing.GroupLayout opponentPanelLayout = new javax.swing.GroupLayout(opponentPanel);
        opponentPanel.setLayout(opponentPanelLayout);
        opponentPanelLayout.setHorizontalGroup(
            opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opponentPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(oppPhase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(opponentPanelLayout.createSequentialGroup()
                        .addComponent(oppName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99))
                    .addGroup(opponentPanelLayout.createSequentialGroup()
                        .addComponent(numCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addComponent(oppPhase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(opponentPanelLayout.createSequentialGroup()
                        .addComponent(numCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(score2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(oppPhase3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numCard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(opponentPanelLayout.createSequentialGroup()
                        .addComponent(oppName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(oppName3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(score3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        opponentPanelLayout.setVerticalGroup(
            opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opponentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oppName3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(opponentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oppPhase3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppPhase2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppPhase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numCard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pg2a1.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2a1ActionPerformed(evt);
            }
        });

        lblTo2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo2.setText("To");

        pg2b1.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2b1ActionPerformed(evt);
            }
        });

        pg1a1.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1a1ActionPerformed(evt);
            }
        });

        pg1b1.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1b1ActionPerformed(evt);
            }
        });

        lblTo1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo1.setText("To");

        pg1a2.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1a2ActionPerformed(evt);
            }
        });

        lblTo3.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo3.setText("To");

        lblTo4.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo4.setText("To");

        pg2a2.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2a2ActionPerformed(evt);
            }
        });

        pg2b2.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2b2ActionPerformed(evt);
            }
        });

        pg1b2.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1b2ActionPerformed(evt);
            }
        });

        pg1b3.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1b3ActionPerformed(evt);
            }
        });

        pg1a3.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1a3ActionPerformed(evt);
            }
        });

        lblTo5.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo5.setText("To");

        pg2a3.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2a3ActionPerformed(evt);
            }
        });

        lblTo6.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo6.setText("To");

        pg2b3.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2b3ActionPerformed(evt);
            }
        });

        pg1a4.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1a4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1a4ActionPerformed(evt);
            }
        });

        pg2a4.setToolTipText("");
        pg2a4.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2a4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2a4ActionPerformed(evt);
            }
        });

        lblTo7.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo7.setText("To");

        lblTo8.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        lblTo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTo8.setText("To");

        pg1b4.setPreferredSize(new java.awt.Dimension(72, 99));
        pg1b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg1b4ActionPerformed(evt);
            }
        });

        pg2b4.setPreferredSize(new java.awt.Dimension(72, 99));
        pg2b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pg2b4ActionPerformed(evt);
            }
        });

        AddToPhase1Opp1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToPhase1Opp1.setText("Add To Phase");
        AddToPhase1Opp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToPhase1Opp1ActionPerformed(evt);
            }
        });

        AddToPhase1Opp2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToPhase1Opp2.setText("Add To Phase");
        AddToPhase1Opp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToPhase1Opp2ActionPerformed(evt);
            }
        });

        AddToPhase1Opp3.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToPhase1Opp3.setText("Add To Phase");
        AddToPhase1Opp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToPhase1Opp3ActionPerformed(evt);
            }
        });

        AddToPhase2Opp1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToPhase2Opp1.setText("Add To Phase");
        AddToPhase2Opp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToPhase2Opp1ActionPerformed(evt);
            }
        });

        AddToPhase2Opp2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToPhase2Opp2.setText("Add To Phase");
        AddToPhase2Opp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToPhase2Opp2ActionPerformed(evt);
            }
        });

        AddToPhase2Opp3.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToPhase2Opp3.setText("Add To Phase");
        AddToPhase2Opp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToPhase2Opp3ActionPerformed(evt);
            }
        });

        AddToYourPhase1.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToYourPhase1.setText("Add To Phase");
        AddToYourPhase1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToYourPhase1ActionPerformed(evt);
            }
        });

        AddToYourPhase2.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        AddToYourPhase2.setText("Add To Phase");
        AddToYourPhase2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToYourPhase2ActionPerformed(evt);
            }
        });

        btnNewPhase.setFont(new java.awt.Font(".AppleSystemUIFont", 0, 13)); // NOI18N
        btnNewPhase.setText("New Phase");
        btnNewPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewPhaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(opponentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pg1a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTo7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pg1b4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AddToYourPhase1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(159, 159, 159)
                                        .addComponent(btnNewPhase)))
                                .addGap(52, 52, 52)
                                .addComponent(pg2a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTo8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pg2b4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddToYourPhase2)))))
                .addContainerGap(485, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pg2a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblTo2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pg2b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddToPhase2Opp1)
                        .addGap(18, 18, 18)
                        .addComponent(pg2a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblTo4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pg2b2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddToPhase2Opp2)
                        .addGap(15, 15, 15)
                        .addComponent(pg2a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(pg2b3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTurnMode)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addComponent(deckCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pg1a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTo1)
                                .addGap(12, 12, 12)
                                .addComponent(pg1b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(AddToPhase1Opp1)
                                .addGap(18, 18, 18)
                                .addComponent(pg1a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTo3)
                                .addGap(12, 12, 12)
                                .addComponent(pg1b2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(AddToPhase1Opp2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(pg1a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblTo5)
                                        .addGap(12, 12, 12)
                                        .addComponent(pg1b3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addComponent(lblTo6)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPlayerName))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(AddToPhase1Opp3)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(AddToPhase2Opp3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(textAreaPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(lblPhase)
                                                        .addGap(16, 16, 16)))))))
                                .addGap(188, 188, 188))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phaseDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(textAreaScore, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(scoreBoard)
                                        .addGap(23, 23, 23)))
                                .addGap(139, 139, 139))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(opponentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pg1a1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pg1b1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(lblTo1))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(AddToPhase1Opp1)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pg1a2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pg1b2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(lblTo3))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(AddToPhase1Opp2)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pg1a3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pg1b3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(lblTo5))))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(AddToPhase1Opp3)
                        .addGap(38, 38, 38)
                        .addComponent(lblPlayerName)
                        .addGap(18, 18, 18)
                        .addComponent(lblPhase)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(AddToPhase2Opp2)
                                        .addGap(51, 51, 51))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblTo4)
                                        .addGap(56, 56, 56))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(textAreaPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(AddToPhase2Opp3))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(lblTo6))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(AddToPhase2Opp1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(lblTo2)))
                                .addGap(32, 32, 32)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(phaseDescription)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addGap(24, 24, 24)
                                .addComponent(textAreaScore, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scoreBoard))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTurnMode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(deckCardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(discardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pg2b1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pg2a1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pg2b2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pg2a2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pg2b3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pg2a3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pg1a4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTo7)
                        .addComponent(pg1b4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddToYourPhase1)
                        .addComponent(pg2b4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pg2a4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTo8)
                        .addComponent(AddToYourPhase2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnNewPhase)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(1).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(1));
            cardButtons.get(1).setSelected(false);
//            cardButtons.get(1).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(1));
            cardButtons.get(1).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if(cardButtons.get(0).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(0));
            cardButtons.get(0).setSelected(false);
//            cardButtons.get(0).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(0));
            cardButtons.get(0).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
//        if(cardIds.get(0)!=null){
//            int index = 0;
//            String cardId = cardIds.get(0);
//            window = new PopUp(cardId, index, cardButtons, this, discardButton);
//            window.setBounds(750, 40, 700, 800);
//            window.setVisible(true);
//            window.setResizeable(false);
//            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(2).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(2));
            cardButtons.get(2).setSelected(false);
//            cardButtons.get(2).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(2));
            cardButtons.get(2).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(3).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(3));
            cardButtons.get(3).setSelected(false);
//            cardButtons.get(3).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(3));
            cardButtons.get(3).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
       if(cardButtons.get(4).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(4));
            cardButtons.get(4).setSelected(false);
//            cardButtons.get(4).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(4));
            cardButtons.get(4).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(5).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(5));
            cardButtons.get(5).setSelected(false);
//            cardButtons.get(5).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(5));
            cardButtons.get(5).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(6).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(6));
            cardButtons.get(6).setSelected(false);
//            cardButtons.get(6).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(6));
            cardButtons.get(6).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(7).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(7));
            cardButtons.get(7).setSelected(false);
//            cardButtons.get(7).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(7));
            cardButtons.get(7).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(8).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(8));
            cardButtons.get(8).setSelected(false);
//            cardButtons.get(8).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(8));
            cardButtons.get(8).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(9).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(9));
            cardButtons.get(9).setSelected(false);
//            cardButtons.get(9).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(9));
            cardButtons.get(9).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        if(cardButtons.get(10).isSelected()){
            selectedCards.remove(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(10));
            cardButtons.get(10).setSelected(false);
//            cardButtons.get(10).setVisible(false);
        }
        else{
            selectedCards.add(gManage.mainManager.getGame().getCurrentPlayer().getHand().getCard(10));
            cardButtons.get(10).setSelected(true);
        }
        if(selectedCards.size() == 1){
            discardButton.setEnabled(true);
        }
        else{
            discardButton.setEnabled(false);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void deckCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckCardButtonActionPerformed
        // TODO add your handling code here:
        if(gManage.mainManager.getGame().getCurrentPlayer().getHasDrawnCard() == false){
            gManage.mainManager.getGame().getRound().drawFromDeck();
            updateFrame(gManage.mainManager.getGame());
            discardButton.setEnabled(false);
        }
        
    }//GEN-LAST:event_deckCardButtonActionPerformed

    private void phaseDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phaseDescriptionActionPerformed
        // TODO add your handling code here:
        gManage.displayPhaseDescriptionFrame();
    }//GEN-LAST:event_phaseDescriptionActionPerformed

    private void scoreBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreBoardActionPerformed
        // TODO add your handling code here:
        gManage.displayScoreFrame();
    }//GEN-LAST:event_scoreBoardActionPerformed

    private void discardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardButtonActionPerformed
        // TODO add your handling code here:
        if(!gManage.mainManager.getGame().getCurrentPlayer().getHasDrawnCard()){
            boolean isValidCard = gManage.mainManager.getGame().getRound().drawFromDiscardStack();
            if(isValidCard){
                
                updateFrame(gManage.mainManager.getGame());
                deckCardButton.setEnabled(false);
                discardButton.setEnabled(false);
            }
            else{
                JLabel message = new JLabel("CANNOT PICK UP A SKIP");
             message.setFont(new Font("Arial", Font.BOLD, 48));
             JOptionPane.showMessageDialog(null, message);
            }
            
        }
        else{//discarding
            if(selectedCards.size()!=0){
            gManage.mainManager.getGame().getRound().discard(selectedCards.get(0));
            }
            updateFrame(gManage.mainManager.getGame());
            deckCardButton.setEnabled(true);
            discardButton.setEnabled(true);
        }
        
    }//GEN-LAST:event_discardButtonActionPerformed

    private void pg2a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2a1ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent1.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg2a1ActionPerformed

    private void pg2b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2b1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg2b1ActionPerformed

    private void pg1a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1a1ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent1.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg1a1ActionPerformed

    private void pg1b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1b1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg1b1ActionPerformed

    private void pg1a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1a2ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent2.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg1a2ActionPerformed

    private void pg2a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2a2ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent2.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg2a2ActionPerformed

    private void pg2b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2b2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg2b2ActionPerformed

    private void pg1b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1b2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg1b2ActionPerformed

    private void pg1b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1b3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg1b3ActionPerformed

    private void pg1a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1a3ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent3.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg1a3ActionPerformed

    private void pg2a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2a3ActionPerformed
        // TODO add your handling code here:
         phaseGroupIndex = 1;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent3.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg2a3ActionPerformed

    private void pg2b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2b3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg2b3ActionPerformed

    private void pg1a4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1a4ActionPerformed
        // TODO add your handling code here:
         phaseGroupIndex = 0;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = current.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg1a4ActionPerformed

    private void pg2a4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2a4ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        isAtBeginning = true;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = current.getPhaseGroup(phaseGroupIndex).addCardToBeginning(selectedCards.get(0));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_pg2a4ActionPerformed

    private void pg1b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg1b4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg1b4ActionPerformed

    private void pg2b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pg2b4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pg2b4ActionPerformed

    private void AddToPhase1Opp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToPhase1Opp1ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        isAtBeginning = false;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent1.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }

                        
    }//GEN-LAST:event_AddToPhase1Opp1ActionPerformed

    private void AddToPhase2Opp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToPhase2Opp1ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        isAtBeginning = false;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent1.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToPhase2Opp1ActionPerformed

    private void AddToPhase1Opp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToPhase1Opp2ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        isAtBeginning = false;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent2.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToPhase1Opp2ActionPerformed

    private void AddToPhase2Opp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToPhase2Opp2ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        isAtBeginning = false;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent2.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToPhase2Opp2ActionPerformed

    private void AddToPhase1Opp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToPhase1Opp3ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        isAtBeginning = false;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent3.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToPhase1Opp3ActionPerformed

    private void AddToPhase2Opp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToPhase2Opp3ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        isAtBeginning = false;
       if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = opponent3.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToPhase2Opp3ActionPerformed

    private void AddToYourPhase1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToYourPhase1ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 0;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = current.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToYourPhase1ActionPerformed

    private void AddToYourPhase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToYourPhase2ActionPerformed
        // TODO add your handling code here:
        phaseGroupIndex = 1;
        if(current.getHasDrawnCard()){
            for(int x=0; x< selectedCards.size();x++){
                boolean valid = current.getPhaseGroup(phaseGroupIndex).addCard(selectedCards.get(x));
                if(!valid){
                   JLabel message = new JLabel("Invalid Add");
                   message.setFont(new Font("Arial", Font.BOLD, 48));
                   JOptionPane.showMessageDialog(null, message); 
                   break;
                }
                else{
                    hideAndClearSelectedCards();
                    updateFrame(gManage.mainManager.getGame());
                    updatecurrentPlayerPhase();
                }
            }
        }
    }//GEN-LAST:event_AddToYourPhase2ActionPerformed

    private void btnNewPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewPhaseActionPerformed
        // TODO add your handling code here:
        if(isPhasing){
            if(isSecondPhaseGroup==false){
                newPhaseGroup = new PhaseGroup(gManage.mainManager.getGame());
        
                for(Card c : selectedCards){
                    newPhaseGroup.addCard(c);
                }
                for(int i = 0; i< cardButtons.size(); i++){
                    if(cardButtons.get(i).isSelected()){
                        cardButtons.get(i).setVisible(false);
                    }
                }
                
                selectedCards.clear();
                
                switch(current.getPhase()){
                    case 1: 
                        btnNewPhase.setText("Add a set of 3!");
                        isSecondPhaseGroup = true;
                        break;
                    case 2:
                        btnNewPhase.setText("Add a run of 4!");
                        isSecondPhaseGroup = true;
                        break;
                    case 3: 
                        btnNewPhase.setText("Add a run of 4!");
                        isSecondPhaseGroup = true;
                        break;
                    case 7:
                        btnNewPhase.setText("Add a set of 4!");
                        isSecondPhaseGroup = true;
                        break;
                    case 9:
                        btnNewPhase.setText("Add a set of 2!");
                        isSecondPhaseGroup = true;
                        break;
                    case 10:
                        btnNewPhase.setText("Add a set of 3!");
                        isSecondPhaseGroup = true;
                        break;
                    default:
                        btnNewPhase.setText("New Phase");
                        isSecondPhaseGroup = false;
                    
                        boolean isValid = current.addPhaseGroups(newPhaseGroup);
                        if(!isValid){
                            JLabel message = new JLabel("Not a valid phase!");
                            message.setFont(new Font("Arial", Font.BOLD, 48));
                            JOptionPane.showMessageDialog(null, message);
                            for(int i = 0; i< cardButtons.size(); i++){
                                cardButtons.get(i).setVisible(true);
                            }
                            selectedCards.clear();
                            isPhasing = false;
                            isSecondPhaseGroup = false; 
                            btnNewPhase.setText("New Phase");
                        }
                        else{
//                            pg1a4.setVisible(true);
//                            lblTo7.setVisible(true);
//                            pg1b4.setVisible(true);
//                            AddToYourPhase1.setVisible(true);
           
                            pg1a4.setIcon(new ImageIcon(getCardFile(newPhaseGroup.getCard(0))));
                            pg1b4.setIcon(new ImageIcon(getCardFile(newPhaseGroup.getCard(newPhaseGroup.getNumberOfCards() -1))));
           
                            selectedCards.clear();
                            btnNewPhase.setVisible(false);
                            btnNewPhase.setText("New Phase");
                            
                            updateFrame(gManage.mainManager.getGame());
                            isPhasing=false;
                            isSecondPhaseGroup = false;
                            break;
                        }
                    }
                }
                
                else{
                int secondGroupStartIndex = newPhaseGroup.getNumberOfCards();
                newPhaseGroup2 = new PhaseGroup(gManage.mainManager.getGame());
                for(Card c: selectedCards){
                    newPhaseGroup2.addCard(c);
                }
                boolean isValid = current.addPhaseGroups(newPhaseGroup, newPhaseGroup2);
                if(!isValid){
                    JLabel message = new JLabel("Not a valid phases!");
                    message.setFont(new Font("Arial", Font.BOLD, 48));
                    JOptionPane.showMessageDialog(null, message);
                        
                    hideAndClearSelectedCards();
                    isPhasing = false;
                    isSecondPhaseGroup = false;
                    btnNewPhase.setText("New Phase");
                }
                else{
//                    pg1a4.setVisible(true);
//                    lblTo7.setVisible(true);
//                    pg1b4.setVisible(true);
//                    AddToYourPhase1.setVisible(true);
           
                    pg1a4.setIcon(new ImageIcon(getCardFile(newPhaseGroup.getCard(0))));
                    pg1b4.setIcon(new ImageIcon(getCardFile(newPhaseGroup.getCard(secondGroupStartIndex -1))));
           
           
//                    pg2a4.setVisible(true);
//                    lblTo8.setVisible(true);
//                    pg2b4.setVisible(true);
//                     AddToYourPhase2.setVisible(true);
           
                    pg2a4.setIcon(new ImageIcon(getCardFile(newPhaseGroup2.getCard(0))));
                    pg2b4.setIcon(new ImageIcon(getCardFile(newPhaseGroup2.getCard(newPhaseGroup2.getNumberOfCards() -1))));
           
                    updateFrame(gManage.mainManager.getGame());
                    selectedCards.clear();
           
                    btnNewPhase.setVisible(false);
                     btnNewPhase.setText("New Phase");
                     
                     isPhasing=false;
                    isSecondPhaseGroup = false;
                }
                
            }
        }
            
        else{
            isPhasing = true;
            isSecondPhaseGroup = false;
            switch(current.getPhase()){
                case 1: 
                btnNewPhase.setText("Add a set of 3!");
                break;
            case 2:
                btnNewPhase.setText("Add a set of 3!");
                break;
            case 3: 
                btnNewPhase.setText("Add a set of 4!");
                break;
            case 4:
                btnNewPhase.setText("Add a run of 7!");
                break;
            case 5:
                btnNewPhase.setText("Add a run of 8!");
                break;
            case 6:
                btnNewPhase.setText("Add a run of 9!");
                break;
            case 7:
                btnNewPhase.setText("Add a set of 4!");
                break;
            case 8:
                btnNewPhase.setText("Add 7 cards of 1 color!");
                break; 
            case 9:
                btnNewPhase.setText("Add a set of 5!");
                break;  
            case 10:
                btnNewPhase.setText("Add a set of 5!");
                break;    
            
            }
        } 
    }//GEN-LAST:event_btnNewPhaseActionPerformed

    private void oppName2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oppName2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_oppName2ActionPerformed

    private void oppPhase2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oppPhase2ActionPerformed

    }//GEN-LAST:event_oppPhase2ActionPerformed

    private void numCard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numCard2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_numCard2ActionPerformed

    private void score2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_score2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_score2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameStage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameStage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToPhase1Opp1;
    private javax.swing.JButton AddToPhase1Opp2;
    private javax.swing.JButton AddToPhase1Opp3;
    private javax.swing.JButton AddToPhase2Opp1;
    private javax.swing.JButton AddToPhase2Opp2;
    private javax.swing.JButton AddToPhase2Opp3;
    private javax.swing.JButton AddToYourPhase1;
    private javax.swing.JButton AddToYourPhase2;
    private javax.swing.JButton btnNewPhase;
    private javax.swing.JButton deckCardButton;
    private javax.swing.JButton discardButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblPhase;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblTo1;
    private javax.swing.JLabel lblTo2;
    private javax.swing.JLabel lblTo3;
    private javax.swing.JLabel lblTo4;
    private javax.swing.JLabel lblTo5;
    private javax.swing.JLabel lblTo6;
    private javax.swing.JLabel lblTo7;
    private javax.swing.JLabel lblTo8;
    private javax.swing.JLabel lblTurnMode;
    private javax.swing.JTextField numCard1;
    private javax.swing.JTextField numCard2;
    private javax.swing.JTextField numCard3;
    private javax.swing.JTextField oppName1;
    private javax.swing.JTextField oppName2;
    private javax.swing.JTextField oppName3;
    private javax.swing.JTextField oppPhase1;
    private javax.swing.JTextField oppPhase2;
    private javax.swing.JTextField oppPhase3;
    private javax.swing.JPanel opponentPanel;
    private javax.swing.JButton pg1a1;
    private javax.swing.JButton pg1a2;
    private javax.swing.JButton pg1a3;
    private javax.swing.JButton pg1a4;
    private javax.swing.JButton pg1b1;
    private javax.swing.JButton pg1b2;
    private javax.swing.JButton pg1b3;
    private javax.swing.JButton pg1b4;
    private javax.swing.JButton pg2a1;
    private javax.swing.JButton pg2a2;
    private javax.swing.JButton pg2a3;
    private javax.swing.JButton pg2a4;
    private javax.swing.JButton pg2b1;
    private javax.swing.JButton pg2b2;
    private javax.swing.JButton pg2b3;
    private javax.swing.JButton pg2b4;
    private javax.swing.JButton phaseDescription;
    private javax.swing.JTextArea phaseNumber;
    private javax.swing.JTextField score1;
    private javax.swing.JTextField score2;
    private javax.swing.JTextField score3;
    private javax.swing.JButton scoreBoard;
    private javax.swing.JTextArea scoreNumber;
    private javax.swing.JScrollPane textAreaPhase;
    private javax.swing.JScrollPane textAreaScore;
    // End of variables declaration//GEN-END:variables
}
