/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm.panels;

import com.cbm.crm.Thereness;
import com.cbm.crm.entity.AccountType;
import com.cbm.crm.entity.Role;
import com.cbm.crm.entity.client.AccountTypeClient;
import com.cbm.crm.entity.client.RoleClient;
import com.cbm.crm.strings.String_Constants;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sakkie
 */
public class JGenericDescriptionPanel extends javax.swing.JPanel {

    public ArrayList<Object> objects = new ArrayList<>();
    public Object o = null;
    public boolean dbCrud = false;
    /**
     * Creates new form JPanel
     */
    public JGenericDescriptionPanel() {
        initComponents();
        
        clear();
        btnListEdit.setSelected(dbCrud);
        btnListEdit.setVisible(dbCrud);
        //fillCmb();
    }
    
    public void clear() {
        clearText();
        cmbList.setSelectedIndex(0);
        
    }
    
    public void clearText() {
        txtName.setText("{none}");
        txtDescription.setText("{none}");
        txtName.setEditable(dbCrud);
        txtDescription.setEditable(dbCrud);
        btnGDDelete.setEnabled(false);
        btnGDSave.setText("New");
    }
    
    public void fillCmb() {
        objects.removeAll(objects);
        cmbList.removeAllItems();
        cmbList.addItem("None");
        
        if(o instanceof Role) {
            RoleClient roleClient = new RoleClient();
            try {
                JSONArray ja = (JSONArray)new JSONParser().parse(roleClient.findAll_JSON(String.class));
                for(int a=0; a<ja.size(); a++) {
                    JSONObject jo = (JSONObject)ja.get(a);
                    Role r = Role.fromJSON(jo);
                    if(r!=null)objects.add(r);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            for(int a=0; a<objects.size(); a++) {
                cmbList.addItem(((Role)objects.get(a)).getRoleName());
            }
        } else if(o instanceof AccountType) {
            AccountTypeClient accountTypeClient = new AccountTypeClient();
            try {
                JSONArray ja = (JSONArray)new JSONParser().parse(accountTypeClient.findAll_JSON(String.class));
                for(int a=0; a<ja.size(); a++) {
                    JSONObject jo = (JSONObject)ja.get(a);
                    AccountType at = AccountType.fromJSON(jo);
                    if(at!=null)objects.add(at);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }

            for(int a=0; a<objects.size(); a++) {
                cmbList.addItem(((AccountType)objects.get(a)).getAccountName());
            }
        }
    }

    public boolean isDbCrud() {
        return dbCrud;
    }

    public void setDbCrud(boolean dbCrud) {
        pnlNewRole.setVisible(dbCrud);
        
        txtName.setEditable(dbCrud);
        txtDescription.setEditable(dbCrud);
        
        this.dbCrud = dbCrud;
    }
    
    public int getSelectedIndex() {
        return cmbList.getSelectedIndex();
    }
    
    public void setSelectedIndex(int i) {
        cmbList.setSelectedIndex(i);
        /*for(int a=0; a<cmbList.getItemCount(); a++) {
            if(a!=i) {
                cmbList.removeItemAt(a);
            }
        }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbList = new javax.swing.JComboBox<>();
        btnListRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        pnlNewRole = new javax.swing.JPanel();
        btnGDDelete = new javax.swing.JButton();
        btnGDSave = new javax.swing.JButton();
        btnGDClear = new javax.swing.JButton();
        btnListEdit = new javax.swing.JToggleButton();

        cmbList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None" }));
        cmbList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbListItemStateChanged(evt);
            }
        });

        btnListRefresh.setText("Refresh");
        btnListRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListRefreshActionPerformed(evt);
            }
        });

        jLabel1.setText("Name");

        jLabel2.setText("Description");

        txtName.setEditable(false);

        txtDescription.setEditable(false);
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        btnGDDelete.setText("Delete");
        btnGDDelete.setEnabled(false);
        btnGDDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGDDeleteActionPerformed(evt);
            }
        });

        btnGDSave.setText("Save");
        btnGDSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGDSaveActionPerformed(evt);
            }
        });

        btnGDClear.setText("Clear");
        btnGDClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGDClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewRoleLayout = new javax.swing.GroupLayout(pnlNewRole);
        pnlNewRole.setLayout(pnlNewRoleLayout);
        pnlNewRoleLayout.setHorizontalGroup(
            pnlNewRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewRoleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGDSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGDDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGDClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNewRoleLayout.setVerticalGroup(
            pnlNewRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewRoleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewRoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGDSave)
                    .addComponent(btnGDDelete)
                    .addComponent(btnGDClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(pnlNewRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlNewRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnListEdit.setText("E");
        btnListEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListRefresh)
                    .addComponent(btnListEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbListItemStateChanged
        //Thereness.be().current[3] = cmbRoleList.getSelectedIndex();
        if(cmbList.getSelectedIndex()==0) {
            clearText();
            //role = null;
            //txtRoleName.setText("{none}");
            //txtRoleDescription.setText("{none}");
            //btnSaveRole.setText("New");
        } else if(objects.size()==cmbList.getItemCount()-1&&cmbList.getSelectedIndex()>0) {
            //role = roles.get(cmbRoleList.getSelectedIndex()-1);
            clearText();
            if(o instanceof Role) {
                txtName.setText(((Role)objects.get(cmbList.getSelectedIndex()-1)).getRoleName());
                txtDescription.setText(((Role)objects.get(cmbList.getSelectedIndex()-1)).getDescription());
            } else if(o instanceof AccountType) {
                txtName.setText(((AccountType)objects.get(cmbList.getSelectedIndex()-1)).getAccountName());
                txtDescription.setText(((AccountType)objects.get(cmbList.getSelectedIndex()-1)).getDescription());
            }
            btnGDSave.setText("Update");
            btnGDDelete.setEnabled(dbCrud);
        }
    }//GEN-LAST:event_cmbListItemStateChanged

    private void btnGDSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGDSaveActionPerformed
        try {
            if(btnGDSave.getText().equals("Save")||btnGDSave.getText().equals("Update")) {
                if(!txtName.getText().isEmpty()&&!txtDescription.getText().isEmpty()) {
                    if(o instanceof Role) {
                        RoleClient client = new RoleClient();
                        Role n = new Role();
                        n.setRoleName(txtName.getText());
                        n.setDescription(txtDescription.getText());

                        if(btnGDSave.getText().equals("Save")) {
                            client.create_JSON(n);
                        } else if(btnGDSave.getText().equals("Update")) {
                            client.edit_JSON(n, ((Role)objects.get(cmbList.getSelectedIndex()-1)).getRoleId()+"");
                        }
                        client.close();
                    } else if(o instanceof AccountType) {
                        AccountTypeClient client = new AccountTypeClient();
                        AccountType n = new AccountType();
                        n.setAccountName(txtName.getText());
                        n.setDescription(txtDescription.getText());

                        if(btnGDSave.getText().equals("Save")) {
                            client.create_JSON(n);
                        } else if(btnGDSave.getText().equals("Update")) {
                            n.setAccountTypeId(((AccountType)objects.get(cmbList.getSelectedIndex()-1)).getAccountTypeId());
                            client.edit_JSON(n, n.getAccountTypeId()+"");
                        }
                        client.close();
                    }
                    fillCmb();
                    cmbList.setSelectedIndex(cmbList.getItemCount()-1);
                    JOptionPane.showMessageDialog(null, String_Constants.SAVED_INFO);
                } else {
                    JOptionPane.showMessageDialog(null, String_Constants.FILL_FIELDS+":\nName and Description");
                }
            }
            
            if(btnGDSave.getText().equals("New")) {
                txtName.setText("");
                txtDescription.setText("");
                txtName.setEditable(true);
                txtDescription.setEditable(true);
                btnGDSave.setText("Save");
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnGDSaveActionPerformed

    private void btnGDClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGDClearActionPerformed
        clear();
        //cmbRoleList.setSelectedIndex(0);
    }//GEN-LAST:event_btnGDClearActionPerformed

    private void btnListEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListEditActionPerformed
        // TODO add your handling code here:
        setDbCrud(btnListEdit.isSelected());
    }//GEN-LAST:event_btnListEditActionPerformed

    private void btnListRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListRefreshActionPerformed
        int i = cmbList.getSelectedIndex();
        fillCmb();
        cmbList.setSelectedIndex(i);
    }//GEN-LAST:event_btnListRefreshActionPerformed

    private void btnGDDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGDDeleteActionPerformed
        int result = JOptionPane.showConfirmDialog(null, String_Constants.DELETE_RECORD);
        if(result == JOptionPane.YES_OPTION) {
            if(cmbList.getSelectedIndex()>0) {
                if(o instanceof Role) {
                    RoleClient client = new RoleClient();
                    client.remove(((Role)objects.get(cmbList.getSelectedIndex()-1)).getRoleId()+"");
                    client.close();
                    fillCmb();
                } else if(o instanceof AccountType) {
                    AccountTypeClient client = new AccountTypeClient();
                    client.remove(((AccountType)objects.get(cmbList.getSelectedIndex()-1)).getAccountTypeId()+"");
                    client.close();
                    fillCmb();
                }
            }
        }
    }//GEN-LAST:event_btnGDDeleteActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGDClear;
    private javax.swing.JButton btnGDDelete;
    private javax.swing.JButton btnGDSave;
    private javax.swing.JToggleButton btnListEdit;
    private javax.swing.JButton btnListRefresh;
    private javax.swing.JComboBox<String> cmbList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlNewRole;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
