/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ImageReader {

    public static void ReaderImg() {
        String folderPath = "C:\\images\\customers"; // Replace with the actual folder path

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path: " + folderPath);
            return;
        }

        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the folder: " + folderPath);
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String fileUrl = file.toURI().toString();
                System.out.println("Name: " + fileName);
                System.out.println("URL: " + fileUrl);
                System.out.println();
            }
        }
    }

    public static String createFolder(String parentDirectory) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        String folderPath = parentDirectory + File.separator + formattedDate;

        File folder = new File(folderPath);
        boolean folderCreated = folder.mkdirs();

        if (folderCreated) {
            System.out.println("Folder created successfully: " + folderPath);
        } else {
            System.out.println("Failed to create folder: " + folderPath);
        }

        return folderPath;
    }

    

    public static String createFolderSrc(String folderPath) {
        File folder = new File(folderPath);
        boolean folderCreated = folder.mkdirs();

        if (folderCreated) {
            System.out.println("Folder created successfully: " + folderPath);
        } else {
            System.out.println("Failed to create folder: " + folderPath);
        }
        return folderPath;
    }

}
