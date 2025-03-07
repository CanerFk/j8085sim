/*
 *  This file is part of j8085sim.
 *
 *   j8085sim is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   any later version.
 *
 *   j8085sim is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with j8085sim.  If not, see <http://www.gnu.org/licenses/>. 
 */

/*
 *   Developer: Sinu John - http://www.sinujohn.wordpress.com
 *               email: sinuvian@gmail.com
 *
 *    Copyright 2010 Sinu John
 */

/*
 * MemFrame.java
 *
 * Created on Mar 16, 2010, 11:56:43 PM
 */

package sim;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

/**
 *
 * @author sinu
 */
public class MemFrame extends javax.swing.JFrame implements TableModelListener{

    Memory m;
    MainFrame mainf;
    String rowData[][],colNames[]={"Memory Location","Contents"};
    TableModel tmodel;

    /** Creates new form MemFrame */
    public MemFrame(Memory mem,MainFrame mf) {
        initComponents();
        mainf=mf;
        m=mem;
        pack();
        setVisible(true);
        updateMemoryViewer();
    }
    
    public void tableChanged(TableModelEvent e)
    {
        int row=memViewer.getEditingRow();
        int tmp;
        String val=(String)memViewer.getValueAt(row, 1);
        String addr=(String)memViewer.getValueAt(row, 0);
        try
        {
            tmp=Integer.parseInt(val, 16);
            if(val.length()==2) m.write(val, Integer.parseInt(addr, 16));
            else 
            {
                memViewer.setValueAt("00", row, 1);
                errorLargeNumber(2);
            }
        }
        catch(java.lang.NumberFormatException q)
        {
            memViewer.setValueAt("00", row, 1);
            errorInvalidHex();
        }
    }

    public void updateMemoryViewer()
    {
        int start=Integer.parseInt(startAddress.getText(), 16);
        int end=Integer.parseInt(endAddress.getText(), 16);
        if(start>end)
        {
            errorMemoryBounds();
            return;
        }
        rowData=new String[end-start+1][2];
        for(int i=0;i<=(end-start);i++)
        {
            String memloc=Integer.toHexString(start+i);

            //to convert memloc to 4digit hex
            int tmp=4-memloc.length();
            String dup="";
            for(int j=0;j<tmp;j++) dup=dup+"0";
                    memloc=dup+memloc;
            ////////////////////////////////////

            memloc=memloc.toUpperCase();
            rowData[i][0]=memloc;
            rowData[i][1]=m.read(memloc);
        }
        tmodel=new DefaultTableModel(rowData,colNames)
                {
                    boolean[] canEdit = new boolean [] {
                        false, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                };
        tmodel.addTableModelListener(this);
        memViewer.setModel(tmodel);
    }

    boolean validHex(String h)
    {
        try
        {
            int n=Integer.parseInt(h, 16);
            return true;
        }
        catch(NumberFormatException q)
        {
            return false;
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

        jPanel20 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        startAddress = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        endAddress = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        memViewer = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sText = new javax.swing.JTextField();
        eText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dataText = new javax.swing.JTextField();
        floodButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Memory");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sim/images/small/icon.png")));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Memory Contents"));

        jLabel33.setText("Start");

        startAddress.setFont(new java.awt.Font("Dialog", 1, 14));
        startAddress.setText("0000");

        jLabel34.setText("End");

        endAddress.setFont(new java.awt.Font("Dialog", 1, 14));
        endAddress.setText("0010");
        endAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endAddressActionPerformed(evt);
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        memViewer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Memory Location", "Contents"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        memViewer.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(memViewer);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addGap(6, 6, 6)
                .addComponent(startAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addGap(6, 6, 6)
                .addComponent(endAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(startAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton)
                    .addComponent(endAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Flood memory"));

        jLabel1.setText("Start");

        jLabel2.setText("End");

        sText.setColumns(5);
        sText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        eText.setColumns(5);
        eText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Data");

        dataText.setColumns(5);
        dataText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        floodButton.setText("Flood");
        floodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                floodButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(floodButton))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(floodButton)
                .addGap(14, 14, 14))
        );

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endAddressActionPerformed
        if(validHex(startAddress.getText())&&validHex(endAddress.getText())) {
            if(startAddress.getText().length()>4||endAddress.getText().length()>4)
            {
                errorLargeNumber(4);
                return;
            }
            int start=Integer.parseInt(startAddress.getText(), 16);
            int end=Integer.parseInt(endAddress.getText(), 16);
            if(start>end) {
                errorMemoryBounds();
            } else  updateMemoryViewer();
        } else {
            errorInvalidHex();
        }
}//GEN-LAST:event_endAddressActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        if(validHex(startAddress.getText())&&validHex(endAddress.getText())) {
            if(startAddress.getText().length()>4||endAddress.getText().length()>4)
            {
                errorLargeNumber(4);
                return;
            }
            int start=Integer.parseInt(startAddress.getText(), 16);
            int end=Integer.parseInt(endAddress.getText(), 16);
            if(start>end) {
                errorMemoryBounds();
            } else  updateMemoryViewer();
        } else {
            errorInvalidHex();
        }
}//GEN-LAST:event_refreshButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        mainf.updateMemoryViewer();
        mainf.setEnabled(true);
        mainf.toFront();
    }//GEN-LAST:event_formWindowClosed

    private void floodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_floodButtonActionPerformed
        if(validHex(sText.getText())&&validHex(eText.getText())&&validHex(dataText.getText()))
        {
            if(sText.getText().length()>4||eText.getText().length()>4)
            {
                errorLargeNumber(4);
                return;
            }
            if(dataText.getText().length()>2)
            {
                errorLargeNumber(2);
                return;
            }
            int start=Integer.parseInt(sText.getText(), 16);
            int end=Integer.parseInt(eText.getText(), 16);
            if(start>end) {
                errorMemoryBounds();
            }
            else
            {
                for(int i=start;i<=end;i++) m.write(dataText.getText(), i);
                updateMemoryViewer();
            }
        }
        else errorInvalidHex();
    }//GEN-LAST:event_floodButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mainf.setEnabled(true);
        mainf.toFront(); 
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    void errorInvalidHex()
    {
        JOptionPane.showMessageDialog(null, "Invalid Hexadecimal number. Please try again.", "Invalid Hexadecimal",JOptionPane.ERROR_MESSAGE);
    }

    void errorMemoryBounds()
    {
        JOptionPane.showMessageDialog(null,"Invalid memory bounds. Check 'Start' and 'End' addresses.","Invalid memory bounds",JOptionPane.ERROR_MESSAGE);
    }
    void errorLargeNumber(int l)
    {
        JOptionPane.showMessageDialog(null,"Invalid number. Please enter a number of length "+l,"Invalid number",JOptionPane.ERROR_MESSAGE);
    }

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dataText;
    private javax.swing.JTextField eText;
    private javax.swing.JTextField endAddress;
    private javax.swing.JButton floodButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable memViewer;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField sText;
    private javax.swing.JTextField startAddress;
    // End of variables declaration//GEN-END:variables

}
