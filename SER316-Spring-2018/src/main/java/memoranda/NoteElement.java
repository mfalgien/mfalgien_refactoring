package main.java.memoranda;

import nu.xom.Element;

public class NoteElement {
	Element nEl;
	
	public NoteElement(Element el) {
		nEl = el;
	}
	
	public Element getElement() {
		return nEl;
	}
}
