package es.ubu.nte;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

	/** Web URL */
	private String html;

	/**
	 * Constructor.
	 * 
	 * @param html
	 *            web url
	 */
	public HTMLParser(String html) {
		this.html = html;
	}

	/**
	 * 
	 */
	public Set<String> linkParser() {

		Elements doc;
		Elements selected_links;
		Set<String> links = new HashSet<String>();
		try {
			// Get only the side panel's  links.
			doc = Jsoup.connect(html).get().getElementsByClass("side");

			// get all selected links
			selected_links = selectLinks(doc.select("a[href]"));
			
			for(Element link : selected_links)
				links.add(fixDomain(link.attr("href")));
	
			//printLinks(links);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return links;
	}

	private void printLinks(Elements links) {
		for (Element link : links) {
			// get the value from href attribute
			System.out.println("\nlink : " + link.attr("href"));
			System.out.println("text : " + link.text());
		}
	}

	/**
	 * Takes all the link from a web pages and selects those that follow
	 * specific rules.
	 * 
	 * @param links
	 *            links from a web page
	 * @return selected links
	 */
	private Elements selectLinks(Elements links) {
		Elements selected = new Elements();
		String domain = getDomain();

		String pattern = ".*/r/.[^/]+";
		String domain_pattern = ".*/r/" + domain + "/?.*";

		for (Element link : links) {
			if (Pattern.matches(pattern, link.attr("href"))
					&& !Pattern.matches(domain_pattern, link.attr("href")))
				selected.add(link);
		}
		return selected;
	}

	/**
	 * Return the subreddit name to avoid links to it self.
	 */
	private String getDomain() {
		String domain = "";
		Pattern url_pattern = Pattern.compile("(.*/r/)(.[^/]+)");
		Matcher matcher = url_pattern.matcher(html);
		if (matcher.find()) {
			domain = matcher.group(2);
		}
		return domain;
	}
	
	private String fixDomain(String html) {
		String domain = "";
		Pattern url_pattern = Pattern.compile("(.+/r/)(.[^/]+)");
		Matcher matcher = url_pattern.matcher(html);
		if (matcher.find()) {
			domain = html;
		} else {
			domain = "http://www.reddit.com" + html;
		}
		return domain;
	}
}
