/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbm.crm.panels;

import com.cbm.crm.Thereness;
import com.cbm.crm.entity.AccountType;
import java.util.ArrayList;

/**
 *
 * @author Tebogo
 */
public class JAccountTypePanel extends javax.swing.JPanel {

    public ArrayList<AccountType> accountTypes = new ArrayList<>();
    public AccountType accountType;
    public boolean dbCrud = false;
    /**
     * Creates new form JAccountTypePanel
     */
    public JAccountTypePanel() {
        initComponents();
    }
    
    public void fillCmb() {
        for(int a=0; a<accountTypes.size(); a++) {
            cmbAccountType.addItem(accountTypes.get(a).getAccountName());
        }
    }

    public void clear() {
        clearText();
        cmbAccountType.setSelectedIndex(0);
    }
    
    private void clearText() {
        txtAccountName.setText("");
        txtAccountDescription.setText("");
    }

    public boolean isDbCrud() {
        return dbCrud;
    }

    public void setDbCrud(boolean dbCrud) {
        pnlNewAccountType.setVisible(dbCrud);
        
        this.dbCrud = dbCrud;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbAccountType = new javax.swing.JComboBox<>();
        btnRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAccountName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAccountDescription = new javax.swing.JTextArea();
        pnlNewAccountType = new javax.swing.JPanel();
        btnAccountTypeDelete = new javax.swing.JButton();
        btnAccountTypeSave = new javax.swing.JButton();
        btnAccountTypeClear = new javax.swing.JButton();

        cmbAccountType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "A", "B", "C" }));
        cmbAccountType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAccountTypeItemStateChanged(evt);
            }
        });

        btnRefresh.setText("Refresh");

        jLabel1.setText("Name");

        jLabel2.setText("Description");

        txtAccountName.setEditable(false);

        txtAccountDescription.setEditable(false);
        txtAccountDescription.setColumns(20);
        txtAccountDescription.setRows(5);
        jScrollPane1.setViewportView(txtAccountDescription);

        btnAccountTypeDelete.setText("Delete");
        btnAccountTypeDelete.setEnabled(false);

        btnAccountTypeSave.setText("Save");

        btnAccountTypeClear.setText("Clear");
        btnAccountTypeClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccountTypeClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewAccountTypeLayout = new javax.swing.GroupLayout(pnlNewAccountType);
        pnlNewAccountType.setLayout(pnlNewAccountTypeLayout);
        pnlNewAccountTypeLayout.setHorizontalGroup(
            pnlNewAccountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewAccountTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAccountTypeSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAccountTypeDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAccountTypeClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNewAccountTypeLayout.setVerticalGroup(
            pnlNewAccountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewAccountTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewAccountTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccountTypeSave)
                    .addComponent(btnAccountTypeDelete)
                    .addComponent(btnAccountTypeClear))
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
                    .addComponent(txtAccountName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(pnlNewAccountType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAccountName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlNewAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbAccountType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbAccountTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAccountTypeItemStateChanged
        // TODO add your handling code here:
        //Thereness.be().current[2] = cmbAccountType.getSelectedIndex();
        //System.out.println("Selected: " + cbmAccountType.getSelectedIndex());
        if(cmbAccountType.getSelectedIndex()==0) {
            clearText();
            accountType = new com.cbm.crm.entity.AccountType();
        } else if(accountTypes.size()==cmbAccountType.getItemCount()-1) {
            accountType = accountTypes.get(cmbAccountType.getSelectedIndex()-1);
            txtAccountName.setText(accountType.getAccountName());
            txtAccountDescription.setText(accountType.getDescription());
        }
    }//GEN-LAST:event_cmbAccountTypeItemStateChanged

    private void btnAccountTypeClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountTypeClearActionPerformed
        //clear();
        cmbAccountType.setSelectedIndex(0);
    }//GEN-LAST:event_btnAccountTypeClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccountTypeClear;
    private javax.swing.JButton btnAccountTypeDelete;
    private javax.swing.JButton btnAccountTypeSave;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cmbAccountType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlNewAccountType;
    private javax.swing.JTextArea txtAccountDescription;
    private javax.swing.JTextField txtAccountName;
    // End of variables declaration//GEN-END:variables
}
