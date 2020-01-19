package app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class tester {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		PageFinder w1 = new PageFinder("http://crisis02.webcomic.ws", false);
		//PageFinder w1 = new PageFinder("http://www.dumbingofage.com/2019/comic/book-10/01-birthday-pursuit/fruits/", false);
		//System.out.println("4 Tom was so disconnected from his own feelings that he offered gold and jewels whenever expressing himself. By the time he and Sally had been together for five years, he had begun to understand that his emotional wea".);
		w1.savePages("C:\\Users\\1stew\\Documents\\Smbc\\");
		//w1.savePages("C:\\Users\\Ethan\\Documents\\Smbc\\");
		//w1.mainImage();

	}

}
