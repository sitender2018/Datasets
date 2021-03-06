/*******************************************************************************
 * Copyright (c) 2007, 2015 IBM Corporation and others.
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
 ******************************************************************************/

package org.eclipse.ui.internal;

import org.eclipse.core.expressions.PropertyTester;

/**
 * Tests various workbench window properties.
 *
 * @since 3.3
 *
 */
public class WorkbenchWindowPropertyTester extends PropertyTester {

	private static final String PROPERTY_IS_COOLBAR_VISIBLE = "isCoolbarVisible"; //$NON-NLS-1$
	private static final String PROPERTY_IS_PERSPECTIVEBAR_VISIBLE = "isPerspectiveBarVisible"; //$NON-NLS-1$

	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {

		if (args.length == 0 && receiver instanceof WorkbenchWindow) {
			boolean defaultExpectedValue = true;
			if (expectedValue != null) {
				if (expectedValue instanceof Boolean)
					defaultExpectedValue = ((Boolean) expectedValue).booleanValue();
				else
					return false; // cant work with anything else
			}
			final WorkbenchWindow window = (WorkbenchWindow) receiver;
			if (PROPERTY_IS_COOLBAR_VISIBLE.equals(property)) {
				return defaultExpectedValue == window.getCoolBarVisible();
			} else if (PROPERTY_IS_PERSPECTIVEBAR_VISIBLE.equals(property)) {
				return defaultExpectedValue == window.getPerspectiveBarVisible();
			}
		}
		return false;
	}
}
