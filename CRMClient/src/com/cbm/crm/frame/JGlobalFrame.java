/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm.frame;

import com.cbm.crm.Thereness;
import com.cbm.crm.entity.Account;
import com.cbm.crm.entity.AccountType;
import com.cbm.crm.entity.DbTransactions;
import com.cbm.crm.entity.LoginDetails;
import com.cbm.crm.entity.Person;
import com.cbm.crm.entity.Role;
import com.cbm.crm.entity.client.AccountClient;
import com.cbm.crm.entity.client.DbTransactionClient;
import com.cbm.crm.entity.client.LoginDetailsClient;
import com.cbm.crm.entity.client.PersonClient;
import com.cbm.crm.panels.JGenericDescriptionPanel;
import com.cbm.crm.panels.JLoginPanel;
import com.cbm.crm.panels.JPersonalContactPanel;
import com.cbm.crm.panels.JPersonalDetailsPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sakkie
 */
public class JGlobalFrame extends javax.swing.JFrame {

    /**
     * Creates new form JGlobalFrame
     */
    private Person mPerson = null;
    private ArrayList<Person> mPeople = new ArrayList<>();
    private Account account = null;
    private LoginDetails login = null;
    private JPersonalDetailsPanel pnlPersonalDetails;
    private JPersonalContactPanel pnlPersonalContact;
    //private JAccountTypePanel pnlAccountType;
    //private JRolePanel pnlRole;
    private JGenericDescriptionPanel pnlAccountType;
    private JGenericDescriptionPanel pnlRole;
    private JLoginPanel pnlLogin;
    
    private int panelIndex;
    public JGlobalFrame() {
        initComponents();
        
        initValues();
        michkEditDetails.setSelected(false);
    }
    
    private void clearAll() {
        clearEntries();
        tblPersonalDetailsMain.clearSelection();
    }
    
    private void clearEntries() {
        pnlPersonalDetails.clear();
        pnlPersonalContact.clear();
        pnlAccountType.clear();
        pnlRole.clear();
        pnlLogin.clear();
        setEditOptions(false);
        tbpnlDetails.setSelectedIndex(0);
    }
    
    private void clearLogin() {
        txtLoginUsername.setText("");
        txtLoginPassword.setText("");
    }
    
    private void tabChange() {
        tbpnlDetails.setSelectedIndex(panelIndex);
        if(tbpnlDetails.getTabCount()<=1){
            btnDetailsPrevious.setEnabled(false);
            btnDetailsNext.setEnabled(false); 
        } else {
            btnDetailsPrevious.setEnabled(false);
            btnDetailsNext.setEnabled(true);
            if(panelIndex>0) btnDetailsPrevious.setEnabled(true);
            if(panelIndex>=tbpnlDetails.getTabCount()-1) btnDetailsNext.setEnabled(false);
        }
    }
    
