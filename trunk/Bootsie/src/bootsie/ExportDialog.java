/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExportDialog.java
 *
 * Created on Sep 27, 2011, 4:40:47 PM
 */
package bootsie;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Justin Payne
 */
public class ExportDialog extends javax.swing.JDialog {

   DataExporter exporter;

    /** Creates new form ExportDialog */
    public ExportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ExportDialog(java.awt.Frame parent, boolean modal, DataExporter e){
       this(parent, modal);
       exporter = e;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      exportMergeGroup = new javax.swing.ButtonGroup();
      exportButton = new javax.swing.JButton();
      cancelButton = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      ExportList = new javax.swing.JList();
      jSeparator1 = new javax.swing.JSeparator();
      exportMerged = new javax.swing.JRadioButton();
      exportSeparately = new javax.swing.JRadioButton();
      jLabel1 = new javax.swing.JLabel();

      setAlwaysOnTop(true);
      setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
      setName("ExportDialog"); // NOI18N

      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(bootsie.BootsieApp.class).getContext().getResourceMap(ExportDialog.class);
      exportButton.setText(resourceMap.getString("exportButton.text")); // NOI18N
      exportButton.setToolTipText(resourceMap.getString("exportButton.toolTipText")); // NOI18N
      exportButton.setActionCommand(resourceMap.getString("exportButton.actionCommand")); // NOI18N
      exportButton.setName("exportButton"); // NOI18N
      exportButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            exportButtonActionPerformed(evt);
         }
      });

      cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
      cancelButton.setToolTipText(resourceMap.getString("cancelButton.toolTipText")); // NOI18N
      cancelButton.setActionCommand(resourceMap.getString("cancelButton.actionCommand")); // NOI18N
      cancelButton.setName("cancelButton"); // NOI18N
      cancelButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelButtonActionPerformed(evt);
         }
      });

      jScrollPane1.setName("jScrollPane1"); // NOI18N

      ExportList.setModel(dataSetCollection);
      ExportList.setName("ExportList"); // NOI18N
      jScrollPane1.setViewportView(ExportList);

      jSeparator1.setName("jSeparator1"); // NOI18N

      exportMergeGroup.add(exportMerged);
      exportMerged.setSelected(true);
      exportMerged.setText(resourceMap.getString("exportMerged.text")); // NOI18N
      exportMerged.setName("exportMerged"); // NOI18N

      exportMergeGroup.add(exportSeparately);
      exportSeparately.setText(resourceMap.getString("exportSeparately.text")); // NOI18N
      exportSeparately.setActionCommand(resourceMap.getString("exportSeparately.actionCommand")); // NOI18N
      exportSeparately.setName("exportSeparately"); // NOI18N

      jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
      jLabel1.setName("jLabel1"); // NOI18N

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap(242, Short.MAX_VALUE)
            .addComponent(cancelButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(exportButton)
            .addContainerGap())
         .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(exportSeparately)
            .addContainerGap(49, Short.MAX_VALUE))
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(exportMerged)
            .addContainerGap(249, Short.MAX_VALUE))
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addContainerGap(251, Short.MAX_VALUE))
         .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(exportMerged)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(exportSeparately)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(exportButton)
               .addComponent(cancelButton))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
       // TODO add your handling code here:
       //get a file location and pass it and the data matrix array to the exporter
       ArrayList<DataMatrixModel> list = new ArrayList<DataMatrixModel>();
       File file;
       JFileChooser fc = new JFileChooser();
       FileNameExtensionFilter filter = new FileNameExtensionFilter("", exporter.getFileExtention());
       fc.setFileFilter(filter);
       if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
          //collect selected DataMatrixModels into list
          file = fc.getSelectedFile();
          exporter.dataExport(file, list, exportMerged.isSelected());
          this.setVisible(false);
       }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
       // TODO add your handling code here:
       this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

 

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JList ExportList;
   private javax.swing.JButton cancelButton;
   private javax.swing.JButton exportButton;
   private javax.swing.ButtonGroup exportMergeGroup;
   private javax.swing.JRadioButton exportMerged;
   private javax.swing.JRadioButton exportSeparately;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JSeparator jSeparator1;
   // End of variables declaration//GEN-END:variables
}
