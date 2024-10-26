package com.project_lp2.project_lp2.services;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import org.apache.commons.io.FilenameUtils;

public class ServiceMultimedia {
    
    public byte[] serv_convert_image_URL(String imagePath) throws IOException {
        byte[] imageBytes = null;

        if( imagePath != null ) {
            URL url_image = new URL( imagePath );

            URLConnection conexion = url_image.openConnection();
    
            try (InputStream inputStream = conexion.getInputStream()) {
    
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int longitud;
                while ((longitud = inputStream.read( buffer )) != -1) {
                    outputStream.write(buffer, 0, longitud);
                }
                
                imageBytes = outputStream.toByteArray();
            } catch ( Exception ex ) { ex.printStackTrace(); } 
        } else return imageBytes;
        return imageBytes;
    }

    public byte[] serv_convert_image_local(String photoPath) {
        byte[] imageBytes = null;
        if (photoPath == null) {
            return null; // Devuelve una cadena vacía o una imagen predeterminada
        }
        try (
            FileInputStream fis = new FileInputStream( photoPath );
            ByteArrayOutputStream bos = new ByteArrayOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int longitud;
    
            while ((longitud = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, longitud);
            }
            imageBytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return imageBytes;
    }

    public String serv_get_extension_image( String pathURL ) {
        return FilenameUtils.getExtension( pathURL );
    }

    public String serv_convert_base_image( byte[] blobData, String extension ) {   
        if ( blobData == null ) {
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString( blobData );     
        return "data:image/"+ extension + ";base64," + base64;
    }
}
