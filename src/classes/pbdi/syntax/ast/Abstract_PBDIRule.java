// ----------------------------------------------------------------------------
// Copyright (C) 2017 Louise A. Dennis and Michael Fisher
//
// This file is part of the Python BDI Agent Model (PBDI) Library.
// 
// The PBDI Library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 3 of the License, or (at your option) any later version.
// 
// The EASS Library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with the EASS Library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
// 
// To contact the authors:
// http://www.csc.liv.ac.uk/~lad
//
//----------------------------------------------------------------------------
package pbdi.syntax.ast;

import ail.syntax.ast.Abstract_Guard;
import gov.nasa.jpf.vm.MJIEnv;

public class Abstract_PBDIRule {
	String rulename;
	Abstract_Guard guard;
	
	public Abstract_PBDIRule(String s) {
		rulename = s;
	}
	
	public void addGuard(Abstract_Guard g) {
		guard = g;
	}
	
	public Abstract_Guard getGuard() {
		return guard;
	}
	
	public String getName() {
		return rulename;
	}
	
    public int newJPFObject(MJIEnv env) {
	    	int objref = env.newObject("pbdi.syntax.ast.Abstract_PBDIRule");
	    	env.setReferenceField(objref, "rulename", env.newString(rulename));
	    	if (guard != null) {
	    		env.setReferenceField(objref, "guard", guard.newJPFObject(env));
	    	}
	 	return objref;
	
    }
}
