/*
 * @(#)QuaquaLionNativeTabBorder.java 
 * 
 * Copyright (c) 2011 Werner Randelshofer, Immensee, Switzerland.
 * All rights reserved.
 * 
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package ch.randelshofer.quaqua.lion;

import ch.randelshofer.quaqua.border.QuaquaNativeBorder;
import ch.randelshofer.quaqua.osx.OSXAquaPainter.Widget;
import java.awt.Insets;

/**
 * {@code QuaquaLionNativeTabBorder}.
 *
 * @author Werner Randelshofer
 * @version $Id: QuaquaLionNativeTabBorder.java 439 2011-08-28 10:05:15Z wrandelshofer $
 */
public class QuaquaLionNativeTabBorder extends QuaquaNativeBorder {

    public QuaquaLionNativeTabBorder(int cacheSize, Insets imageInsets, Insets borderInsets) {
        super(cacheSize, Widget.tab, imageInsets, borderInsets);
    }

    public QuaquaLionNativeTabBorder(Insets imageInsets, Insets borderInsets) {
        super(Widget.tab,  imageInsets, borderInsets);
    }

    public QuaquaLionNativeTabBorder(int cacheSize) {
        super(cacheSize, Widget.tab);
    }

    public QuaquaLionNativeTabBorder() {
        super(Widget.tab);
    }
    
}
