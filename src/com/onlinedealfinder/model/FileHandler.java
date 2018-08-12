package com.onlinedealfinder.model;

import com.onlinedealfinder.controller.AmazonClient;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class FileHandler {
    public static String upload(HttpServletRequest request) throws ServletException, IOException {

        String fileName = null;

        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                String fname = null;
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()) {
                        fileName = AmazonClient.getInstance().uploadFile(item);
                    }
                }
                System.out.println("TRY");
            } catch (Exception ex) {
                System.out.println("CATCH");
                return C.FILE.FILE_ERROR;
            }
            return fileName;
        }else{
            System.out.println("ELSE");
            return C.FILE.FILE_ERROR;
        }
    }
}
