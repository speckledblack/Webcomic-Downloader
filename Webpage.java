package app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Webpage {
	public URL url;
	public String html;
	public byte[] image;
	Webpage(String x) throws MalformedURLException, IOException 
	{
		x = sfix(x);
		//System.out.println(x);
		html = "";
        BufferedReader br = null;

        try {

            url = new URL(x);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();
            int count = 0;
            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            html = html + sb + '\n';
            //System.out.println(count + " " + sb);
            count++;
        } finally {

            if (br != null) 
                br.close();
            
        }
        
        //System.out.println(html);
        
    }
	public void Webpage(String x) throws MalformedURLException, IOException 
	{
		html = "";
        BufferedReader br = null;
        //System.out.println(x);
        x = sfix(x);
        try {

            url = new URL(x);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            html = html + sb + '\n';
            //System.out.println(sb);
        } finally {

            if (br != null) 
                br.close();
            
        }
        /*
        FileOutputStream fos = new FileOutputStream("C:\\Users\\1stew\\Documents\\Smbc\\g.txt");
        byte[] ht = html.getBytes();
        fos.write(ht);
        fos.close();
        */
        //System.out.println(html);
        
	}
	public void getImage(String get) throws MalformedURLException, IOException 
	{
		//System.out.println("get = \n" + get);
		get = sfix(get);
		URL url = new URL(get);
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
        image = out.toByteArray();
        
	}
	public void saveImage(String give) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(give);
        fos.write(image);
        fos.close();
	}
	private String sfix(String x)
	{
		String y = x.replaceAll(" ", "%20");
		return y;
		
	}
}