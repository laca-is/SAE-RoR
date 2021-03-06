// ----------------------------------------------------------------------------
// Copyright (C) 2012 Louise A. Dennis, Michael Fisher 
// 
// This file is part of Gwendolen
// 
// Gwendolen is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// Gwendolen is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with Gwendolen; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------

package gwendolen.ail_tutorials.tutorial4.answers;

import ail.mas.DefaultEnvironment;
import ail.mas.MAS;
import ail.util.AILConfig;
import ail.util.AILexception;
import ail.syntax.Unifier;
import ail.syntax.Action;
import ail.syntax.SendAction;
import ail.syntax.Literal;
import ail.syntax.Predicate;
import ajpf.util.AJPFLogger;
import ajpf.util.choice.UniformBoolChoice;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

import gov.nasa.jpf.annotation.FilterField;

/**
 * Environment for a Trash Robot Scenario.  Tailored for verification so that
 * the percepts are decided at random.
 * 
 * @author louiseadennis
 *
 */
public class RobotEnvEx1 extends DefaultEnvironment {
	boolean change = false;
	@FilterField
	UniformBoolChoice random;
	boolean canseehumanr = false;
	
	String logname = "gwendolen.ail_tutorials.tutorial3.answers.RobotEnvEx1";
	
	/**
	 * Constructor.
	 */
	public RobotEnvEx1() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ail.mas.DefaultEnvironment#getPercepts(java.lang.String, boolean)
	 */
	public Set<Predicate> getPercepts(String agName, boolean update) {
		if (AJPFLogger.ltFine(logname)) {
			String s = agName + " checking percepts";
			AJPFLogger.finer(logname, s);
		}
		Set<Predicate> percepts = new HashSet<Predicate>();
		if (agName.equals("searcher")) {
			if (change) {
				canseehumanr = random.nextBoolean();
				if (canseehumanr) {
					AJPFLogger.info(logname, "A human appears");
				}
			}
			if (canseehumanr) {
				AJPFLogger.fine(logname, "Agent can see a human");
				percepts.add(new Literal("human"));
			}
			change = false;
		
		} 
		return percepts;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see ail.mas.DefaultEnvironment#executeAction(java.lang.String, ail.syntax.Action)
	 */
   public Unifier executeAction(String agName, Action act) throws AILexception {
	   	Unifier theta = new Unifier();	   	
	   	change = true;
	   	super.executeAction(agName, act);
	   	 
    	return theta;
    }
   
	/*
	 * (non-Javadoc)
	 * @see ail.mas.DefaultEnvironment#setMAS(ail.mas.MAS)
	 */
	public void setMAS(MAS m) {
		super.setMAS(m);
		random = new UniformBoolChoice(m.getController());
	}

      
}



