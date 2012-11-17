/*
 * @(#)TabbedPaneTest.java  1.0  12 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package test;

import ch.randelshofer.quaqua.*;
import ch.randelshofer.quaqua.util.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * TabbedPaneTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  12 February 2005  Created.
 */
public class TabbedPaneTest extends javax.swing.JPanel {
private final static        String[] strings = {"Apple", "Banana", "Cantaloupe", "Cherry", "Grape", "Lemon", "Mango", "Melon", "Orange", "Peach", "Pear", "Pineapple", "Plum", "Strawberry"};

    /** Creates new form. */
    public TabbedPaneTest() {
        initComponents();
        tabbedPane1.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

        for (String s : strings) {
            JPanel p = new JPanel();
            tabbedPane1.add(p, s);
        }

        // Try to get a better layout with J2SE6
        try {
            int BASELINE_LEADING = GridBagConstraints.class.getDeclaredField("BASELINE_LEADING").getInt(null);
            GridBagLayout layout = (GridBagLayout) getLayout();
            for (Component c : getComponents()) {
                GridBagConstraints gbc = layout.getConstraints(c);
                if (gbc.anchor == GridBagConstraints.WEST) {
                    gbc.anchor = BASELINE_LEADING;
                    layout.setConstraints(c, gbc);
                }
            }
        } catch (Exception ex) {
            // bail
        }

    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("TabbedPaneTest: " + UIManager.getLookAndFeel().getName());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new TabbedPaneTest());
        f.pack();
        f.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sizeVariantGroup = new javax.swing.ButtonGroup();
        tabLayoutPolicyGroup = new javax.swing.ButtonGroup();
        tabPlacementGroup = new javax.swing.ButtonGroup();
        tabCountGroup = new javax.swing.ButtonGroup();
        tabbedPane1 = new javax.swing.JTabbedPane();
        jSeparator1 = new javax.swing.JSeparator();
        regularRadio = new javax.swing.JRadioButton();
        smallRadio = new javax.swing.JRadioButton();
        miniRadio = new javax.swing.JRadioButton();
        wrapRadio = new javax.swing.JRadioButton();
        scrollRadio = new javax.swing.JRadioButton();
        topRadio = new javax.swing.JRadioButton();
        rightRadio = new javax.swing.JRadioButton();
        bottomRadio = new javax.swing.JRadioButton();
        leftRadio = new javax.swing.JRadioButton();
        enabledCheck = new javax.swing.JCheckBox();
        buttonInTabs = new javax.swing.JCheckBox();
        manyTabsRadio = new javax.swing.JRadioButton();
        fewTabsRadio = new javax.swing.JRadioButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 17, 17, 17));
        setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(tabbedPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        add(jSeparator1, gridBagConstraints);

        sizeVariantGroup.add(regularRadio);
        regularRadio.setSelected(true);
        regularRadio.setText("Regular");
        regularRadio.setActionCommand("regular");
        regularRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sizeVariantChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(regularRadio, gridBagConstraints);

        sizeVariantGroup.add(smallRadio);
        smallRadio.setText("Small");
        smallRadio.setActionCommand("small");
        smallRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sizeVariantChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(smallRadio, gridBagConstraints);

        sizeVariantGroup.add(miniRadio);
        miniRadio.setText("Mini");
        miniRadio.setActionCommand("mini");
        miniRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sizeVariantChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(miniRadio, gridBagConstraints);

        tabLayoutPolicyGroup.add(wrapRadio);
        wrapRadio.setSelected(true);
        wrapRadio.setText("Wrap");
        wrapRadio.setActionCommand("wrap");
        wrapRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabLayoutPolicyChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(wrapRadio, gridBagConstraints);

        tabLayoutPolicyGroup.add(scrollRadio);
        scrollRadio.setText("Scroll");
        scrollRadio.setActionCommand("scroll");
        scrollRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabLayoutPolicyChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(scrollRadio, gridBagConstraints);

        tabPlacementGroup.add(topRadio);
        topRadio.setSelected(true);
        topRadio.setText("Top");
        topRadio.setActionCommand("top");
        topRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabPlacementChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(topRadio, gridBagConstraints);

        tabPlacementGroup.add(rightRadio);
        rightRadio.setText("Right");
        rightRadio.setActionCommand("right");
        rightRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabPlacementChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(rightRadio, gridBagConstraints);

        tabPlacementGroup.add(bottomRadio);
        bottomRadio.setText("Bottom");
        bottomRadio.setActionCommand("bottom");
        bottomRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabPlacementChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(bottomRadio, gridBagConstraints);

        tabPlacementGroup.add(leftRadio);
        leftRadio.setText("Left");
        leftRadio.setActionCommand("left");
        leftRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabPlacementChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(leftRadio, gridBagConstraints);

        enabledCheck.setSelected(true);
        enabledCheck.setText("Enabled");
        enabledCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enabledChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(enabledCheck, gridBagConstraints);

        buttonInTabs.setText("Button in Tabs");
        buttonInTabs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buttonInTabsChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(buttonInTabs, gridBagConstraints);

        tabCountGroup.add(manyTabsRadio);
        manyTabsRadio.setSelected(true);
        manyTabsRadio.setText("Many Tabs");
        manyTabsRadio.setActionCommand("14");
        manyTabsRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabCountChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(manyTabsRadio, gridBagConstraints);

        tabCountGroup.add(fewTabsRadio);
        fewTabsRadio.setText("Few Tabs");
        fewTabsRadio.setActionCommand("4");
        fewTabsRadio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tabCountChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(fewTabsRadio, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void sizeVariantChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sizeVariantChanged
        String cmd = sizeVariantGroup.getSelection().getActionCommand();
        tabbedPane1.putClientProperty("JComponent.sizeVariant", cmd);
        // tabbedPane1.setUI(tabbedPane1.getUI()); // force ui update 
    }//GEN-LAST:event_sizeVariantChanged

    private void enabledChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_enabledChanged
        tabbedPane1.setEnabled(enabledCheck.isSelected());
    }//GEN-LAST:event_enabledChanged

    private void tabLayoutPolicyChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tabLayoutPolicyChanged
        String cmd = tabLayoutPolicyGroup.getSelection().getActionCommand();
        tabbedPane1.setTabLayoutPolicy("wrap".equals(cmd) ? JTabbedPane.WRAP_TAB_LAYOUT : JTabbedPane.SCROLL_TAB_LAYOUT);
    }//GEN-LAST:event_tabLayoutPolicyChanged

    private void tabPlacementChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tabPlacementChanged
        String cmd = tabPlacementGroup.getSelection().getActionCommand();
        int p = JTabbedPane.TOP;
        if ("top".equals(cmd)) {
            p = JTabbedPane.TOP;
        } else if ("right".equals(cmd)) {
            p = JTabbedPane.RIGHT;
        } else if ("bottom".equals(cmd)) {
            p = JTabbedPane.BOTTOM;
        } else if ("left".equals(cmd)) {
            p = JTabbedPane.LEFT;
        }
        tabbedPane1.setTabPlacement(p);
    }//GEN-LAST:event_tabPlacementChanged

    private void buttonInTabsChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buttonInTabsChanged
        boolean b = buttonInTabs.isSelected();
        if (b) {
            for (int i=0,n=tabbedPane1.getTabCount();i<n;i++) {
            JPanel p=new JPanel(new FlowLayout(FlowLayout.LEADING,4,0));
            p.setOpaque(false);
           p.add(new JLabel(tabbedPane1.getTitleAt(i)));
           JButton btn=new JButton("×");
           btn.setMargin(new Insets(-4,0,-2,0));
          // btn.setBorder(new EmptyBorder(0,0,0,0));
           btn.putClientProperty("JButton.buttonType", "square");
           btn.setOpaque(false);
            p.add(btn);
            Methods.invokeIfExists(tabbedPane1,"setTabComponentAt",new Class[]{Integer.TYPE,Component.class},new Object[]{i,p});
            }
            
        } else {
            for (int i=0,n=tabbedPane1.getTabCount();i<n;i++) {
            Methods.invokeIfExists(tabbedPane1,"setTabComponentAt",new Class[]{Integer.TYPE,Component.class},new Object[]{i,null});
            }
        }

    }//GEN-LAST:event_buttonInTabsChanged

    private void tabCountChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tabCountChanged
        String cmd = tabCountGroup.getSelection().getActionCommand();
        int newCount=Integer.valueOf(cmd);
        tabbedPane1.removeAll();
        for (int i=0;i<newCount;i++) {
                  
            JPanel p = new JPanel();
            tabbedPane1.add(p, strings[i]);
       

        }
    }//GEN-LAST:event_tabCountChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bottomRadio;
    private javax.swing.JCheckBox buttonInTabs;
    private javax.swing.JCheckBox enabledCheck;
    private javax.swing.JRadioButton fewTabsRadio;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton leftRadio;
    private javax.swing.JRadioButton manyTabsRadio;
    private javax.swing.JRadioButton miniRadio;
    private javax.swing.JRadioButton regularRadio;
    private javax.swing.JRadioButton rightRadio;
    private javax.swing.JRadioButton scrollRadio;
    private javax.swing.ButtonGroup sizeVariantGroup;
    private javax.swing.JRadioButton smallRadio;
    private javax.swing.ButtonGroup tabCountGroup;
    private javax.swing.ButtonGroup tabLayoutPolicyGroup;
    private javax.swing.ButtonGroup tabPlacementGroup;
    private javax.swing.JTabbedPane tabbedPane1;
    private javax.swing.JRadioButton topRadio;
    private javax.swing.JRadioButton wrapRadio;
    // End of variables declaration//GEN-END:variables
}
