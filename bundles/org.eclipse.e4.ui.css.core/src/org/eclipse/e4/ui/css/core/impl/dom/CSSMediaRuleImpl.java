/*******************************************************************************
 * Copyright (c) 2008, 2015 Angelo Zerr and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 *     IBM Corporation - ongoing development
 *******************************************************************************/

package org.eclipse.e4.ui.css.core.impl.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSMediaRule;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.stylesheets.MediaList;

public class CSSMediaRuleImpl extends CSSRuleImpl implements CSSMediaRule {

	public CSSMediaRuleImpl(CSSStyleSheet parentStyleSheet, CSSRule parentRule,
			MediaListImpl mediaListImpl) {
		super(parentStyleSheet, parentRule);
		// TODO Auto-generated constructor stub
	}

	@Override
	public short getType() {
		return CSSRule.MEDIA_RULE;
	}

	// W3C CSSMediaRule API methods

	@Override
	public void deleteRule(int index) throws DOMException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public CSSRuleList getCssRules() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public MediaList getMedia() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}

	@Override
	public int insertRule(String rule, int index) throws DOMException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
	}


	// Additional methods

	public void setRuleList(CSSRuleList rules) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("NOT YET IMPLEMENTED");

	}

}
