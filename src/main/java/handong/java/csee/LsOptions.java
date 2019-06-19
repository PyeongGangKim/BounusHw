package handong.java.csee;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.cli.*;
public class LsOptions {
	private boolean size;
	private boolean reverse;
	private boolean directoryChecker;
	private boolean lastModifedTime;
	private boolean allTheFile;
	private boolean help;
	private ArrayList<String> fileNames;
	String currentPath;
	File currentDirectory;
	private File[] allFiles;
	public void run(String[] args) {
		Options options = new Options();
		createOptions(options);
		currentPath = System.getProperty("user.dir");
		currentDirectory= new File(currentPath);
		allFiles=currentDirectory.listFiles();
		if(parseOptions(options, args)) {
			if (help){
				printHelp(options);
				return;
			}
			
			if(size) {
				for(File file:allFiles) {
					if(file.getName().indexOf(".")!=0)
					System.out.println(file.getName()+" "+file.length()+"bytes");
				}
				
			}
			if(reverse) {
				fileNames=new ArrayList<String>();
				for(File file:allFiles) {
					if(file.getName().indexOf(".")!=0)
					fileNames.add(file.getName());					
				}
		        Collections.sort(fileNames);
		        Collections.reverse(fileNames);
		        for(String name:fileNames) {

		        	System.out.println(name);
		        	
		        }

			}
			if(directoryChecker) {
				for(File file:allFiles) {
					if(file.isDirectory()) {
						if(file.getName().indexOf(".")!=0)
						System.out.println(file.getName()+"/");
					}
					else {
						if(file.getName().indexOf(".")!=0)
						System.out.println(file.getName());
					}
				}				
			}
			if(lastModifedTime) {
				SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-dd / HH:mm:ss");
				for(File file:allFiles) {
					if(file.getName().indexOf(".")!=0)
					System.out.println(file.getName()+" "+pattern.format(file.lastModified()));
				}
				
			}
			if(allTheFile) {
				for(File file:allFiles) {
					System.out.println(file.getName());
				}
			}
			if(!help&&!size&&!reverse&&!directoryChecker&&!lastModifedTime&&!allTheFile) {
				for(File file:allFiles) {
					if(file.getName().indexOf(".")!=0)
					System.out.println(file.getName());
			}
			
		}
			
		}
	}
	
	private void createOptions(Options options) {

		options.addOption(Option.builder("s").longOpt("size")
				.desc("Get the file size in current directory by bytes")
				//.hasArg()
				.argName("File size")
				//.required()
				.build());
		
		options.addOption(Option.builder("r").longOpt("reverse")
				.desc("Print the files in current directory in reverse order")
				//.hasArg()
				.argName("Reverse order")
				//.required()
				.build());
		
		options.addOption(Option.builder("d").longOpt("direcotryChecker")
				.desc("If it is directory, print with '/' at the end")
				//.hasArg()
				.argName("Distingusih directory")
				//.required()
				.build());
		
		options.addOption(Option.builder("t").longOpt("lastModifedTime")
				.desc("Print the last modified time about file")
				//.hasArg()
				.argName("last modifed time")
				//.required()
				.build());
		options.addOption(Option.builder("a").longOpt("all")
				.desc("Print all the file in current directory")
				//.hasArg()
				.argName("all the file")
				//.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				//.hasArg()
				.argName("Help")
				//.required()
				.build());
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			size = cmd.hasOption("s");
			reverse = cmd.hasOption("r");
			directoryChecker = cmd.hasOption("d");
			lastModifedTime = cmd.hasOption("t");
			allTheFile= cmd.hasOption("a");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
		
	}
	private void printHelp(Options options) {
		// automatically generate the help statement
				HelpFormatter formatter = new HelpFormatter();
				String header = "HGU Course Analyzer";
				String footer ="";
				formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}
}
