/*******************************************************************************
 * Copyright (c) 2003, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jface.preference;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Provides labels for <code>IPreferenceNode</code> objects.
 *
 * @since 3.0
 */
public class PreferenceLabelProvider extends LabelProvider {

    /**
     * @param element must be an instance of <code>IPreferenceNode</code>.
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    @Override
	public String getText(Object element) {
        return ((IPreferenceNode) element).getLabelText();
    }

    /**
     * @param element must be an instance of <code>IPreferenceNode</code>.
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    @Override
	public Image getImage(Object element) {
        return ((IPreferenceNode) element).getLabelImage();
    }
}
