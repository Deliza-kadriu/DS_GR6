import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ImportKey {

	private static String checkIfItsPublicOrPrivate(Document doc) {
		doc.getDocumentElement().normalize();

		int a = doc.getElementsByTagName("RSAKeyValue").item(0).getChildNodes().getLength();
		NodeList rsa1 = doc.getElementsByTagName("RSAKeyValue");
		NodeList exp1 = doc.getElementsByTagName("Exponent");
		NodeList mod1 = doc.getElementsByTagName("Modulus");
		NodeList p1 = doc.getElementsByTagName("P");
		NodeList q1 = doc.getElementsByTagName("Q");
		NodeList dp1 = doc.getElementsByTagName("DP");
		NodeList dq1 = doc.getElementsByTagName("DQ");
		NodeList inv1 = doc.getElementsByTagName("InverseQ");
		NodeList d1 = doc.getElementsByTagName("D");
		int rsa = rsa1.getLength();
		int mod = mod1.getLength();
		int exp = exp1.getLength();
		int p = p1.getLength();
		int q = q1.getLength();
		int dp = dp1.getLength();
		int dq = dq1.getLength();
		int inv = inv1.getLength();
		int d = d1.getLength();

		if (rsa == 1 && a == 5 && mod == 1 && exp == 1 && p == 0 && q == 0 && dp == 0 && dq == 0 && inv == 0
				&& d == 0) {

			return "public";

		} else if (rsa == 1 && a == 17 && mod == 1 && exp == 1 && p == 1 && q == 1 && dp == 1 && dq == 1 && inv == 1
				&& d == 1)

			return "private";

		else

			return "error";

	}

}
