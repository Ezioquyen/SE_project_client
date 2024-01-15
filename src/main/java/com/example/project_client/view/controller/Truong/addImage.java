package com.example.project_client.view.controller.Truong;

import java.io.*;
import java.net.URL;

public final class addImage {
    public static void saveImage(){
        try {URL url = new URL("https://media-vov.emitech.vn/sites/default/files/styles/large/public/2022-03/cf.png.jpg");
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Laptop K1\\IdeaProjects\\SE_project_client_Truong\\src\\main\\resources\\com\\example\\project_client\\images\\cf.jpg".replace("\\", "/"));
            fos.write(response);
            fos.close();
        } catch (IOException e) {
            // handle IOException
            System.out.println(e.getMessage());
        }
    }
}
