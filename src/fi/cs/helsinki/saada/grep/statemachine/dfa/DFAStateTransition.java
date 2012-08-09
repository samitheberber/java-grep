package fi.cs.helsinki.saada.grep.statemachine.dfa;

import fi.cs.helsinki.saada.grep.statemachine.state_transition.AbstractStateTransition;
import fi.cs.helsinki.saada.grep.statemachine.state_transition.StateTransitionStructure;

public class DFAStateTransition extends AbstractStateTransition {

    public DFAStateTransition(StateTransitionStructure structure) {
        super(structure);
    }

}

