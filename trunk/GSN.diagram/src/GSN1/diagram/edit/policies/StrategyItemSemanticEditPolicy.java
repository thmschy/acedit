/*
 * 
 */
package GSN1.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import GSN1.diagram.edit.commands.InContextOfCreateCommand;
import GSN1.diagram.edit.commands.InContextOfReorientCommand;
import GSN1.diagram.edit.commands.SolvedByCreateCommand;
import GSN1.diagram.edit.commands.SolvedByReorientCommand;
import GSN1.diagram.edit.commands.SolvedUsingContractCreateCommand;
import GSN1.diagram.edit.commands.SolvedUsingContractReorientCommand;
import GSN1.diagram.edit.parts.InContextOfEditPart;
import GSN1.diagram.edit.parts.SolvedByEditPart;
import GSN1.diagram.edit.parts.SolvedUsingContractEditPart;
import GSN1.diagram.part.GSN1VisualIDRegistry;
import GSN1.diagram.providers.GSN1ElementTypes;

/**
 * @generated
 */
public class StrategyItemSemanticEditPolicy extends
		GSN1BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StrategyItemSemanticEditPolicy() {
		super(GSN1ElementTypes.Strategy_2002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (GSN1VisualIDRegistry.getVisualID(incomingLink) == SolvedUsingContractEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (GSN1VisualIDRegistry.getVisualID(incomingLink) == SolvedByEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (GSN1VisualIDRegistry.getVisualID(outgoingLink) == SolvedUsingContractEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (GSN1VisualIDRegistry.getVisualID(outgoingLink) == SolvedByEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (GSN1VisualIDRegistry.getVisualID(outgoingLink) == InContextOfEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (GSN1ElementTypes.SolvedUsingContract_4001 == req.getElementType()) {
			return getGEFWrapper(new SolvedUsingContractCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (GSN1ElementTypes.SolvedBy_4002 == req.getElementType()) {
			return getGEFWrapper(new SolvedByCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (GSN1ElementTypes.InContextOf_4003 == req.getElementType()) {
			return getGEFWrapper(new InContextOfCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (GSN1ElementTypes.SolvedUsingContract_4001 == req.getElementType()) {
			return getGEFWrapper(new SolvedUsingContractCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (GSN1ElementTypes.SolvedBy_4002 == req.getElementType()) {
			return getGEFWrapper(new SolvedByCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (GSN1ElementTypes.InContextOf_4003 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case SolvedUsingContractEditPart.VISUAL_ID:
			return getGEFWrapper(new SolvedUsingContractReorientCommand(req));
		case SolvedByEditPart.VISUAL_ID:
			return getGEFWrapper(new SolvedByReorientCommand(req));
		case InContextOfEditPart.VISUAL_ID:
			return getGEFWrapper(new InContextOfReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
