package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.SubjectObject;

public class FastLookUpSubjectObject {
	private static final Map<String,Set<String>> data = new HashMap<>();
	
	public FastLookUpSubjectObject(final List<SubjectObject> sos) {
		for(SubjectObject so:sos) {
			final Set<String>  set = data.get(so.getObject());
			if(set == null) {
				data.put(so.getObject(), new HashSet<String>());
			}else {
				set.add(so.getSubject());
				data.put(so.getObject(), set);
			}
		}
	}
	
	public static Map<String,Set<String>> getFastlookUpSubjectObjects() {
		return data;
	}
}
