package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import model.SongList;
import model.CompareSong;


public class ListController {
	
	
/*read song from the txt file
 list alphabetical order*/
	 private ArrayList<SongList> readFile(String filePathName)
	   {
		   ArrayList <SongList> songLists = new ArrayList<SongList>();
		   BufferedReader buffReader;
		   Path file = Paths.get(filePathName);
		   try {

				if (!new File(filePathName).exists())
				{
				   return songLists;
				}
			buffReader = Files.newBufferedReader(file);
			   String textFile = buffReader.readLine();
				
			   while (textFile != null) { 
		              
				   String name = textFile;
		               
				   textFile = buffReader.readLine();
				   String artist = textFile;
		               
				   textFile = buffReader.readLine();
				   String album = textFile;
		               
				   textFile = buffReader.readLine();
				   String year = textFile;
		               
				   SongList temp = new SongList(name, artist, album, year);
				   songLists.add(temp);
		               
				   textFile = buffReader.readLine(); 
			   }
			  
				
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		      
		   Collections.sort(songLists, new CompareSong());
		   return songLists;
	   }

}
