package es.ubu.nte;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.select.Elements;

/**
 * Hello world!
 * 
 */
public class App {
	/** Nodes counter. */
	private static int num_nodes = 0;

	/** Max number of iteractions. */
	private static final int MAX_COUNT = 350;

	/** List of nodes. */
	private static Set<String> nodes;

	/** List of edges. */
	private static List<String> edges;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		nodes = new LinkedHashSet<String>();
		edges = new ArrayList<String>();

		nodes.add(args[0]);

		System.out.println("Operation in progress...");
		
		while (num_nodes < MAX_COUNT){
			newSearch((setToList(nodes)).get(num_nodes));
			num_nodes++;
		}
		
		FileWrite f = new FileWrite(nodes, edges);
		f.writeNodes("nodes.csv");
		f.writeEdges("edges.csv");
		/*for(String node : nodes)
			System.out.println(node);
		for(String edge : edges)
			System.out.println(edge);
*/
	}

	public static void newSearch(String html) {
		Set<String> links = new HashSet<String>();
		HTMLParser p = new HTMLParser(html);
		links = p.linkParser();
		
		nodes.addAll(links);
		setEdges(links);
	}
	
	private static void setEdges(Set<String> links){
		List<String> list = new ArrayList<String>();
		list = setToList(nodes);
		
		for(String link : links){
			edges.add((num_nodes+1) +","+ (list.indexOf(link)+1));
		}	
	}

	private static List<String> setToList(Set<String> set) {
		List<String> list = new ArrayList<String>();
		for (String subset : set) {
			list.add(subset);
		}
		return list;
	}

}
