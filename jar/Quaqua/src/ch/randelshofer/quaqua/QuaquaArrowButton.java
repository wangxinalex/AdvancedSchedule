/*
 * @(#)QuaquaArrowButton.java 
 *
 * Copyright (c) 2005-2010 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package ch.randelshofer.quaqua;

import java.awt.*;
import javax.swing.*;
/**
 * QuaquaArrowButton is used handle events for the arrow buttons of a
 * QuaquaScrollBarUI. Since the QuaquaScrollBarUI does all the button drawing,
 * the button is completely transparent.
 *
 * @author  Werner Randelshofer
 * @version $Id: QuaquaArrowButton.java 361 2010-11-21 11:19:20Z wrandelshofer $
 */
public class QuaquaArrowButton extends JButton implements SwingConstants {
    private JScrollBar scrollbar;
    
    public QuaquaArrowButton(JScrollBar scrollbar) {
        this.scrollbar = scrollbar;
        setRequestFocusEnabled(false);
        setOpaque(false);
    }
    
    public void paint(Graphics g) {
        return;
    }
    /*
    public Dimension getPreferredSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            if (scrollbar.getFont().getSize() <= 11) {
                return new Dimension(11, 12);
            } else {
                return new Dimension(15, 16);
            }
        } else {
            if (scrollbar.getFont().getSize() <= 11) {
                return new Dimension(12, 11);
            } else {
                return new Dimension(16, 15);
            }
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(5, 5);
    }
    
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }*/
    
    public boolean isFocusTraversable() {
        return false;
    }
}
