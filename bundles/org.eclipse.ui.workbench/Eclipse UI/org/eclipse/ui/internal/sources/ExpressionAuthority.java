/******************************************************************************* * Copyright (c) 2005 IBM Corporation and others. * All rights reserved. This program and the accompanying materials * are made available under the terms of the Eclipse Public License v1.0 * which accompanies this distribution, and is available at * http://www.eclipse.org/legal/epl-v10.html * * Contributors: *     IBM Corporation - initial API and implementation ******************************************************************************/package org.eclipse.ui.internal.sources;import java.util.ArrayList;import java.util.Collection;import java.util.Collections;import java.util.Iterator;import java.util.Map;import org.eclipse.core.expressions.EvaluationContext;import org.eclipse.core.expressions.Expression;import org.eclipse.core.expressions.IEvaluationContext;import org.eclipse.jface.viewers.IStructuredSelection;import org.eclipse.ui.ISourceProvider;import org.eclipse.ui.ISourceProviderListener;import org.eclipse.ui.ISources;/** * <p> * Provides common functionality for evaluating expressions and listening to * {@link ISourceProvider} (i.e., the common event framework for commands). * </p> * <p> * <strong>EXPERIMENTAL</strong>. This class or interface has been added as * part of a work in progress. There is a guarantee neither that this API will * work nor that it will remain the same. Please do not use this API without * consulting with the Platform/UI team. * </p> *  * @since 3.2 * @see ISourceProvider * @see ISources * @see Expression * @see IEvaluationContext */public abstract class ExpressionAuthority implements ISourceProviderListener {	/**	 * The evaluation context instance to use when evaluating expression. This	 * context is shared, and so all calls into <code>sourceChanged</code>	 * must happen on the event thread.	 */	private final IEvaluationContext context;	/**	 * The collection of source providers used by this authority. This	 * collection is consulted whenever a contribution is made. This collection	 * only contains instances of <code>ISourceProvider</code>.	 */	private final Collection providers = new ArrayList();	/**	 * Constructs a new instance of <code>ExpressionAuthority</code>.	 */	protected ExpressionAuthority() {		context = new EvaluationContext(null, this);	}	/**	 * Adds a source provider to a list of providers to check when updating.	 * This also attaches this authority as a listener to the provider.	 * 	 * @param provider	 *            The provider to add; must not be <code>null</code>.	 */	public final void addSourceProvider(final ISourceProvider provider) {		provider.addSourceProviderListener(this);		providers.add(provider);		updateCurrentState();	}	/**	 * Returns whether at least one of the <code>IEvaluationResultCache</code>	 * instances in <code>collection</code> evaluates to <code>true</code>.	 * 	 * @param collection	 *            The evaluation result caches to check; must not be	 *            <code>null</code>, but may be empty.	 * @return <code>true</code> if there is at least one expression that	 *         evaluates to <code>true</code>; <code>false</code>	 *         otherwise.	 */	protected final boolean evaluate(final Collection collection) {		final Iterator iterator = collection.iterator();		while (iterator.hasNext()) {			final IEvaluationResultCache cache = (IEvaluationResultCache) iterator					.next();			if (evaluate(cache)) {				return true;			}		}		return false;	}	/**	 * Returns whether the <code>IEvaluationResultCache</code> evaluates to	 * <code>true</code>.	 * 	 * @param expression	 *            The evaluation result cache to check; must not be	 *            <code>null</code>.	 * @return <code>true</code> if the expression evaluates to	 *         <code>true</code>; <code>false</code> otherwise.	 */	protected final boolean evaluate(final IEvaluationResultCache expression) {		final Object defaultVariable = context				.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);		final IEvaluationContext contextWithDefaultVariable;		if (defaultVariable instanceof IStructuredSelection) {			final IStructuredSelection selection = (IStructuredSelection) defaultVariable;			contextWithDefaultVariable = new EvaluationContext(context,					selection);		} else if (defaultVariable == null) {			contextWithDefaultVariable = new EvaluationContext(context,					Collections.EMPTY_LIST);		} else {			contextWithDefaultVariable = new EvaluationContext(context,					defaultVariable);		}		return expression.evaluate(contextWithDefaultVariable);	}	/**	 * Returns the variable of the given name.	 * 	 * @param name	 *            The name of the variable to get; must not be <code>null</code>.	 * @return The variable of the given name; <code>null</code> if none.	 */	protected final Object getVariable(final String name) {		return context.getVariable(name);	}	/**	 * Removes this source provider from the list, and detaches this authority	 * as a listener.	 * 	 * @param provider	 *            The provider to remove; must not be <code>null</code>.	 */	public final void removeSourceProvider(final ISourceProvider provider) {		provider.removeSourceProviderListener(this);		providers.remove(provider);		updateCurrentState();	}	/**	 * Changes the variable of the given name. If the <code>value</code> is	 * <code>null</code>, then the	 * 	 * @param name	 * @param value	 */	protected final void changeVariable(final String name, final Object value) {		if (value == null) {			context.removeVariable(name);		} else {			context.addVariable(name, value);		}	}	/**	 * Carries out the actual source change notification. It assumed that by the	 * time this method is called, <code>getEvaluationContext()</code> is	 * up-to-date with the current state of the application.	 * 	 * @param sourcePriority	 *            A bit mask of all the source priorities that have changed.	 */	protected abstract void sourceChanged(final int sourcePriority);	public final void sourceChanged(final int sourcePriority,			final Map sourceValuesByName) {		final Iterator entryItr = sourceValuesByName.entrySet().iterator();		while (entryItr.hasNext()) {			final Map.Entry entry = (Map.Entry) entryItr.next();			final String sourceName = (String) entry.getKey();			final Object sourceValue = entry.getValue();			updateEvaluationContext(sourceName, sourceValue);		}		sourceChanged(sourcePriority);	}	public final void sourceChanged(final int sourcePriority,			final String sourceName, final Object sourceValue) {		updateEvaluationContext(sourceName, sourceValue);		sourceChanged(sourcePriority);	}	/**	 * Updates the evaluation context with the current state from all of the	 * source providers.	 */	protected final void updateCurrentState() {		final Iterator providerItr = providers.iterator();		while (providerItr.hasNext()) {			final ISourceProvider provider = (ISourceProvider) providerItr					.next();			final Map currentState = provider.getCurrentState();			final Iterator variableItr = currentState.entrySet().iterator();			while (variableItr.hasNext()) {				final Map.Entry entry = (Map.Entry) variableItr.next();				final String variableName = (String) entry.getKey();				final Object variableValue = entry.getValue();				/*				 * Bug 84056. If we update the active workbench window, then we				 * risk falling back to that shell when the active shell has				 * registered as "none".				 */				if ((variableName != null)						&& (!ISources.ACTIVE_WORKBENCH_WINDOW_NAME								.equals(variableName))) {					if (variableValue == null) {						context.removeVariable(variableName);					} else {						context.addVariable(variableName, variableValue);					}				}			}		}	}	/**	 * Updates this authority's evaluation context.	 * 	 * @param name	 *            The name of the variable to update; must not be	 *            <code>null</code>.	 * @param value	 *            The new value of the variable. If this value is	 *            <code>null</code>, then the variable is removed.	 */	protected void updateEvaluationContext(final String name, final Object value) {		if (name != null) {			changeVariable(name, value);		}	}}
