/*
 * @(#)QuaquaFocusHandler.java  
 *
 * Copyright (c) 2004-2010 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package ch.randelshofer.quaqua;

import java.awt.event.*;
import javax.swing.*;
/**
 * QuaquaFocusHandler.
 *
 * @author  Werner Randelshofer
 * @version $Id: QuaquaFocusHandler.java 361 2010-11-21 11:19:20Z wrandelshofer $
 */
public class QuaquaFocusHandler implements FocusListener {
    private static QuaquaFocusHandler instance;
    
    public static QuaquaFocusHandler getInstance() {
        if (instance == null) {
            instance = new QuaquaFocusHandler();
        }
        return instance;
    }
    
    
    /**
     * Prevent instance creation.
     */
    private QuaquaFocusHandler() {
    }
    
    public void focusGained(FocusEvent event) {
            QuaquaUtilities.repaintBorder((JComponent) event.getComponent());
    }
    
    public void focusLost(FocusEvent event) {
            QuaquaUtilities.repaintBorder((JComponent) event.getComponent());
    }
}

