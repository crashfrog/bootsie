/*
 * BootsieView.java
 */

package bootsie;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The application's main frame.
 */
public class BootsieView extends FrameView {

   private boolean isMasterAdded = false;

    public BootsieView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        //progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    //progressBar.setVisible(true);
                    //progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                   // progressBar.setVisible(false);
                    //progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    //progressBar.setVisible(true);
                    //progressBar.setIndeterminate(false);
                    //progressBar.setValue(value);
                }
            }
        });
    }

    public PopulationMatrixModel newDataModel(String n){
       PopulationMatrixModel newModel;
       DataSetPanel setPanel = new DataSetPanel(true);
       setPanel.setDataName(n);
       if (isMasterAdded){
          //do nothing
       } else {
          DataSetPane.add(DataSetMasterPanel.getInstance());
          isMasterAdded = true;
       }
       DataSetPane.add(setPanel);
       newModel = new PopulationMatrixModel(n, setPanel);
       setPanel.addModel(newModel);
       PopulationMatrixModelCollection.getInstance().addMatrix(newModel);
       return newModel;
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = BootsieApp.getApplication().getMainFrame();
            aboutBox = new BootsieAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        BootsieApp.getApplication().show(aboutBox);
    }

    private void openFile() {
      //get file or file array and apply app parse method
      JFileChooser fc = new JFileChooser();
      fc.setMultiSelectionEnabled(true);
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
      fc.setFileFilter(filter);
      int returnVal = fc.showOpenDialog(BootsieApp.getApplication().getMainFrame());
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         File[] files = fc.getSelectedFiles();
         BootsieApp.parseFile(files);
      }
   }

    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        ExportMenu = new javax.swing.JMenu();
        exportPopgene = new javax.swing.JMenuItem();
        exportNtsys = new javax.swing.JMenuItem();
        exportArlequin = new javax.swing.JMenuItem();
        exportPaup = new javax.swing.JMenuItem();
        exportTab = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        reportArea = new javax.swing.JTextArea();
        EstimationReportLabel = new javax.swing.JLabel();
        GoButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataSetPane = new javax.swing.JPanel();

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(bootsie.BootsieApp.class).getContext().getResourceMap(BootsieView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        openMenuItem.setText(resourceMap.getString("openMenuItem.text")); // NOI18N
        openMenuItem.setName("openMenuItem"); // NOI18N
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(bootsie.BootsieApp.class).getContext().getActionMap(BootsieView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        ExportMenu.setText(resourceMap.getString("ExportMenu.text")); // NOI18N
        ExportMenu.setEnabled(false);
        ExportMenu.setName("ExportMenu"); // NOI18N

        exportPopgene.setText(resourceMap.getString("exportPopgene.text")); // NOI18N
        exportPopgene.setActionCommand(resourceMap.getString("exportPopgene.actionCommand")); // NOI18N
        exportPopgene.setName("exportPopgene"); // NOI18N
        exportPopgene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToPopgen(evt);
            }
        });
        ExportMenu.add(exportPopgene);

        exportNtsys.setText(resourceMap.getString("exportNtsys.text")); // NOI18N
        exportNtsys.setActionCommand(resourceMap.getString("exportNtsys.actionCommand")); // NOI18N
        exportNtsys.setName("exportNtsys"); // NOI18N
        exportNtsys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToNtsys(evt);
            }
        });
        ExportMenu.add(exportNtsys);

        exportArlequin.setText(resourceMap.getString("exportArlequin.text")); // NOI18N
        exportArlequin.setActionCommand(resourceMap.getString("exportArlequin.actionCommand")); // NOI18N
        exportArlequin.setName("exportArlequin"); // NOI18N
        exportArlequin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToArl(evt);
            }
        });
        ExportMenu.add(exportArlequin);

        exportPaup.setText(resourceMap.getString("exportPaup.text")); // NOI18N
        exportPaup.setActionCommand(resourceMap.getString("exportPaup.actionCommand")); // NOI18N
        exportPaup.setName("exportPaup"); // NOI18N
        ExportMenu.add(exportPaup);

        exportTab.setText(resourceMap.getString("exportTab.text")); // NOI18N
        exportTab.setActionCommand(resourceMap.getString("exportTab.actionCommand")); // NOI18N
        exportTab.setName("exportTab"); // NOI18N
        exportTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportAsTabs(evt);
            }
        });
        ExportMenu.add(exportTab);

        menuBar.add(ExportMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(714, 500));

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        reportArea.setColumns(20);
        reportArea.setEditable(false);
        reportArea.setRows(5);
        reportArea.setName("reportArea"); // NOI18N
        jScrollPane2.setViewportView(reportArea);

        EstimationReportLabel.setText(resourceMap.getString("EstimationReportLabel.text")); // NOI18N
        EstimationReportLabel.setName("EstimationReportLabel"); // NOI18N

        GoButton.setAction(actionMap.get("beginAnalysis")); // NOI18N
        GoButton.setText(resourceMap.getString("GoButton.text")); // NOI18N
        GoButton.setEnabled(false);
        GoButton.setName("GoButton"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        DataSetPane.setName("DataSetPane"); // NOI18N
        DataSetPane.setLayout(new javax.swing.BoxLayout(DataSetPane, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(DataSetPane);

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                        .addComponent(statusMessageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 684, Short.MAX_VALUE)
                        .addComponent(statusAnimationLabel))
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addComponent(EstimationReportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GoButton)))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EstimationReportLabel)
                    .addComponent(GoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusMessageLabel)
                        .addComponent(statusAnimationLabel))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        setComponent(statusPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
       openFile();
       EstimationReportLabel.setText("Estimating...");
       GoButton.setEnabled(true);
       ExportMenu.setEnabled(true);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void exportToPopgen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToPopgen
       // TODO add your handling code here:
       ExportDialog exportDialog = new ExportDialog(this.getFrame(), true, new PopgeneDataExporter());
       exportDialog.setVisible(true);
    }//GEN-LAST:event_exportToPopgen

    private void exportToNtsys(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToNtsys
       // TODO add your handling code here:
       ExportDialog exportDialog = new ExportDialog(this.getFrame(), true, new NtsysDataExporter());
       exportDialog.setVisible(true);
    }//GEN-LAST:event_exportToNtsys

    private void exportToArl(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToArl
       // TODO add your handling code here:
       ExportDialog exportDialog = new ExportDialog(this.getFrame(), true, new ArlequinDataExporter());
       exportDialog.setVisible(true);
    }//GEN-LAST:event_exportToArl

    private void exportAsTabs(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportAsTabs
       // TODO add your handling code here:
       ExportDialog exportDialog = new ExportDialog(this.getFrame(), true, new TabDelimitDataExporter());
       exportDialog.setVisible(true);
    }//GEN-LAST:event_exportAsTabs

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DataSetPane;
    private javax.swing.JLabel EstimationReportLabel;
    private javax.swing.JMenu ExportMenu;
    private javax.swing.JButton GoButton;
    private javax.swing.JMenuItem exportArlequin;
    private javax.swing.JMenuItem exportNtsys;
    private javax.swing.JMenuItem exportPaup;
    private javax.swing.JMenuItem exportPopgene;
    private javax.swing.JMenuItem exportTab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JTextArea reportArea;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private DataSetMasterPanel masterPanel = DataSetMasterPanel.getInstance();

   void addReport(String s) {
      reportArea.append(s);
      reportArea.append("\n");
   }
}
