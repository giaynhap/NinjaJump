package Object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

 
public class Container {
	private HashMap<String, Integer> container = new HashMap<String, Integer>();;
	public void addItem(String name,int num)
	{
		container.put(name,  getItem(name)+num);
	}
	public int getItem(String name)
	{	
	 Integer i = container.get(name);
	 if ( i==null) return 0;
		return i ;
	}
	public ArrayList< String> getItems()
	{
		ArrayList< String> rtn = new ArrayList< String>();
		Set set = container.entrySet();
	    Iterator i = set.iterator();
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         rtn.add((String) me.getKey());
	      }
		return rtn;
		
	}
}
