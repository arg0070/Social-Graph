package es.ubu.nte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class FileWrite {
	
	/** Nodes. */
	private Set<String> nodes;
	
	/** Edges. */
	private List<String> edges;
	
	
	public FileWrite(Set<String> nodes, List<String> edges){
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public void writeNodes(String name){
		int id = 1;
		try {
			  
			File file = new File(name);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Id,Label\n");
			for(String node : nodes){
				bw.write(id +","+node+"\n");
				id ++;
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeEdges(String name){
		int id = 1;
		try {
			  
			File file = new File(name);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("Source,Target,Type,Id,Label,Weight\n");
			for(String edge : edges){
				bw.write(edge+",Directed,"+id+",,1.0\n");
				id ++;
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
