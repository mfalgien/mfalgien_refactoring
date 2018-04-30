package main.java.memoranda;

import java.util.Vector;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

public class Day {
    Element dEl = null;

    public Day(Element el) {
        dEl = el;
        // Added to fix old '.notes' XML format 
        // Old-style XML is converted on the fly [alexeya]
        if (dEl.getAttribute("date") != null) {
        	Attribute dAttr = dEl.getAttribute("date");
        	Attribute tAttr = dEl.getAttribute("title");
			Element nEl = new Element("note");
			String date = dAttr.getValue().replace('/', '-');
			nEl.addAttribute(new Attribute("refid", date));
			nEl.addAttribute(new Attribute("title", tAttr.getValue()));
			dEl.appendChild(nEl);
        	dEl.removeAttribute(dAttr);            	
			dEl.removeAttribute(tAttr);
        }
    }

    public int getValue() {
        return new Integer(dEl.getAttribute("day").getValue()).intValue();
    }

    /*public Note getNote() {
        return new NoteImpl(dEl);
    }*/
	
	public NoteElement getNote(String d) {
        if (dEl == null) 
			return null;
        Elements ne = dEl.getChildElements("note");
        
        for (int i = 0; i < ne.size(); i++)
            if (ne.get(i).getAttribute("refid").getValue().equals(d))
                return new NoteElement(ne.get(i));
        //return createDay(d);
        return null;
    }

    public NoteElement createNote(String d) {
        Element el = new Element("note");
//		el.addAttribute(new Attribute("refid", d));
/*            el.addAttribute(new Attribute("day", new Integer(d).toString()));
                    el.addAttribute(
            new Attribute(
                "date",
                new CalendarDate(
                    10,
                    10,
                    2004).toString()));
*/						
        dEl.appendChild(el);
        return new NoteElement(el);
    }

    public Vector getNotes() {
        if (dEl == null)
            return null;
        Vector v = new Vector();
        Elements ds = dEl.getChildElements("note");
        for (int i = 0; i < ds.size(); i++)
            v.add(new NoteElement(ds.get(i)));                                    
        return v;
    }

    public Element getElement() {
        return dEl;
    }
}
