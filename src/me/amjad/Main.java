package me.amjad;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Amjad Almutairi on June 1,2017.
 */

public class Main {
    private final static String ANSI_BLUE = "\u001B[34m";
    private final static String ANSI_PURPLE = "\u001B[35m";
    private final static String ANSI_RED = "\u001B[31m";
    private final static String ANSI_GREEN = "\u001B[32m";
    private final static String ANSI_CYNE = "\u001B[36m";
    private final static String HIGH_INTENSITY = "\u001B[1m"; //bold


    public static void main(String[] args) {

        //User desktop path
        File Desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");
        //Name of the folder that will hold the converted text files
        File ConvertedFiles = new File(Desktop + File.separator + "ConvertedFiles");

        if(!ConvertedFiles.exists())
        {
            ConvertedFiles.mkdir();
        }

        String folderName ;

        System.out.println(HIGH_INTENSITY + ANSI_BLUE + "\nEnter the folder name that contains the files of cp1256 encoding: ");
        System.out.println(ANSI_PURPLE + "** The folder must be on your desktop **");

        Scanner sc = new Scanner(System.in);
        folderName = sc.next();

        File theFolder = new File (Desktop + File.separator + folderName );

        if(theFolder.exists())
        {

            File [] files = theFolder.listFiles();

            for (int i = 0; i < files.length ; i++) {

                try {

                    //1.read the text as cp1256 and store it in a string
                    BufferedReader in = new BufferedReader( new InputStreamReader(new FileInputStream(files[i]), "cp1256"));
                    String str;
                    String content = "" ;

                    while ((str = in.readLine()) != null) {

                        content = content.concat(str+"\n");
                    }

                    //2.convert string encoding to utf-8
                    byte[] bytes = content.getBytes("UTF-8");
                    String convertedText = new String(bytes , "UTF-8");

                    //3.create a new file with same file name
                    File newFile = new File(ConvertedFiles.getAbsolutePath() + File.separator + files[i].getName());
                    newFile.createNewFile();

                    //4.write on the file the text with utf-8 encoding
                    FileWriter fw = new FileWriter(newFile);
                    fw.write(convertedText);
                    fw.flush();
                    fw.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println( ANSI_GREEN + HIGH_INTENSITY  + "\n" + "****************  Done Successfully! ****************");
            System.out.println(ANSI_CYNE + "** The converted files are in (ConvertedFiles) folder in your desktop, Enjoy! **");
        }

        else
        {
            System.out.println(ANSI_RED + HIGH_INTENSITY + "\n"  + "Error: The folder name is not correct! or the folder is not in your desktop!");
            System.out.println(ANSI_RED + ">> The entered folder name: " + folderName);
        }

    }
}