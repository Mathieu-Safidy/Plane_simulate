package org.springcopy.core;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mapping {

    String className;
	VerbAction action;
	Set<VerbAction> listAction;

	
	public Mapping(String className, VerbAction verb) {
		this.className = className;
		this.action = verb;
	}
	public Mapping(String className){
		this.className = className;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public VerbAction getAction() {
		return action;
	}
	public Set<VerbAction> getVerbActions() {
		return listAction;
	}
	public void setAction(VerbAction verb) {
		this.action = verb;
	}
	public void addVerbAction(VerbAction act)throws Exception {
		try {
			if (this.listAction == null) {
				this.listAction = new HashSet<>() ;
			}
			this.listAction.add(act);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Un lien ne peut contenir deux meme verb action 	");
		}
	
	}



	
    // String method;
    
    // public Mapping(String className, String method) {
    //     this.className = className;
    //     this.method = method;
    // }

    // public String getClassName() {
	// 	return className;
	// }

	// public void setClassName(String className) {
	// 	this.className = className;
	// }

	// public String getMethod() {
	// 	return this.method;
	// }

	

	
    
}
