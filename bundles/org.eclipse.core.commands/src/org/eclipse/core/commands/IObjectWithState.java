/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.core.commands;

/**
 * <p>
 * An object that holds zero or more state objects. This state information can
 * be shared between different instances of <code>IObjectWithState</code>.
 * </p>
 * <p>
 * Clients may implement, but must not extend this interface.
 * </p>
 * <p>
 * <strong>EXPERIMENTAL</strong>. This class or interface has been added as
 * part of a work in progress. There is a guarantee neither that this API will
 * work nor that it will remain the same. Please do not use this API without
 * consulting with the Platform/UI team.
 * </p>
 * 
 * @see AbstractHandlerWithState
 * @since 3.2
 */
public interface IObjectWithState {

	/**
	 * Adds state to this object.
	 * 
	 * @param id
	 *            The identifier indicating the type of state being added; must
	 *            not be <code>null</code>.
	 * @param state
	 *            The new state to add to this object; must not be
	 *            <code>null</code>.
	 */
	public void addState(String id, IState state);

	/**
	 * Gets the state with the given id.
	 * 
	 * @param stateId
	 *            The identifier of the state to retrieve; must not be
	 *            <code>null</code>.
	 * @return The state; may be <code>null</code> if there is no state with
	 *         the given id.
	 */
	public IState getState(String stateId);

	/**
	 * Gets the identifiers for all of the state associated with this object.
	 * 
	 * @return All of the state identifiers; may be <code>null</code> if there
	 *         is no state associated with this object.
	 */
	public String[] getStateIds();

	/**
	 * Removes state from this object.
	 * 
	 * @param stateId
	 *            The id of the state to remove from this object; must not be
	 *            <code>null</code>.
	 */
	public void removeState(String stateId);
}
