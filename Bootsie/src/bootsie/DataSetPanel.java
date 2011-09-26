/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DataSetPanel.java
 *
 * Created on Sep 20, 2010, 4:29:38 PM
 */

package bootsie;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author jpayne
 */
public class DataSetPanel extends javax.swing.JPanel {

    /** Creates new form DataSetPanel */
   public DataSetPanel(){
      initComponents();
   }

    public DataSetPanel(boolean b) {
        initComponents();
        Border lnBdr = BorderFactory.createLineBorder(Color.GRAY);
        this.setBorder(lnBdr);
        
        DataSetMasterPanel masterPanel = DataSetMasterPanel.getInstance();
        masterPanel.addNumBootstrapsListener(new ActionListener(){

         public void actionPerformed(ActionEvent e) {
            JTextField f = (JTextField) e.getSource();
            NumBootstraps.setText(f.getText());
         }

        });
        masterPanel.addPlotCVNoneListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JRadioButton b = (JRadioButton) e.getSource();
            PlotCVNone.setSelected(b.isSelected());
         }

        });

        masterPanel.addPlotCVSVGListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JRadioButton b = (JRadioButton) e.getSource();
            PlotCVSVG.setSelected(b.isSelected());
         }

        });

        masterPanel.addPlotCVPNGListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JRadioButton b = (JRadioButton) e.getSource();
            PlotCVPNG.setSelected(b.isSelected());
         }

        });
        
    }

    public JLabel getDataSetName(){
       return DataSetName;
    }

    public void setDataName(String s){
       DataSetName.setText(s);
    }

    public void addModel(DataMatrixModel m){
       //add model as listener to plotter selector and bootstrap number field
       NumBootstraps.addActionListener(m);
       PlotCVSVG.addActionListener(m);
       PlotCVPNG.addActionListener(m);
       PlotCVNone.addActionListener(m);
       addMouseListener(m);
    }

    public void displayBeginComputation(){
       PlotCVSVG.setVisible(false);
       PlotCVPNG.setVisible(false);
       PlotCVNone.setVisible(false);
       NumBootstraps.setEditable(false);
    }

    public void displayComputationProgress(int i){
       //int 1 < 1000 for progress bar
    }

    public void displayFinishedComputation(){

    }


    @SuppressWarnings("unchecked")

   private void initComponents() {

      jSeparator1 = new javax.swing.JSeparator();
      PlotCVGroup = new javax.swing.ButtonGroup();
      DataSetName = new javax.swing.JLabel();
      PlotCVGroupLabel = new javax.swing.JLabel();
      PlotCVPNG = new javax.swing.JRadioButton();
      PlotCVSVG = new javax.swing.JRadioButton();
      NumBootstraps = new javax.swing.JTextField();
      NumBootLabel = new javax.swing.JLabel();
      PlotCVNone = new javax.swing.JRadioButton();

      jSeparator1.setName("jSeparator1"); // NOI18N

      setName("Form"); // NOI18N

      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(bootsie.BootsieApp.class).getContext().getResourceMap(DataSetPanel.class);
      DataSetName.setText(resourceMap.getString("DataSetName.text")); // NOI18N
      DataSetName.setName("DataSetName"); // NOI18N

      PlotCVGroupLabel.setText(resourceMap.getString("PlotCVGroupLabel.text")); // NOI18N
      PlotCVGroupLabel.setName("PlotCVGroupLabel"); // NOI18N

      //PlotCVPNG.setModel(null);
      PlotCVPNG.setSelected(false);
      PlotCVGroup.add(PlotCVPNG);
      PlotCVPNG.setText(resourceMap.getString("PlotCVPNG.text")); // NOI18N
      PlotCVPNG.setName("PlotCVPNG"); // NOI18N
      PlotCVPNG.setActionCommand("setPlotterPNG");
      PlotCVPNG.setSelected(false);
      PlotCVPNG.setOpaque(false);
      PlotCVGroup.add(PlotCVSVG);
      PlotCVSVG.setText(resourceMap.getString("PlotCVSVG.text")); // NOI18N
      PlotCVSVG.setName("PlotCVSVG"); // NOI18N
      PlotCVSVG.setActionCommand("setPlotterSVG");
      PlotCVSVG.setOpaque(false);

      NumBootstraps.setText(resourceMap.getString("NumBootstraps.text")); // NOI18N
      NumBootstraps.setName("NumBootstraps"); // NOI18N
      NumBootstraps.setActionCommand("setNumBootstraps");

      NumBootLabel.setText(resourceMap.getString("NumBootLabel.text")); // NOI18N
      NumBootLabel.setName("NumBootLabel"); // NOI18N

      //PlotCVNone.setModel(null);
      PlotCVNone.setSelected(true);
      PlotCVNone.setOpaque(false);
      PlotCVNone.setText(resourceMap.getString("PlotCVNone.text")); // NOI18N
      PlotCVNone.setName("PlotCVNone"); // NOI18N
      PlotCVNone.setActionCommand("setPlotterNone");
      PlotCVGroup.add(PlotCVNone);
      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(DataSetName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(38, 38, 38)
            .addComponent(PlotCVGroupLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(PlotCVPNG)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(PlotCVSVG)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(PlotCVNone)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
            .addComponent(NumBootLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(NumBootstraps, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(DataSetName)
            .addComponent(PlotCVPNG)
            .addComponent(PlotCVSVG)
            .addComponent(PlotCVGroupLabel)
            .addComponent(NumBootstraps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(NumBootLabel)
            .addComponent(PlotCVNone))
      );
   }



   protected javax.swing.JLabel DataSetName;
   private javax.swing.JLabel NumBootLabel;
   protected javax.swing.JTextField NumBootstraps;
   private javax.swing.ButtonGroup PlotCVGroup;
   private javax.swing.JLabel PlotCVGroupLabel;
   protected javax.swing.JRadioButton PlotCVNone;
   protected javax.swing.JRadioButton PlotCVPNG;
   protected javax.swing.JRadioButton PlotCVSVG;
   private javax.swing.JSeparator jSeparator1;


}