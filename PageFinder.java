package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class PageFinder extends Webpage
{
	String ext = "";
	boolean fullPage = false;
	String title = "";
	String comicHost = "";
	PageFinder(String x, boolean y) throws MalformedURLException, IOException 
	{
		
		super(x);
		
		fullPage = y;
	}
	public String mainImage()
	{
		if(fullPage)
			return null;
		//System.out.println(html);
		int l1 = 0;
		int l2 = 0;
		if(comicHost == "hiveworks")
		{
			l1 = html.indexOf("cc-comic");
			l2 = html.indexOf("src=\"http", l1);
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int p11 = html.indexOf(".jpeg", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			if(p11 == -1)
				p11 = html.length() + 1;
			p1 = (int) Math.min(p1, p11);
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			if(p1 == p11)
				ext = ".jpeg";
			String implace = html.substring(l2 + 5, l3 + 4);
			title = html.substring(html.indexOf("title=\"", l1) + 7, l2 - 2);
			return implace;
							
		}else if (comicHost == "gocomics")
		{
			l1 = html.indexOf("item-comic-image");
			l2 = html.indexOf("src=\"http", l1);
			int l3 = html.indexOf(" ", l2);
			ext = ".gif";
			String implace = html.substring(l2 + 5, l3 - 1);
			//System.out.println(title);
			//System.out.println("T");
			title = title.substring(title.indexOf("/2") + 1);
			//System.out.println(title);
			return implace;
		}else if(comicHost == "wordpress")
		{
			l1 = html.indexOf("\"comic\"");
			l2 = html.indexOf("src=\"http", l1);
			//System.out.println(l1 + " " + l2);
			//System.out.println(html.substring(l1, l2));
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			//System.out.println(html.substring(l1, l3));
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("title=\"", l1) + 7;
			l2 =  html.indexOf("\"", l1);
			title = html.substring(l1, l2);
			//System.out.println(implace);
			return implace;
							
		}else if(comicHost == "schema")
		{
			l1 = html.indexOf("\"comicContent");
			l2 = html.indexOf("src=\"/", l1);
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("title=\"", l1);
			l2 = html.indexOf("\"", l1 + 7);
			//System.out.println(l1 + " " + l2);
			title = html.substring(l1 + 7, l2);
			//System.out.println(title);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "octopuspie")
		{
			l1 = html.indexOf("strippy");
			l2 = html.substring(0,l1).lastIndexOf("http");
			int l3 = html.indexOf("\"", l2);
			ext = ".png";
			String implace = html.substring(l2, l3);
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "swords")
		{
			l1 = html.indexOf("id=\"comic-image");
			l2 = html.indexOf("src=\"/", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".png";
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return webtrim(url.toString()) + implace;
			
		}else if(comicHost == "xkcd")
		{
			l1 = html.indexOf("\"comic\"");
			l2 = html.indexOf("src=\"/", l1);
			//System.out.println(l1 + " " + l2);
			//System.out.println(html.substring(l1, l2));
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			//System.out.println(html.substring(l1, l3));
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("title=\"", l1) + 7;
			l2 =  html.indexOf("\"", l1);
			title = html.substring(l1, l2);
			//System.out.println(implace);
			return "http:" + implace;
							
		}else if(comicHost == "rockethost")
		{
			l1 = html.indexOf("\"comic\"");
			l2 = html.indexOf("src=\"", l1);
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("alt=\"", l1);
			l2 = html.indexOf("\"", l1 + 7);
			title = html.substring(l1 + 5, l2);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "unsounded")
		{
			l1 = html.lastIndexOf("\"comic\"");
			l2 = html.indexOf("pageart", l1);
			//System.out.println(l1 + " " + l2);
			int l3 = html.indexOf("jpg", l2 + 6) + 3;
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2, l3);
			implace = url.toString().substring(0, url.toString().lastIndexOf("/")) + "/" + implace;
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "gunnerkrigg")
		{
			l1 = html.lastIndexOf("\"comic_image\"");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			implace =  "https://www.gunnerkrigg.com" + implace;
			//System.out.println(implace);
			return implace;
							
		}
		else if(comicHost == "smackjeeves")
		{
			l1 = html.lastIndexOf("\"comic_image\"");
			l2 = html.substring(0,l1).lastIndexOf("src=\"");
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			if(implace.contains("http"))
				return implace;
			else
				return "http:" + implace;				
		}
		else if(comicHost == "smackjeeves")
		{
			l1 = html.lastIndexOf("thumb-image loaded");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return implace;				
		}
		else if(comicHost == "squarespace")
		{
			//System.out.println(nextPage().substring(7));
			//System.out.println(html);
			if(html.contains(nextPage().substring(7)))
				l1 = html.indexOf(nextPage().substring(7));
			else
				l1 = html.lastIndexOf("\"thumb-image\"");
			//System.out.println(l1 + " " + nextPage().substring(7));
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return implace;				
		}
		else if(comicHost == "comicfury")
		{			
			l1 = html.lastIndexOf("\"comicimage\"");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return implace;				
		}
		return null;
		
		
	}
	public String firstPage() throws MalformedURLException, IOException
	{
		if(comicHost == "hiveworks")
		{
			//System.out.println( html.indexOf("nav") + " " + html.contains("nav"));
			int l1;
			if(html.contains("cc-nav"))
				l1 = html.indexOf("first", html.indexOf("cc-nav"));
			else
				l1 = html.indexOf("nav");
			//System.out.println("l1 = " + l1);
			int l2 = html.indexOf("http", l1);
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			return implace;
		}
		else if(comicHost == "gocomics")
		{
			int l1 = html.indexOf("cta-0");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			Webpage("https://www.gocomics.com" + implace);
			l1 = html.indexOf("fa-backward");
			l2 = html.substring(0,l1).lastIndexOf("\'/");
			l3 = html.indexOf("\'", l2 + 1);
			implace = html.substring(l2 + 1, l3);
			title = implace;
			return "https://www.gocomics.com" + implace;
		}
		else if(comicHost == "wordpress")
		{
			int l1 = 0;
			if(html.contains("navi-first"))
			{
				l1 = html.indexOf("navi-first");
			}
			else
				l1 = html.indexOf("First");
			int l2 = 0;
			if(!html.substring(l1,l1 + 30).contains("void"))
					l2 = html.substring(0,l1).lastIndexOf("http");
			else
			{
				return url.toString();
			}
			//System.out.println(l1 + " " + l2);
			//System.out.println(html.substring(l1, l2));
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			//System.out.println(implace);
			return implace;
		}
		else if(comicHost == "schema")
		{
			int l1 = html.indexOf("left nav");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(url.toString() + implace);
			return webtrim(url.toString()) + implace;
		}
		else if(comicHost == "octopuspie")
		{
			int l1 = html.indexOf("first-comic-new.png");
			int l2 = html.substring(0,l1).lastIndexOf("href=\"http");
			int l3 = html.indexOf("\"", l2 + 7);
			String implace = html.substring(l2 + 6, l3);
			//System.out.println(implace);
			return implace;
		}
		else if(comicHost == "swords")
		{
			int l1 = html.indexOf("button navigation-first");
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			return webtrim(url.toString()) + implace;
			
		}
		else if(comicHost == "xkcd")
		{
			return "https://xkcd.com/1/";
		}
		else if(comicHost == "rockethost")
		{
			int l1 = html.indexOf("\"/first");
			int l2 = html.substring(0,l1 - 2).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(implace);
			//System.out.println(webtrim(url.toString()));
			return webtrim(url.toString()) + implace;
		}
		else if(comicHost == "unsounded")
		{
			return "http://www.casualvillain.com/Unsounded/comic/ch01/ch01_01.html";
		}
		else if(comicHost == "gunnerkrigg")
		{
			return "https://www.gunnerkrigg.com/?p=1";
		}
		else if(comicHost == "smackjeeves")
		{
			int l1 = html.indexOf("class=\"navi\"");
			int l2 = html.substring(0,l1).lastIndexOf("\"http");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return implace;
		}
		else if(comicHost == "squarespace")
		{
			int l1 = html.lastIndexOf("archive-item archive-item--show-date");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			System.out.println(webtrim(url.toString()) + implace);
			return webtrim(url.toString()) + implace;
		}
		else if(comicHost == "comicfury")
		{
			return webtrim(url.toString()) + "/comics/first";
		}
		System.out.println("ll");
		return null;
	}
	public String nextPage()
	{
		if(comicHost == "hiveworks")
		{
			int l1;
			if(html.contains("cc-nav"))
				l1 = html.indexOf("next", html.indexOf("cc-nav"));
			else
				l1 = html.indexOf("prev", html.indexOf("nav"));
			int l2 = html.indexOf("http", l1);
			
			int l3 = html.indexOf("\"", l2);
			//System.out.println(l1+ " " + l2 + " " + l3);
			String implace = html.substring(l2, l3);
			//System.out.println();
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "gocomics")
		{
			int l1 = html.indexOf("fa-caret-right");
			int l2 = html.substring(0,l1).lastIndexOf("\'/");
			int l3 = html.indexOf("\'", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			title = implace;
			return "https://www.gocomics.com" + implace;
		}else if(comicHost == "wordpress")
		{
			int l1 = 0;
			if(html.contains("navi-next"))
				l1 = html.indexOf("navi-next");
			else
				l1 = html.indexOf("Next");
			int l2 = html.substring(0,l1).lastIndexOf("http");
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			//System.out.println(html.substring(l2, l1 + 20));
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "schema")
		{
			int l1 = html.indexOf("right nav", html.indexOf("right nav") + 1);
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(url.toString() + implace);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "octopuspie")
		{
			int l1 = html.indexOf("frontarrow.png");
			int l2 = html.substring(0,l1).lastIndexOf("href=\"http");
			int l3 = html.indexOf("\"", l2 + 7);
			String implace = html.substring(l2 + 6, l3);
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "swords")
		{
			int l1 = html.indexOf("button navigation-next");
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			return webtrim(url.toString()) + implace;	
		}else if(comicHost == "xkcd")
		{
			return "https://xkcd.com/" + (Integer.parseInt(url.toString().substring(url.toString().indexOf("m/") + 2, url.toString().length() - 1)) + 1) + "/";
		}else if(comicHost == "rockethost")
		{
			int l1 = html.indexOf("\"next");
			int l2 = html.substring(0,l1 - 2).lastIndexOf("=\"");
			int l3 = html.indexOf("\"", l2 + 2);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(implace);
			//System.out.println(webtrim(url.toString()));
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "unsounded")
		{
			if(url.toString().equals("http://www.casualvillain.com/Unsounded/comic//ch13/you_let_me_fall.html"))
				return "http://www.casualvillain.com/Unsounded/comic//ch13/ch13_87.html";
			if(url.toString().equals("http://www.casualvillain.com/Unsounded/comic//ch14/ch14_39.html"))
				return "http://www.casualvillain.com/Unsounded/comic//ch14/ch14_40.html";
			if(url.toString().equals("http://www.casualvillain.com/Unsounded/comic//ch15/ch15_50.html"))
				return null;
			int l1 = html.indexOf("class=\"forward");
			int l2 = html.substring(0, l1).lastIndexOf(".html");
			l2 = html.substring(0, l2).lastIndexOf("\"");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			
			if(implace.contains("/"))
			{
				l2 = html.substring(0, l1).lastIndexOf("ch");
				implace = html.substring(l2, l3);
				System.out.println("kk");
			}
			System.out.println(implace);
			int temp = url.toString().lastIndexOf("/");
			//System.out.println(implace);
			if(implace.contains("/ch") || implace.substring(0,2).contains("ch"))
				implace = "/" + implace.substring(0, 4) + "/" + implace;
			else
				implace = url.toString().substring(url.toString().substring(0, temp).lastIndexOf("/"), temp) + "/" + implace;
			System.out.println(implace);
			
			return "http://www.casualvillain.com/Unsounded/comic/" + implace;
		}else if(comicHost == "gunnerkrigg")
		{
			return "https://www.gunnerkrigg.com/?p=" + (Integer.parseInt(url.toString().substring(url.toString().indexOf("=") + 1, url.toString().length())) + 1);
		}else if(comicHost == "smackjeeves")
		{
			int l1 = html.indexOf("class=\"navi\"");
			l1 = html.indexOf("class=\"navi\"", l1 + 1);
			l1 = html.indexOf("class=\"navi\"", l1 + 1);
			int l2 = html.substring(0,l1).lastIndexOf("\"http");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return implace;
		}else if(comicHost == "squarespace")
		{
			String x = url.toString();
			//x = "/" + x.substring(x.indexOf('/', x.indexOf('/', x.indexOf('/') + 1) + 1) + 1);
			x = x.substring(x.lastIndexOf('/'));
			int l1 = html.lastIndexOf(x + "\" class=\"archive");
			//System.out.println(x + "\" class=\"archive-item-link");
			//System.out.println("ggg " + url.toString());
			int khj =  html.substring(0,l1).lastIndexOf("<!-- item -->");
			//System.out.println("ggg " + url.toString());
			//System.out.println(khj + " " + l1);
			if(l1 - khj < 40)
			{
				l1 = html.substring(0,l1).lastIndexOf("\" class=\"archive") - 30;
				//System.out.println("kk");
			}
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			l2 = html.substring(0,l2 - 2).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			//System.out.println(implace);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "comicfury")
		{
			int num  = Integer.parseInt(url.toString().substring(url.toString().lastIndexOf("/")));
			return webtrim(url.toString()) + "/comics/" + num;
		}
		
		
		return null;
	}
	public String lastPage()
	{
		if(comicHost == "hiveworks")
		{
			int l1 = 0;
			if(html.contains("cc-nav"))
				l1 = html.indexOf("last", html.indexOf("cc-nav"));
			int l2 = html.indexOf("http", l1);
			
			int l3 = html.indexOf("\"", l2);
			//System.out.println(l1+ " " + l2 + " " + l3);
			String implace = html.substring(l2, l3);
			//System.out.println();
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "gocomics")
		{
			int l1 = html.indexOf("fa-forward");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			title = implace;
			return "https://www.gocomics.com" + implace;
		}else if(comicHost == "wordpress")
		{
			int l1 = 0;
			if(html.contains("navi-last"))
				l1 = html.indexOf("navi-last");
			else
				return null;
			int l2 = html.substring(0,l1).lastIndexOf("http");
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			return implace;
		}else if(comicHost == "schema")
		{
			int l1 = html.indexOf("right nav");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(url.toString() + implace);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "swords")
		{
			int l1 = html.indexOf("button navigation-last");
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 3);
			String implace = html.substring(l2 + 2, l3);
			return webtrim(url.toString()) + implace;	
		}else if(comicHost == "rockethost")
		{
			int l1 = html.indexOf("\"ultima");
			int l2 = html.substring(0,l1 - 2).lastIndexOf("\"");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(implace);
			//System.out.println(webtrim(url.toString()));
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "squarespace")
		{
			int l1 = html.indexOf("archive-item archive-item--show-date");
			int l2 = html.indexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return webtrim(url.toString()) + implace;
		}
		return null;
	}
	public boolean getComicHost()
	{
		if(html.contains("thehiveworks.com/") || html.contains("http://www.hiveworkscomics.com"))
		{
			comicHost = "hiveworks";
			return true;
		}
		if(html.contains("\"https://www.gocomics.com/\""))
		{
			comicHost = "gocomics";
			return true;
		}
		if(html.contains("generator\" content=\"WordPress") || html.contains("\"http://wordpress.org/\""))
		{
			comicHost = "wordpress";
			return true;
		}
		if(html.contains("\"http://schema.org/Comic\""))
		{
			comicHost = "schema";
			return true;
		}
		if(html.contains("\"http://www.octopuspie.com/feed/\""))
		{
			comicHost = "octopuspie";
			return true;
		}
		if(html.contains("pennomi"))
		{
			System.out.println("kk");
			comicHost = "swords";
			return true;
		}
		if(html.contains(" Layout by Ian Clasbey, davean, and chromakode "))
		{
			comicHost = "xkcd";
			return true;
		}
		if(html.contains("http://www.rockethosts.net"))
		{
			comicHost = "rockethost";
			return true;
		}
		if(html.contains("http://www.casualvillain.com/Unsounded"))
		{
			comicHost = "unsounded";
			return true;
		}
		if(html.contains("https://www.patreon.com/gunnerkrigg"))
		{
			comicHost = "gunnerkrigg";
			return true;
		}
		if(html.contains("\"http://www.smackjeeves.com\""))
		{
			comicHost = "smackjeeves";
			return true;
		}
		if(html.contains("Squarespace.afterBodyLoad(Y);"))
		{
			comicHost = "squarespace";
			return true;
		}
	
		if(html.contains("https://comicfury.com"))
		{
			comicHost = "comicfury";
			return true;
		}
			return false;
		}
	public void savePages(String x)  throws MalformedURLException, IOException
	{
			getComicHost();
			String temp = html;
			//System.out.println(html);
			while(true)
			{
				try {
					Webpage(firstPage());
					//Webpage("https://www.namesakecomic.com/comic/the-place-inside-emma");
					break;
				} catch (IOException e) {
					
					System.out.println("ll");
					html = temp;
				}
			}
			
			getImage(mainImage());
			String imageName;
			title  = title.replaceAll("/",  " " );
			if(!(title.contains(":") || title.contains("<") || title.contains(">") || title.contains("*") || title.contains("?") || title.contains("\\") || title.contains("|") || title.length() > (256 - (0 + "").length() - ext.length())))
				imageName = 0 + " " + title + ext;
			else
				imageName = 0 +  ext;
			saveImage(x + imageName);
			String last = lastPage();
			int count = 0;
		
		//Webpage("http://www.smbc-comics.com/comic/2008-09-03");
		//System.out.println(url.toString() + " " + last);
		//System.out.println(html);
		while(!(url.toString().equals(last)))
		{
			count++;
			System.out.println(count);
			temp = html;
			try {
				Webpage(nextPage());
			} catch (IOException e) {
				html = temp;
				System.out.println("ll");
				continue;
			}
	
			
			getImage(mainImage());
			title = title.replaceAll("/",  " " );
			if(!(title.contains(":") || title.contains("<") || title.contains(">") || title.contains("*") || title.contains("?") || title.contains("\\") || title.contains("|") || title.length() > (256 - (count + "").length() - ext.length())))
				imageName = count + " " + title + ext;
			else
				imageName = count +  ext;
			
			saveImage(x + imageName);
			//System.out.println(url.toString() + " " + last);
			//System.out.println(html);
		}

	}
	private String webtrim(String x)
	{
		return x.substring(0, x.indexOf('/', x.indexOf('/', x.indexOf('/') + 1) + 1));
	}
	
}

			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			String implace = html.substring(l2 + 5, l3 + 4);
			title = html.substring(html.indexOf("title=\"", l1) + 7, l2 - 2);
			return implace;
							
		}else if (comicHost == "gocomics")
		{
			l1 = html.indexOf("item-comic-image");
			l2 = html.indexOf("src=\"http", l1);
			int l3 = html.indexOf(" ", l2);
			ext = ".gif";
			String implace = html.substring(l2 + 5, l3 - 1);
			//System.out.println(title);
			//System.out.println("T");
			title = title.substring(title.indexOf("/2") + 1);
			//System.out.println(title);
			return implace;
		}else if(comicHost == "wordpress")
		{
			l1 = html.indexOf("\"comic\"");
			l2 = html.indexOf("src=\"http", l1);
			//System.out.println(l1 + " " + l2);
			//System.out.println(html.substring(l1, l2));
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			//System.out.println(html.substring(l1, l3));
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("title=\"", l1) + 7;
			l2 =  html.indexOf("\"", l1);
			title = html.substring(l1, l2);
			//System.out.println(implace);
			return implace;
							
		}else if(comicHost == "schema")
		{
			l1 = html.indexOf("\"comicContent");
			l2 = html.indexOf("src=\"/", l1);
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("title=\"", l1);
			l2 = html.indexOf("\"", l1 + 7);
			//System.out.println(l1 + " " + l2);
			title = html.substring(l1 + 7, l2);
			//System.out.println(title);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "octopuspie")
		{
			l1 = html.indexOf("strippy");
			l2 = html.substring(0,l1).lastIndexOf("http");
			int l3 = html.indexOf("\"", l2);
			ext = ".png";
			String implace = html.substring(l2, l3);
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "swords")
		{
			l1 = html.indexOf("id=\"comic-image");
			l2 = html.indexOf("src=\"/", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".png";
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return webtrim(url.toString()) + implace;
			
		}else if(comicHost == "xkcd")
		{
			l1 = html.indexOf("\"comic\"");
			l2 = html.indexOf("src=\"/", l1);
			//System.out.println(l1 + " " + l2);
			//System.out.println(html.substring(l1, l2));
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			//System.out.println(html.substring(l1, l3));
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("title=\"", l1) + 7;
			l2 =  html.indexOf("\"", l1);
			title = html.substring(l1, l2);
			//System.out.println(implace);
			return "http:" + implace;
							
		}else if(comicHost == "rockethost")
		{
			l1 = html.indexOf("\"comic\"");
			l2 = html.indexOf("src=\"", l1);
			int p1 = html.indexOf(".jpg", l2);
			int p2 = html.indexOf(".png", l2);
			int p3 = html.indexOf(".gif", l2);
			int l3 = 0;
			if(p1 == -1)
				p1 = html.length() + 1;
			if(p2 == -1)
				p2 = html.length() + 1;
			if(p3 == -1)
				p3 = html.length() + 1;
			
			if(p3 < p1 && p3 < p2)
			{
				l3 = p3;
				ext = ".gif";
			}
			if(p2 < p1 && p2 < p3)
			{
				l3 = p2;
				ext = ".png";
			}
			if(p1 < p2 && p1 < p3)
			{
				l3 = p1;
				ext = ".jpg";
			}
			String implace = html.substring(l2 + 5, l3 + 4);
			l1 = html.indexOf("alt=\"", l1);
			l2 = html.indexOf("\"", l1 + 7);
			title = html.substring(l1 + 5, l2);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "unsounded")
		{
			l1 = html.lastIndexOf("\"comic\"");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			implace = url.toString().substring(0, url.toString().lastIndexOf("/")) + "/" + implace;
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "gunnerkrigg")
		{
			l1 = html.lastIndexOf("\"comic_image\"");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			implace =  "https://www.gunnerkrigg.com" + implace;
			//System.out.println(implace);
			return implace;
							
		}
		else if(comicHost == "smackjeeves")
		{
			l1 = html.lastIndexOf("\"comic_image\"");
			l2 = html.substring(0,l1).lastIndexOf("src=\"");
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			if(implace.contains("http"))
				return implace;
			else
				return "http:" + implace;				
		}
		else if(comicHost == "smackjeeves")
		{
			l1 = html.lastIndexOf("thumb-image loaded");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return implace;				
		}
		else if(comicHost == "squarespace")
		{
			//System.out.println(nextPage().substring(7));
			if(html.contains(nextPage().substring(7)))
				l1 = html.indexOf(nextPage().substring(7));
			else
				l1 = html.lastIndexOf("\"thumb-image\"");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return implace;				
		}
		else if(comicHost == "comicfury")
		{			
			l1 = html.lastIndexOf("\"comicimage\"");
			l2 = html.indexOf("src=\"", l1);
			int l3 = html.indexOf("\"", l2 + 6);
			ext = ".jpg";
			//System.out.println(l2 +  " " + l3 + " " + l1);
			String implace = html.substring(l2 + 5, l3);
			//System.out.println(implace);
			return implace;				
		}
		return null;
		
		
	}
	public String firstPage() throws MalformedURLException, IOException
	{
		if(comicHost == "hiveworks")
		{
			//System.out.println( html.indexOf("nav") + " " + html.contains("nav"));
			int l1;
			if(html.contains("cc-nav"))
				l1 = html.indexOf("first", html.indexOf("cc-nav"));
			else
				l1 = html.indexOf("nav");
			//System.out.println("l1 = " + l1);
			int l2 = html.indexOf("http", l1);
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			return implace;
		}
		else if(comicHost == "gocomics")
		{
			int l1 = html.indexOf("cta-0");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			Webpage("https://www.gocomics.com" + implace);
			l1 = html.indexOf("fa-backward");
			l2 = html.substring(0,l1).lastIndexOf("\'/");
			l3 = html.indexOf("\'", l2 + 1);
			implace = html.substring(l2 + 1, l3);
			title = implace;
			return "https://www.gocomics.com" + implace;
		}
		else if(comicHost == "wordpress")
		{
			int l1 = 0;
			if(html.contains("navi-first"))
			{
				l1 = html.indexOf("navi-first");
			}
			else
				l1 = html.indexOf("First");
			int l2 = 0;
			if(!html.substring(l1,l1 + 30).contains("void"))
					l2 = html.substring(0,l1).lastIndexOf("http");
			else
			{
				return url.toString();
			}
			//System.out.println(l1 + " " + l2);
			//System.out.println(html.substring(l1, l2));
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			//System.out.println(implace);
			return implace;
		}
		else if(comicHost == "schema")
		{
			int l1 = html.indexOf("left nav");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(url.toString() + implace);
			return webtrim(url.toString()) + implace;
		}
		else if(comicHost == "octopuspie")
		{
			int l1 = html.indexOf("first-comic-new.png");
			int l2 = html.substring(0,l1).lastIndexOf("href=\"http");
			int l3 = html.indexOf("\"", l2 + 7);
			String implace = html.substring(l2 + 6, l3);
			//System.out.println(implace);
			return implace;
		}
		else if(comicHost == "swords")
		{
			int l1 = html.indexOf("button navigation-first");
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			return webtrim(url.toString()) + implace;
			
		}
		else if(comicHost == "xkcd")
		{
			return "https://xkcd.com/1/";
		}
		else if(comicHost == "rockethost")
		{
			int l1 = html.indexOf("\"/first");
			int l2 = html.substring(0,l1 - 2).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(implace);
			//System.out.println(webtrim(url.toString()));
			return webtrim(url.toString()) + implace;
		}
		else if(comicHost == "unsounded")
		{
			return "http://www.casualvillain.com/Unsounded/comic/ch01/ch01_01.html";
		}
		else if(comicHost == "gunnerkrigg")
		{
			return "https://www.gunnerkrigg.com/?p=1";
		}
		else if(comicHost == "smackjeeves")
		{
			int l1 = html.indexOf("class=\"navi\"");
			int l2 = html.substring(0,l1).lastIndexOf("\"http");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return implace;
		}
		else if(comicHost == "squarespace")
		{
			int l1 = html.lastIndexOf("archive-item archive-item--show-date");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return webtrim(url.toString()) + implace;
		}
		else if(comicHost == "comicfury")
		{
			return webtrim(url.toString()) + "/comics/first";
		}
		System.out.println("ll");
		return null;
	}
	public String nextPage()
	{
		if(comicHost == "hiveworks")
		{
			int l1;
			if(html.contains("cc-nav"))
				l1 = html.indexOf("next", html.indexOf("cc-nav"));
			else
				l1 = html.indexOf("prev", html.indexOf("nav"));
			int l2 = html.indexOf("http", l1);
			
			int l3 = html.indexOf("\"", l2);
			//System.out.println(l1+ " " + l2 + " " + l3);
			String implace = html.substring(l2, l3);
			//System.out.println();
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "gocomics")
		{
			int l1 = html.indexOf("fa-caret-right");
			int l2 = html.substring(0,l1).lastIndexOf("\'/");
			int l3 = html.indexOf("\'", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			title = implace;
			return "https://www.gocomics.com" + implace;
		}else if(comicHost == "wordpress")
		{
			int l1 = 0;
			if(html.contains("navi-next"))
				l1 = html.indexOf("navi-next");
			else
				l1 = html.indexOf("Next");
			int l2 = html.substring(0,l1).lastIndexOf("http");
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			//System.out.println(html.substring(l2, l1 + 20));
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "schema")
		{
			int l1 = html.indexOf("right nav", html.indexOf("right nav") + 1);
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(url.toString() + implace);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "octopuspie")
		{
			int l1 = html.indexOf("frontarrow.png");
			int l2 = html.substring(0,l1).lastIndexOf("href=\"http");
			int l3 = html.indexOf("\"", l2 + 7);
			String implace = html.substring(l2 + 6, l3);
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "swords")
		{
			int l1 = html.indexOf("button navigation-next");
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			return webtrim(url.toString()) + implace;	
		}else if(comicHost == "xkcd")
		{
			return "https://xkcd.com/" + (Integer.parseInt(url.toString().substring(url.toString().indexOf("m/") + 2, url.toString().length() - 1)) + 1) + "/";
		}else if(comicHost == "rockethost")
		{
			int l1 = html.indexOf("\"next");
			int l2 = html.substring(0,l1 - 2).lastIndexOf("=\"");
			int l3 = html.indexOf("\"", l2 + 2);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(implace);
			//System.out.println(webtrim(url.toString()));
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "unsounded")
		{
			int l1 = html.indexOf("class=\"forward");
			int l2 = html.substring(0, l1).lastIndexOf("\"ch");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			//System.out.println(implace);
			int temp = url.toString().lastIndexOf("/");
			implace = url.toString().substring(url.toString().substring(0, temp).lastIndexOf("/"), temp) + "/" + implace;
			//System.out.println(implace);
			return "http://www.casualvillain.com/Unsounded/comic/" + implace;
		}else if(comicHost == "gunnerkrigg")
		{
			return "https://www.gunnerkrigg.com/?p=" + (Integer.parseInt(url.toString().substring(url.toString().indexOf("=") + 1, url.toString().length())) + 1);
		}else if(comicHost == "smackjeeves")
		{
			int l1 = html.indexOf("class=\"navi\"");
			l1 = html.indexOf("class=\"navi\"", l1 + 1);
			l1 = html.indexOf("class=\"navi\"", l1 + 1);
			int l2 = html.substring(0,l1).lastIndexOf("\"http");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return implace;
		}else if(comicHost == "squarespace")
		{
			String x = url.toString();
			//x = "/" + x.substring(x.indexOf('/', x.indexOf('/', x.indexOf('/') + 1) + 1) + 1);
			x = x.substring(x.lastIndexOf('/'));
			int l1 = html.lastIndexOf(x + "\" class=\"archive");
			//System.out.println(x + "\" class=\"archive-item-link");
			//System.out.println("ggg " + url.toString());
			int khj =  html.substring(0,l1).lastIndexOf("<!-- item -->");
			//System.out.println("ggg " + url.toString());
			//System.out.println(khj + " " + l1);
			if(l1 - khj < 40)
			{
				l1 = html.substring(0,l1).lastIndexOf("\" class=\"archive") - 30;
				//System.out.println("kk");
			}
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			l2 = html.substring(0,l2 - 2).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			//System.out.println(implace);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "comicfury")
		{
			int num  = Integer.parseInt(url.toString().substring(url.toString().lastIndexOf("/")));
			return webtrim(url.toString()) + "/comics/" + num;
		}
		return null;
	}
	public String lastPage()
	{
		if(comicHost == "hiveworks")
		{
			int l1 = 0;
			if(html.contains("cc-nav"))
				l1 = html.indexOf("last", html.indexOf("cc-nav"));
			int l2 = html.indexOf("http", l1);
			
			int l3 = html.indexOf("\"", l2);
			//System.out.println(l1+ " " + l2 + " " + l3);
			String implace = html.substring(l2, l3);
			//System.out.println();
			//System.out.println(implace);
			return implace;
		}else if(comicHost == "gocomics")
		{
			int l1 = html.indexOf("fa-forward");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			title = implace;
			return "https://www.gocomics.com" + implace;
		}else if(comicHost == "wordpress")
		{
			int l1 = 0;
			if(html.contains("navi-last"))
				l1 = html.indexOf("navi-last");
			else
				return null;
			int l2 = html.substring(0,l1).lastIndexOf("http");
			int l3 = html.indexOf("\"", l2);
			String implace = html.substring(l2, l3);
			return implace;
		}else if(comicHost == "schema")
		{
			int l1 = html.indexOf("right nav");
			int l2 = html.indexOf("\"/", l1);
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(url.toString() + implace);
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "swords")
		{
			int l1 = html.indexOf("button navigation-last");
			int l2 = html.substring(0,l1).lastIndexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 3);
			String implace = html.substring(l2 + 2, l3);
			return webtrim(url.toString()) + implace;	
		}else if(comicHost == "rockethost")
		{
			int l1 = html.indexOf("\"ultima");
			int l2 = html.substring(0,l1 - 2).lastIndexOf("\"");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 2, l3);
			//System.out.println(implace);
			//System.out.println(webtrim(url.toString()));
			return webtrim(url.toString()) + implace;
		}else if(comicHost == "squarespace")
		{
			int l1 = html.indexOf("archive-item archive-item--show-date");
			int l2 = html.indexOf("\"/");
			int l3 = html.indexOf("\"", l2 + 1);
			String implace = html.substring(l2 + 1, l3);
			return webtrim(url.toString()) + implace;
		}
		return null;
	}
	public boolean getComicHost()
	{
		if(html.contains("http://www.thehiveworks.com/") || html.contains("http://www.hiveworkscomics.com"))
		{
			comicHost = "hiveworks";
			return true;
		}
		if(html.contains("\"https://www.gocomics.com/\""))
		{
			comicHost = "gocomics";
			return true;
		}
		if(html.contains("generator\" content=\"WordPress") || html.contains("\"http://wordpress.org/\""))
		{
			comicHost = "wordpress";
			return true;
		}
		if(html.contains("\"http://schema.org/Comic\""))
		{
			comicHost = "schema";
			return true;
		}
		if(html.contains("\"http://www.octopuspie.com/feed/\""))
		{
			comicHost = "octopuspie";
			return true;
		}
		if(html.contains("pennomi"))
		{
			System.out.println("kk");
			comicHost = "swords";
			return true;
		}
		if(html.contains(" Layout by Ian Clasbey, davean, and chromakode "))
		{
			comicHost = "xkcd";
			return true;
		}
		if(html.contains("http://www.rockethosts.net"))
		{
			comicHost = "rockethost";
			return true;
		}
		if(html.contains("http://www.casualvillain.com/Unsounded"))
		{
			comicHost = "unsounded";
			return true;
		}
		if(html.contains("https://www.patreon.com/gunnerkrigg"))
		{
			comicHost = "gunnerkrigg";
			return true;
		}
		if(html.contains("\"http://www.smackjeeves.com\""))
		{
			comicHost = "smackjeeves";
			return true;
		}
		if(html.contains("Squarespace.afterBodyLoad(Y);"))
		{
			comicHost = "squarespace";
			return true;
		}
	
		if(html.contains("https://comicfury.com"))
		{
			comicHost = "comicfury";
			return true;
		}
			return false;
		}
	public void savePages(String x)  throws MalformedURLException, IOException
	{
			getComicHost();
			String temp = html;
			while(true)
			{
				try {
					Webpage(firstPage());
					break;
				} catch (IOException e) {
					System.out.println("ll");
					html = temp;
				}
			}
			
			getImage(mainImage());
			String imageName;
			title  = title.replaceAll("/",  " " );
			if(!(title.contains(":") || title.contains("<") || title.contains(">") || title.contains("*") || title.contains("?") || title.contains("\\") || title.contains("|") || title.length() > (256 - (0 + "").length() - ext.length())))
				imageName = 0 + " " + title + ext;
			else
				imageName = 0 +  ext;
			saveImage(x + imageName);
			String last = lastPage();
			int count = 0;
		
		//Webpage("http://www.smbc-comics.com/comic/2008-09-03");
		//System.out.println(url.toString() + " " + last);

		while(!(url.toString().equals(last)))
		{
			count++;
			System.out.println(count);
			temp = html;
			try {
				Webpage(nextPage());
			} catch (IOException e) {
				html = temp;
				System.out.println("ll");
				continue;
			}
	

			
			getImage(mainImage());
			title  = title.replaceAll("/",  " " );
			if(!(title.contains(":") || title.contains("<") || title.contains(">") || title.contains("*") || title.contains("?") || title.contains("\\") || title.contains("|") || title.length() > (256 - (count + "").length() - ext.length())))
				imageName = count + " " + title + ext;
			else
				imageName = count +  ext;
			saveImage(x + imageName);
			
		}

	}
	private String webtrim(String x)
	{
		return x.substring(0, x.indexOf('/', x.indexOf('/', x.indexOf('/') + 1) + 1));
	}
	
}
