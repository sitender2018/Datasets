/*******************************************************************************
 * Copyright (c) 2009, 2017 IBM Corporation and others.
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
 *     Daniel Kruegler <daniel.kruegler@gmail.com> - Bug 493459
 *******************************************************************************/
package org.eclipse.e4.core.commands;

import java.util.Map;
import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.ParameterizedCommand;

/**
 * @noimplement
 */
public interface ECommandService {
	public ParameterizedCommand createCommand(String id, Map<String, ?> parameters);

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @return
	 * @noreference
	 */
	public Category defineCategory(String id, String name, String description);

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param category
	 * @param parameters
	 * @return
	 * @noreference
	 */
	public Command defineCommand(String id, String name, String description, Category category,
			IParameter[] parameters);

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param category
	 * @param parameters
	 * @param helpContextId
	 * @return
	 * @noreference
	 */
	public Command defineCommand(String id, String name, String description, Category category,
			IParameter[] parameters,String helpContextId);

	public Category getCategory(String categoryId);

	public Command getCommand(String commandId);
}
