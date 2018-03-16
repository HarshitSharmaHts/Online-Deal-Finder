package com.onlinedealfinder.model;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileHandler {
    public static String upload(String path, HttpServletRequest request) {
        String fileName=null;
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                String fname = null;
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()) {
                        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
                        fname = new File(item.getName()).getName();
                        fileName = path + File.separator + fname +"_"+timeStamp;
                        item.write( new File(fileName));
                    }
                }
            } catch (Exception ex) {
                return C.FILE.FILE_ERROR;
            }
            return fileName;
        }else{
            return C.FILE.FILE_ERROR;
        }
    }
}