    private void initValues() {
        pnlPersonalDetails = new JPersonalDetailsPanel();
        pnlPersonalContact = new JPersonalContactPanel();
        pnlAccountType = new JGenericDescriptionPanel();
        pnlRole = new JGenericDescriptionPanel();
        pnlLogin = new JLoginPanel();
        panelIndex = 0;
        tbpnlDetails.add("Personal", pnlPersonalDetails);
        tbpnlDetails.add("Contact", pnlPersonalContact);
        tbpnlDetails.add("Account Type", pnlAccountType);
        tbpnlDetails.add("Role", pnlRole);
        tbpnlDetails.add("Login", pnlLogin);
        
        if(tbpnlDetails.getTabCount()>1) {
            btnDetailsNext.setEnabled(true);
            //btnDetailsPrevious.setEnabled(true);
        } else {
            btnDetailsNext.setEnabled(false);
            btnDetailsPrevious.setEnabled(false);
        }
        
        tbpnlDetails.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                panelIndex=tbpnlDetails.getSelectedIndex();
                tabChange();
            }
        });
        
        pnlPersonalContact.getTxtEmail().addKeyListener(new KeyListener() {
            @Override public void keyTyped(KeyEvent ke) {}
            @Override public void keyPressed(KeyEvent ke) {}
            @Override public void keyReleased(KeyEvent ke) {pnlLogin.getTxtUsername().setText(pnlPersonalContact.getTxtEmail().getText());}}
        );
        
        pnlRole.o = new Role();
        pnlAccountType.o = new AccountType();
        pnlRole.fillCmb();
        pnlAccountType.fillCmb();
        
        updateTable();
    }
    
    private void initPersonValues(Person p) {
        pnlPersonalDetails.setPerson(p);
        pnlPersonalContact.setPerson(p);
        //pnlLogin.setLoginDetails(login);
        
        account = new AccountClient().findByPersonId_JSON(Account.class, p.getPersonId()+"");
        for(int x=0; x<pnlRole.objects.size(); x++) {
            if(((Role)pnlRole.objects.get(x)).getRoleId()==account.getRoleId()) {
                pnlRole.o = (Role)pnlRole.objects.get(x);
                pnlRole.setSelectedIndex(x+1);
                break;
            }
        }
        int accountTypeId = account.getAccountTypeId();
        for(int x=0; x<pnlAccountType.objects.size(); x++) {
            if(((AccountType)pnlAccountType.objects.get(x)).getAccountTypeId()==accountTypeId) {
                pnlAccountType.o = (AccountType)pnlAccountType.objects.get(x);
                pnlAccountType.setSelectedIndex(x+1);
                break;
            }
        }
        //setEditOptions(account.getRoleId()==1);
        michkEditDetails.setVisible(account.getRoleId()==1&&login.getPersonId()==account.getPersonId());
    }
    
    private Person refreshAccount(int _id) {
        PersonClient client = new PersonClient();
        mPerson = client.find_JSON(Person.class, _id+"");
        client.close();
        
        return mPerson;
    }
    
    private void updateTable() {
        try {
            mPeople.removeAll(mPeople);
            JSONArray ja = (JSONArray)new JSONParser().parse(new PersonClient().findAll_JSON(String.class));
            for(int i=0; i<ja.size(); i++) {
                JSONObject jo = (JSONObject)ja.get(i);
                mPeople.add(new Person(Integer.parseInt(jo.get("personId").toString()), 
                        jo.get("firstname").toString(), 
                        jo.get("lastname").toString(), 
                        jo.get("email").toString(), 
                        jo.get("cellnumber1").toString(), 
                        jo.get("cellnumber2").toString(), 
                        jo.get("fax").toString(), 
                        jo.get("gender").toString()));
            }
            
            Object[] columns = {"ID", "Fullname", "Cell Nr. 1", "Email"};
            Object[][] dataTable = new Object[mPeople.size()][columns.length];
            for(int i = 0; i<mPeople.size(); i++) {
                dataTable[i][0] = mPeople.get(i).getPersonId()+"";
                dataTable[i][1] = mPeople.get(i).getFirstname()+" "+mPeople.get(i).getLastname();
                dataTable[i][2] = mPeople.get(i).getCellnumber1();
                dataTable[i][3] = mPeople.get(i).getEmail();
            }
            
            tblPersonalDetailsMain.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    if(tblPersonalDetailsMain.getSelectedRow()>=0) {
                        if(!michkEditDetails.isSelected())initPersonValues(mPeople.get(tblPersonalDetailsMain.getSelectedRow()));
                        else JOptionPane.showMessageDialog(null, "Edit Mode is on. To switch "
                                + "between details Edit Mode must be off.");
                    }
                }
            });
            
            tblPersonalDetailsMain.setModel(new DefaultTableModel(dataTable, columns));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void setEditOptions(boolean active) {
        pnlCRUDEditDetails.setVisible(active);
        pnlRole.setDbCrud(active);
        pnlAccountType.setDbCrud(active);
        //if(tbpnlDetails.getTabCount()>0)tbpnlDetails.getTabComponentAt(tbpnlDetails.getTabCount()-1).setVisible(active);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlLD = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLoginUsername = new javax.swing.JTextField();
        txtLoginPassword = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        btnLoginClear = new javax.swing.JButton();
        btnLoginResetPassword = new javax.swing.JButton();
        btnLoginRegister = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnlCRUDEditDetails = new javax.swing.JPanel();
        btnPersonalDetailsClear = new javax.swing.JButton();
        btnPersonalDetailsSave = new javax.swing.JButton();
        btnPersonalDetailsRemove = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPersonalDetailsMain = new javax.swing.JTable();
        pnlDetails = new javax.swing.JPanel();
        tbpnlDetails = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        btnDetailsPrevious = new javax.swing.JButton();
        btnDetailsNext = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miNewRegistration = new javax.swing.JMenuItem();
        miSave = new javax.swing.JMenuItem();
        miLogout = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        michkEditDetails = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        miClearAll = new javax.swing.JMenuItem();
        miClearEntries = new javax.swing.JMenuItem();
        miClearTableSelection = new javax.swing.JMenuItem();
        miPreviousTab = new javax.swing.JMenuItem();
        miNextTab = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        txtLoginUsername.setMinimumSize(new java.awt.Dimension(120, 20));

        txtLoginPassword.setMinimumSize(new java.awt.Dimension(120, 20));

        btnLoginClear.setText("Clear");

        btnLoginResetPassword.setText("Reset Password");

        btnLoginRegister.setText("Register");
        btnLoginRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginRegisterActionPerformed(evt);
            }
        });

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoginRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(btnLoginClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(btnLoginResetPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(btnLoginRegister)
                    .addComponent(btnLoginClear)
                    .addComponent(btnLoginResetPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLDLayout = new javax.swing.GroupLayout(pnlLD);
        pnlLD.setLayout(pnlLDLayout);
        pnlLDLayout.setHorizontalGroup(
            pnlLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLDLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoginUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoginPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLDLayout.setVerticalGroup(
            pnlLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtLoginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnPersonalDetailsClear.setText("Clear");
        btnPersonalDetailsClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalDetailsClearActionPerformed(evt);
            }
        });

        btnPersonalDetailsSave.setText("Save");
        btnPersonalDetailsSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalDetailsSaveActionPerformed(evt);
            }
        });

        btnPersonalDetailsRemove.setText("Remove");
        btnPersonalDetailsRemove.setEnabled(false);
        btnPersonalDetailsRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalDetailsRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCRUDEditDetailsLayout = new javax.swing.GroupLayout(pnlCRUDEditDetails);
        pnlCRUDEditDetails.setLayout(pnlCRUDEditDetailsLayout);
        pnlCRUDEditDetailsLayout.setHorizontalGroup(
            pnlCRUDEditDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCRUDEditDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPersonalDetailsSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonalDetailsRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonalDetailsClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCRUDEditDetailsLayout.setVerticalGroup(
            pnlCRUDEditDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCRUDEditDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCRUDEditDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPersonalDetailsSave)
                    .addComponent(btnPersonalDetailsRemove)
                    .addComponent(btnPersonalDetailsClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblPersonalDetailsMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P_ID", "Fullname", "Cell Nr. 1", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPersonalDetailsMain.setName(""); // NOI18N
        tblPersonalDetailsMain.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblPersonalDetailsMain.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblPersonalDetailsMain);

        pnlDetails.setLayout(new java.awt.BorderLayout());
        pnlDetails.add(tbpnlDetails, java.awt.BorderLayout.CENTER);

        btnDetailsPrevious.setText("<");
        btnDetailsPrevious.setEnabled(false);
        btnDetailsPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsPreviousActionPerformed(evt);
            }
        });

        btnDetailsNext.setText(">");
        btnDetailsNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnDetailsPrevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetailsNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetailsPrevious)
                    .addComponent(btnDetailsNext))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlCRUDEditDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCRUDEditDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        jMenu1.setText("File");

        miNewRegistration.setText("New Registration");
        miNewRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewRegistrationActionPerformed(evt);
            }
        });
        jMenu1.add(miNewRegistration);

        miSave.setText("Save");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveActionPerformed(evt);
            }
        });
        jMenu1.add(miSave);

        miLogout.setText("Logout");
        miLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(miLogout);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu1.add(miExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        michkEditDetails.setSelected(true);
        michkEditDetails.setText("Edit Details");
        michkEditDetails.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                michkEditDetailsStateChanged(evt);
            }
        });
        jMenu2.add(michkEditDetails);

        jMenu3.setText("Clear");

        miClearAll.setText("Clear All");
        miClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClearAllActionPerformed(evt);
            }
        });
        jMenu3.add(miClearAll);

        miClearEntries.setText("Clear Entries");
        miClearEntries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClearEntriesActionPerformed(evt);
            }
        });
        jMenu3.add(miClearEntries);

        miClearTableSelection.setText("Clear Selection");
        miClearTableSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClearTableSelectionActionPerformed(evt);
            }
        });
        jMenu3.add(miClearTableSelection);

        jMenu2.add(jMenu3);

        miPreviousTab.setText("Previous Tab");
        jMenu2.add(miPreviousTab);

        miNextTab.setText("Next Tab");
        jMenu2.add(miNextTab);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("View");

        jMenuItem10.setText("Show/Hide Login");
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Show/Hide Table");
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPersonalDetailsClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalDetailsClearActionPerformed
        // TODO add your handling code here:
        try {
            clearAll();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPersonalDetailsClearActionPerformed

    private void btnPersonalDetailsSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalDetailsSaveActionPerformed
        PersonClient personClient = new PersonClient();
        Person p = new Person();
        p=pnlPersonalDetails.getPerson(p);
        p=pnlPersonalContact.getPerson(p);
        String dateCreated = new SimpleDateFormat(Thereness.be().dateFormat).format(new Date());
        if(btnPersonalDetailsSave.getText().equals("Save"))p.setDateCreated(dateCreated);
        if(p!=null) {
            personClient.create_JSON(p);
            p.setPersonId(personClient.findByDate_JSON(Person.class, dateCreated).getPersonId());
            pnlLogin.getLoginDetails().setPersonId(p.getPersonId());
            LoginDetailsClient loginClient = new LoginDetailsClient();
            loginClient.create_JSON(pnlLogin.getLoginDetails());
            AccountClient accountClient = new AccountClient();
            Account a = new Account();
            a.setRoleId(((Role)pnlRole.objects.get(pnlRole.getSelectedIndex()-1)).getRoleId());
            AccountType at = (AccountType)pnlAccountType.objects.get(pnlAccountType.getSelectedIndex()-1);
            String accName = at.getAccountName();
            a.setAccountNo(accName.substring(0, accName.length()<3?0:2)+p.getPersonId());
            a.setAccountTypeId(at.getAccountTypeId());
            a.setPersonId(p.getPersonId());
            accountClient.create_JSON(a);
            accountClient.close();
            
            DbTransactionClient dbTransClient = new DbTransactionClient();
            dbTransClient.create_JSON(new DbTransactions(0, "Person object created by id="+p.getPersonId(), p.getPersonId(), dateCreated));
            dbTransClient.close();
        }
        personClient.close();
        updateTable();
        clearAll();
        michkEditDetails.setSelected(false);
        for(int i=0; i<mPeople.size(); i++) {
            if(mPeople.get(i).getPersonId()==p.getPersonId()) {
                tblPersonalDetailsMain.addRowSelectionInterval(i, i);
                break;
            }
        }
        
        System.out.println("Fullname="+p.getFirstname()+" "+p.getLastname()+", Email="+p.getEmail());
        /*if(tn.p!=null)p.setPersonId(tn.p.getPersonId());
        p.setFirstname(txtFirstname.getText());
        p.setLastname(txtLastname.getText());
        p.setGender(cmbGender.getItemAt(cmbGender.getSelectedIndex()));
        p.setCellnumber1(txtCellphonenr1.getText());
        p.setCellnumber2(txtCellphonenr2.getText());
        p.setEmail(txtEmail.getText());
        p.setRoleId(0);
        p.setServiceId(0);

        PersonClient client = new PersonClient();
        try {
            if(btnPersonalDetailsSave.getText().equals("Save")) {
                client.create_JSON(p);
            } else if(btnPersonalDetailsSave.getText().equals("Update")) {
                client.edit_JSON(p, p.getPersonId()+"");
            }

            updateTable(true);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        client.close();
        System.out.println(btnPersonalDetailsSave.getText()+"d details of "
            + p.getFirstname() + " "
            + p.getLastname());*/
    }//GEN-LAST:event_btnPersonalDetailsSaveActionPerformed

    private void btnPersonalDetailsRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalDetailsRemoveActionPerformed
        // TODO add your handling code here:
        /*PersonClient client = new PersonClient();
        try {
            String sRows = "";
            for(int i:tblPersonalDetailsMain.getSelectedRows()) {
                Person p = tn.lstP.get(i);
                sRows += "ID: " + p.getPersonId() + " Fullname: " + p.getFirstname()+" "+p.getLastname()+".\n";
            }
            String msg = "About to remove\n"+sRows+"Continue?";
            int result = JOptionPane.showConfirmDialog(null, msg);
            switch(result) {
                case JOptionPane.YES_OPTION:
                System.out.println("Record removal.");
                for(int i:tblPersonalDetailsMain.getSelectedRows()) {
                    Person p = tn.lstP.get(i);
                    client.remove(p.getPersonId()+"");
                }
                clear();
                updateTable(true);
                break;
                case JOptionPane.NO_OPTION:
                System.out.println("No selected.");
                break;
                case JOptionPane.CANCEL_OPTION:
                System.out.println("Cancel selected.");
                break;
                case JOptionPane.CLOSED_OPTION:
                System.out.println("Closed selected.");
                break;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        client.close();*/
    }//GEN-LAST:event_btnPersonalDetailsRemoveActionPerformed

    private void btnDetailsPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsPreviousActionPerformed
        // TODO add your handling code here:
        panelIndex = panelIndex-1>-1?panelIndex -= 1:0;
        tabChange();
        /*tbpnlDetails.setSelectedIndex(panelIndex);
        
        if(panelIndex>0)btnDetailsPrevious.setEnabled(true);
        else {
            btnDetailsPrevious.setEnabled(false);
            btnDetailsNext.setEnabled(true);
        }*/
    }//GEN-LAST:event_btnDetailsPreviousActionPerformed

    private void btnDetailsNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsNextActionPerformed
        // TODO add your handling code here:
        panelIndex = panelIndex +1<tbpnlDetails.getTabCount()?panelIndex += 1:tbpnlDetails.getTabCount()-1;
        tabChange();
        /*tbpnlDetails.setSelectedIndex(panelIndex);
        if(panelIndex>0)btnDetailsPrevious.setEnabled(true);
        if(panelIndex==tbpnlDetails.getTabCount()-1)btnDetailsNext.setEnabled(false);*/
    }//GEN-LAST:event_btnDetailsNextActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            LoginDetailsClient client = new LoginDetailsClient();
            login = client.findByUsername_JSON(LoginDetails.class, txtLoginUsername.getText());
            client.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        if(login!=null&&login.getUsername().toLowerCase().equals(txtLoginUsername.getText().toLowerCase())
                &&login.getPassword().equals(txtLoginPassword.getText())) {
            clearLogin();
            pnlLD.setVisible(false);
            JOptionPane.showMessageDialog(null, "Login successful.");
            initPersonValues(refreshAccount(login.getPersonId()));
        } else {
            JOptionPane.showMessageDialog(null, "Login not successful.");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void miNewRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miNewRegistrationActionPerformed

    private void miSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miSaveActionPerformed

    private void miLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLogoutActionPerformed
        // TODO add your handling code here:
        clearLogin();
        mPerson = new Person();
        pnlLD.setVisible(true);
        txtLoginUsername.requestFocus();
    }//GEN-LAST:event_miLogoutActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_miExitActionPerformed

    private void michkEditDetailsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_michkEditDetailsStateChanged
        setEditOptions(michkEditDetails.isSelected());
    }//GEN-LAST:event_michkEditDetailsStateChanged

    private void btnLoginRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginRegisterActionPerformed
        clearAll();
        setEditOptions(true);
    }//GEN-LAST:event_btnLoginRegisterActionPerformed

    private void miClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClearAllActionPerformed
        clearAll();
    }//GEN-LAST:event_miClearAllActionPerformed

    private void miClearEntriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClearEntriesActionPerformed
        clearEntries();
    }//GEN-LAST:event_miClearEntriesActionPerformed

    private void miClearTableSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClearTableSelectionActionPerformed
        tblPersonalDetailsMain.clearSelection();
    }//GEN-LAST:event_miClearTableSelectionActionPerformed

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
            java.util.logging.Logger.getLogger(JGlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JGlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JGlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JGlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JGlobalFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetailsNext;
    private javax.swing.JButton btnDetailsPrevious;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLoginClear;
    private javax.swing.JButton btnLoginRegister;
    private javax.swing.JButton btnLoginResetPassword;
    private javax.swing.JButton btnPersonalDetailsClear;
    private javax.swing.JButton btnPersonalDetailsRemove;
    private javax.swing.JButton btnPersonalDetailsSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem miClearAll;
    private javax.swing.JMenuItem miClearEntries;
    private javax.swing.JMenuItem miClearTableSelection;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JMenuItem miLogout;
    private javax.swing.JMenuItem miNewRegistration;
    private javax.swing.JMenuItem miNextTab;
    private javax.swing.JMenuItem miPreviousTab;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JCheckBoxMenuItem michkEditDetails;
    private javax.swing.JPanel pnlCRUDEditDetails;
    private javax.swing.JPanel pnlDetails;
    private javax.swing.JPanel pnlLD;
    private javax.swing.JTable tblPersonalDetailsMain;
    private javax.swing.JTabbedPane tbpnlDetails;
    private javax.swing.JPasswordField txtLoginPassword;
    private javax.swing.JTextField txtLoginUsername;
    // End of variables declaration//GEN-END:variables
}
