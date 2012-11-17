/*
 * @(#)QuaquaDefaultTableCellRenderer.java  
 *
 * Copyright (c) 2006-2010 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */

package ch.randelshofer.quaqua;

import javax.swing.table.*;
/**
 * QuaquaDefaultTableCellRenderer.
 *
 * @author Werner Randelshofer
 * @version $Id: QuaquaDefaultTableCellRenderer.java 361 2010-11-21 11:19:20Z wrandelshofer $
 */
public class QuaquaDefaultTableCellRenderer extends DefaultTableCellRenderer.UIResource {
    
    /** Creates a new instance. */
    public QuaquaDefaultTableCellRenderer() {
        setOpaque(false);
    }
    
}
