/*
 * @(#)TextPaneTest.java  1.0  13 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package test;

/**
 * TextPaneTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  13 February 2005  Created.
 */
public class TextPaneTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public TextPaneTest() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrollPane1 = new javax.swing.JScrollPane();
        textPane1 = new javax.swing.JTextPane();
        label1 = new javax.swing.JLabel();
        scrollPane2 = new javax.swing.JScrollPane();
        textPane2 = new javax.swing.JTextPane();
        label2 = new javax.swing.JLabel();
        scrollPane3 = new javax.swing.JScrollPane();
        textPane3 = new javax.swing.JTextPane();
        label3 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());

        textPane1.setText("the quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog");
        scrollPane1.setViewportView(textPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(scrollPane1, gridBagConstraints);

        label1.setText("Enabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(label1, gridBagConstraints);

        textPane2.setEditable(false);
        textPane2.setText("the quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog");
        scrollPane2.setViewportView(textPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(scrollPane2, gridBagConstraints);

        label2.setText("Non-Editable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(label2, gridBagConstraints);

        textPane3.setText("the quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog\nthe quick brown fox jumps over the lazy dog");
        textPane3.setEnabled(false);
        scrollPane3.setViewportView(textPane3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(scrollPane3, gridBagConstraints);

        label3.setText("Disabled");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(label3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JTextPane textPane1;
    private javax.swing.JTextPane textPane2;
    private javax.swing.JTextPane textPane3;
    // End of variables declaration//GEN-END:variables
    
}
