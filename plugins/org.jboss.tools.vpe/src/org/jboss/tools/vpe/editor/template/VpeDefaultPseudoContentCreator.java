/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.vpe.editor.template;

import java.util.HashSet;
import java.util.Set;

import org.jboss.tools.vpe.editor.context.VpePageContext;
import org.jboss.tools.vpe.editor.util.HTML;
import org.mozilla.interfaces.nsIDOMDocument;
import org.mozilla.interfaces.nsIDOMElement;
import org.mozilla.interfaces.nsIDOMNode;
import org.w3c.dom.Node;

public class VpeDefaultPseudoContentCreator extends VpePseudoContentCreator {

	private static VpePseudoContentCreator INSTANCE = null;
	private static Set withoutPseudoContentSet = new HashSet();
	
	static {
		withoutPseudoContentSet.add("br");
		withoutPseudoContentSet.add("nobr");
		withoutPseudoContentSet.add("input");
		withoutPseudoContentSet.add("textarea");
		withoutPseudoContentSet.add("select");
	}

	public static final VpePseudoContentCreator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VpeDefaultPseudoContentCreator();
		}
		return INSTANCE;
	}

	public void setPseudoContent(VpePageContext pageContext, Node sourceContainer, nsIDOMNode visualContainer, nsIDOMDocument visualDocument) {
		if (!withoutPseudoContentSet.contains(visualContainer.getNodeName().toLowerCase())) {
			nsIDOMElement visualPseudoElement = visualDocument.createElement(HTML.TAG_BR);
			setPseudoAttribute(visualPseudoElement);
			visualContainer.appendChild(visualPseudoElement);
		}
	}
}
